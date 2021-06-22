package br.com.supera.game.store.service;

import br.com.supera.game.store.dto.CartDTO;
import br.com.supera.game.store.dto.ProductsCartDTO;
import br.com.supera.game.store.dto.UserDTO;
import br.com.supera.game.store.entity.Cart;
import br.com.supera.game.store.entity.ProductsCart;
import br.com.supera.game.store.exception.CartNotFoundException;
import br.com.supera.game.store.exception.UserNotFoundException;
import br.com.supera.game.store.mapper.CartMapper;
import br.com.supera.game.store.mapper.ProductCartMapper;
import br.com.supera.game.store.mapper.ProductMapper;
import br.com.supera.game.store.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CartService {
    private final CartRepository cartRepository;

    private final CartMapper cartMapper = CartMapper.INSTANCE;
    private final ProductCartMapper productCartMapper = ProductCartMapper.INSTANCE;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    private final UserService userService;
    private final ProductsCartService productsCartService;
    private final ProductService productService;

    public List<CartDTO> findAll() {
        return cartRepository.findAll()
                .stream()
                .map(cartMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CartDTO findCartByUserCpf(String cpf) {
        Cart cart = cartRepository.findByUserCpf(cpf)
                .orElseThrow();
        return cartMapper.toDTO(cart);
    }

    public CartDTO createCart(UserDTO userDTO) throws UserNotFoundException {
        userService.verifyIfUserExists(userDTO.getCpf());
        CartDTO cartDTO = new CartDTO();

        cartDTO.setUser(userDTO);
        cartDTO.setDateCreated(new Date());
        Cart cart = cartMapper.toModel(cartDTO);
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toDTO(savedCart);
    }

    public CartDTO addProductToCart(ProductsCartDTO productsCartDTO, long cartId) {
        Cart cart = new Cart();
        cart.setId(cartId);
        boolean productAlreadyExists = false;

        Cart checkCart = cartRepository.getById(cartId);
        CartDTO cartDTO = cartMapper.toDTO(checkCart);

        // Verifies if the product exists at cart, and if exists, add to the quantity.
        for (ProductsCart productsCart :
                checkCart.getProductsCart()) {
            if (productsCart.getProduct().getId() == productsCartDTO.getProduct().getId()) {
                productsCartDTO.setQuantity(productsCartDTO.getQuantity() + productsCart.getQuantity());
                productAlreadyExists = true;
                break;
            }
        }

        ProductsCart productsCart = productsCartService.updateProductCart(
                new ProductsCart(cart,
                        productMapper.toModel(
                                productService.getProductById(productsCartDTO.getProduct().getId())),
                        productsCartDTO.getQuantity()));

        if (productAlreadyExists) {
            for (ProductsCartDTO productsCartDTO1 :
                    cartDTO.getProductsCart()) {
                if (productsCartDTO1.getProduct().getId() == productsCartDTO.getProduct().getId()) {
                    productsCartDTO1.setQuantity(productsCartDTO.getQuantity());
                }
            }
        } else {
            cartDTO.getProductsCart().add(new ProductsCartDTO(productsCart.getQuantity(),
                    productMapper.toDTO(productsCart.getProduct())));
        }

        return cartDTO;
    }

    public CartDTO removeProductFromCart(ProductsCartDTO productsCartDTO, long cartId) {
        Cart cart = new Cart();
        cart.setId(cartId);
        boolean productExists = false;
        boolean removeProduct = false;

        Cart checkCart = cartRepository.getById(cartId);
        CartDTO cartDTO = cartMapper.toDTO(checkCart);
        ProductsCart newProductsCart = new ProductsCart();
        ProductsCart productsCartToremove = new ProductsCart();

        // Verifies if the product exists at cart, and if exists, subtract from the quantity.
        for (ProductsCart productsCart :
                checkCart.getProductsCart()) {
            if (productsCart.getProduct().getId() == productsCartDTO.getProduct().getId()) {
                int newQuantity = productsCart.getQuantity() - productsCartDTO.getQuantity();
                if (newQuantity <= 0) {
                    removeProduct = true;
                    productsCartToremove = new ProductsCart(cart,
                            productMapper.toModel(
                                    productService.getProductById(productsCartDTO.getProduct().getId())),
                            productsCartDTO.getQuantity());
                    productsCartService.remove(productsCartToremove);
                } else {
                    productsCartDTO.setQuantity(newQuantity);
                    productsCartService.updateProductCart(
                            new ProductsCart(cart,
                                    productMapper.toModel(
                                            productService.getProductById(productsCartDTO.getProduct().getId())),
                                    productsCartDTO.getQuantity()));
                }
                productExists = true;
                break;
            }
        }

        if (productExists) {
            if (removeProduct) {
                cartDTO.getProductsCart().remove(productCartMapper.toDTO(productsCartToremove));
            } else {
                for (ProductsCartDTO productsCartDTO1 :
                        cartDTO.getProductsCart()) {
                    if (productsCartDTO1.getProduct().getId() == productsCartDTO.getProduct().getId()) {
                        productsCartDTO1.setQuantity(productsCartDTO.getQuantity());
                        break;
                    }
                }
            }
        }

        return cartDTO;
    }

    public void verifyIfCartExists(long cartId) throws CartNotFoundException {
        Optional<Cart> optSavedCart = cartRepository.findById(cartId);
        if (optSavedCart.isEmpty()) {
            throw new CartNotFoundException(cartId);
        }
    }
}
