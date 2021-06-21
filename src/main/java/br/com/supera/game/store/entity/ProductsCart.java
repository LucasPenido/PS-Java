package br.com.supera.game.store.entity;

import br.com.supera.game.store.key.ProductsCartKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "ProductsCart")
@Table(name = "products_cart")
@AllArgsConstructor
@NoArgsConstructor
public class ProductsCart {

    @EmbeddedId
    private ProductsCartKey id;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    @ManyToOne
    @MapsId("cartId")
    private Cart cart;

    private int quantity;
}
