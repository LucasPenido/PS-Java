package br.com.supera.game.store.mapper;

import br.com.supera.game.store.dto.CartDTO;
import br.com.supera.game.store.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    Cart toModel(CartDTO cartDTO);

    CartDTO toDTO(Cart cart);
}
