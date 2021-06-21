package br.com.supera.game.store.mapper;

import br.com.supera.game.store.dto.ProductsCartDTO;
import br.com.supera.game.store.entity.ProductsCart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductCartMapper {
    ProductCartMapper INSTANCE = Mappers.getMapper(ProductCartMapper.class);

    default ProductsCartDTO toDTO(ProductsCart productsCart) {
        ProductsCartDTO productsCartDTO = new ProductsCartDTO();
        productsCartDTO.setQuantity(productsCart.getQuantity());
        productsCartDTO.setProduct(productsCart.getProduct());

        return productsCartDTO;
    }
}
