package br.com.supera.game.store.repository;

import br.com.supera.game.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findAllByOrderByScore();
    public List<Product> findAllByOrderByPrice();
    public List<Product> findAllByOrderByName();
}
