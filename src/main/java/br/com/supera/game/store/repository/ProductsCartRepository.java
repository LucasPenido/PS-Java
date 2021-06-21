package br.com.supera.game.store.repository;

import br.com.supera.game.store.entity.ProductsCart;
import br.com.supera.game.store.key.ProductsCartKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsCartRepository extends JpaRepository<ProductsCart, ProductsCartKey> {
}
