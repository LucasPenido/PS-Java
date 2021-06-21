package br.com.supera.game.store.repository;

import br.com.supera.game.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
