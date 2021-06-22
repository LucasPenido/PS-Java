package br.com.supera.game.store.service;

import br.com.supera.game.store.dto.CartDTO;
import br.com.supera.game.store.dto.CheckoutDTO;
import br.com.supera.game.store.entity.Checkout;
import br.com.supera.game.store.enums.CheckoutStatus;
import br.com.supera.game.store.exception.CartNotFoundException;
import br.com.supera.game.store.mapper.CartMapper;
import br.com.supera.game.store.mapper.CheckoutMapper;
import br.com.supera.game.store.repository.CheckoutRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CheckoutService {
    private final CheckoutRepository checkoutRepository;
    private final CheckoutMapper checkoutMapper = CheckoutMapper.INSTANCE;
    private final CartService cartService;

    public List<CheckoutDTO> findAll() {
        return checkoutRepository.findAll()
                .stream()
                .map(checkoutMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CheckoutDTO findCheckoutById(long checkoutId) {
        return checkoutMapper.toDTO(checkoutRepository.getById(checkoutId));
    }

    public CheckoutDTO findChecoutByCartId(long cartId) {
        return checkoutMapper.toDTO(checkoutRepository.findByCartId(cartId).orElseThrow());
    }

    public CheckoutDTO createCheckout(CartDTO cartDTO) throws CartNotFoundException {
        cartService.verifyIfCartExists(cartDTO.getId());
        CheckoutDTO checkoutDTO = new CheckoutDTO();

        checkoutDTO.setCart(cartDTO);
        checkoutDTO.setStatus(CheckoutStatus.IN_PROGRESS);
        checkoutDTO.setDate(new Date());
        Checkout checkout = checkoutMapper.toModel(checkoutDTO);
        Checkout savedCheckout = checkoutRepository.save(checkout);

        return checkoutMapper.toDTO(savedCheckout);
    }
}
