package br.com.supera.game.store.controller;

import br.com.supera.game.store.dto.CartDTO;
import br.com.supera.game.store.dto.CheckoutDTO;
import br.com.supera.game.store.exception.CartNotFoundException;
import br.com.supera.game.store.service.CartService;
import br.com.supera.game.store.service.CheckoutService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkouts")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CheckoutController {
    private final CheckoutService checkoutService;
    private final CartService cartService;

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

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CheckoutDTO createCheckout(@RequestBody CartDTO cartDTO) throws CartNotFoundException {
        return checkoutService.createCheckout(cartDTO);
    }
}
