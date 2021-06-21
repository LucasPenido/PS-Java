package br.com.supera.game.store.service;

import br.com.supera.game.store.dto.CheckoutDTO;
import br.com.supera.game.store.mapper.CartMapper;
import br.com.supera.game.store.mapper.CheckoutMapper;
import br.com.supera.game.store.repository.CheckoutRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CheckoutService {
    private final CheckoutRepository checkoutRepository;
    private final CheckoutMapper checkoutMapper = CheckoutMapper.INSTANCE;

    public List<CheckoutDTO> findAll() {
        return checkoutRepository.findAll()
                .stream()
                .map(checkoutMapper::toDTO)
                .collect(Collectors.toList());
    }
}
