package br.com.supera.game.store.controller;

import br.com.supera.game.store.dto.CartDTO;
import br.com.supera.game.store.dto.ProductsCartDTO;
import br.com.supera.game.store.dto.UserDTO;
import br.com.supera.game.store.exception.UserNotFoundException;
import br.com.supera.game.store.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user/{cpf}")
    public CartDTO findCartByUserCpf(@PathVariable String cpf) {
        return cartService.findCartByUserCpf(cpf);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartDTO createCart(@RequestBody UserDTO userDTO) throws UserNotFoundException {
        return cartService.createCart(userDTO);
    }

    @PostMapping("/{cartId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CartDTO addProductToCart(@RequestBody ProductsCartDTO productsCartDTO, @PathVariable long cartId) {
        return cartService.addProductToCart(productsCartDTO, cartId);
    }

    @DeleteMapping("/{cartId}")
    public CartDTO removeProductFromCart(@RequestBody ProductsCartDTO productsCartDTO, @PathVariable long cartId) {
        return cartService.removeProductFromCart(productsCartDTO, cartId);
    }
}
