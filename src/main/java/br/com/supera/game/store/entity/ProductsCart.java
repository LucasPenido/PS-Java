package br.com.supera.game.store.entity;

import br.com.supera.game.store.key.ProductsCartKey;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private ProductsCartKey id;

    private int quantity;

    public ProductsCart(Cart cart, Product product, int quantity) {
        id = new ProductsCartKey();
        id.setCart(cart);
        id.setProduct(product);
        this.quantity = quantity;
    }

    @Transient
    public Product getProduct() {
        return this.id.getProduct();
    }
}
