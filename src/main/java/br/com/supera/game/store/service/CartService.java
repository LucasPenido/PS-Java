package br.com.supera.game.store.service;

import br.com.supera.game.store.dto.CartDTO;
import br.com.supera.game.store.dto.ProductsCartDTO;
import br.com.supera.game.store.dto.UserDTO;
import br.com.supera.game.store.entity.Cart;
import br.com.supera.game.store.entity.ProductsCart;
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

        // Verifies if exists the product at cart, and if exists, add to the quantity.
        for (ProductsCart productsCart :
                checkCart.getProductsCart()) {
            if (productsCart.getProduct().getId() == productsCartDTO.getProduct().getId()) {
                productsCartDTO.setQuantity(productsCartDTO.getQuantity() + productsCart.getQuantity());
                productAlreadyExists = true;
                break;
            }
        }

        ProductsCart productsCart = productsCartService.addProductToCart(
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
}
