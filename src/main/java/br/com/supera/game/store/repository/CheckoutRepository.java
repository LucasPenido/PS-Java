package br.com.supera.game.store.repository;

import br.com.supera.game.store.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    Optional<Checkout> findByCartId(Long cartId);
}
