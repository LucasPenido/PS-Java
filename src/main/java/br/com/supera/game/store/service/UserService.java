package br.com.supera.game.store.service;

import br.com.supera.game.store.dto.UserDTO;
import br.com.supera.game.store.entity.User;
import br.com.supera.game.store.exception.UserNotFoundException;
import br.com.supera.game.store.mapper.UserMapper;
import br.com.supera.game.store.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    public List<UserDTO> listAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findUserByCpf(String cpf) {
        User user = userRepository.findById(cpf)
                .orElseThrow();
        return userMapper.toDTO(user);
    }

    public void verifyIfUserExists(String cpf) throws UserNotFoundException {
        Optional<User> optSavedUser = userRepository.findById(cpf);
        if (optSavedUser.isEmpty()) {
            throw new UserNotFoundException(cpf);
        }
    }
}
