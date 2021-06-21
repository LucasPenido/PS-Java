package br.com.supera.game.store.mapper;

import br.com.supera.game.store.dto.CheckoutDTO;
import br.com.supera.game.store.entity.Checkout;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CheckoutMapper {
    CheckoutMapper INSTANCE = Mappers.getMapper(CheckoutMapper.class);

    Checkout toModel(CheckoutDTO checkoutDTO);

    CheckoutDTO toDTO(Checkout checkout);
}
