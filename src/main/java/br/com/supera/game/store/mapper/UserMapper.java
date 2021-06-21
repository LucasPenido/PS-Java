package br.com.supera.game.store.mapper;

import br.com.supera.game.store.dto.UserDTO;
import br.com.supera.game.store.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toModel(UserDTO userDTO);

    UserDTO toDTO(User user);
}
