package br.com.supera.game.store.service;

import br.com.supera.game.store.dto.CartDTO;
import br.com.supera.game.store.dto.UserDTO;
import br.com.supera.game.store.entity.Cart;
import br.com.supera.game.store.exception.UserNotFoundException;
import br.com.supera.game.store.mapper.CartMapper;
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
    private final UserService userService;

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
        System.out.println(userDTO);
        userService.verifyIfUserExists(userDTO.getCpf());
        CartDTO cartDTO = new CartDTO();

        cartDTO.setUser(userDTO);
        cartDTO.setDateCreated(new Date());
        Cart cart = cartMapper.toModel(cartDTO);
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toDTO(savedCart);
    }
}
