package br.com.supera.game.store.controller;

import br.com.supera.game.store.dto.UserDTO;
import br.com.supera.game.store.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> listUsers() {
        return userService.listAll();
    }

    @GetMapping("/{cpf}")
    public UserDTO findUserByCpf(@PathVariable String cpf) {
        return userService.findUserByCpf(cpf);
    }
}
