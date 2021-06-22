package br.com.supera.game.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartNotFoundException extends Exception{
    public CartNotFoundException(long cartId) {
        super(String.format("Cart with id %s not found.", cartId));
    }
}
