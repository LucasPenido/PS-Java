package br.com.supera.game.store.mapper;

import br.com.supera.game.store.dto.CartDTO;
import br.com.supera.game.store.dto.ProductsCartDTO;
import br.com.supera.game.store.entity.Cart;
import br.com.supera.game.store.entity.ProductsCart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    Cart toModel(CartDTO cartDTO);

    default CartDTO toDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getId());
        cartDTO.setDateCreated(cart.getDateCreated());
        cartDTO.setUser(cart.getUser());
        cartDTO.setQuantity(cart.getQuantity());

        final ProductCartMapper productCartMapper = ProductCartMapper.INSTANCE;
        List<ProductsCartDTO> productsCartDTOList = new ArrayList<>();

        for (ProductsCart productCart : cart.getProductsCart()) {
            productsCartDTOList.add(productCartMapper.toDTO(productCart));
        }

        cartDTO.setProductsCart(productsCartDTOList);
        return cartDTO;
    }
}
