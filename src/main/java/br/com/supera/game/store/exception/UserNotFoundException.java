package br.com.supera.game.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception{

    public UserNotFoundException(String cpf) {
        super(String.format("User with cpf %s not found.", cpf));
    }
}
