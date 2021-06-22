package br.com.supera.game.store.controller;

import br.com.supera.game.store.dto.CheckoutDTO;
import br.com.supera.game.store.service.CheckoutService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/checkouts")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CheckoutController {
    private final CheckoutService checkoutService;

    @GetMapping
    public List<CheckoutDTO> listCheckouts() {
        return checkoutService.findAll();
    }

    @GetMapping("/{checkoutId}")
    public CheckoutDTO findCheckoutById(@PathVariable long checkoutId) {
        return checkoutService.findCheckoutById(checkoutId);
    }

    @GetMapping("/cart/{cartId}")
    public CheckoutDTO findCheckoutByCartId(@PathVariable long cartId) {
        return checkoutService.findChecoutByCartId(cartId);
    }
}
