package br.com.supera.game.store.controller;

import br.com.supera.game.store.dto.CartDTO;
import br.com.supera.game.store.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CartController {
    private final CartService cartService;

    @GetMapping
    public List<CartDTO> findAll() {
        return cartService.findAll();
    }

    @GetMapping("/{cpf}")
    public CartDTO findCartByUserCpf(@PathVariable String cpf) {
        return cartService.findCartByUserCpf(cpf);
    }
}
