package br.com.supera.game.store.key;

import br.com.supera.game.store.entity.Cart;
import br.com.supera.game.store.entity.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class ProductsCartKey implements Serializable {

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ProductsCartKey that = (ProductsCartKey) o;
        return Objects.equals(product.getId(), that.product.getId()) &&
                Objects.equals(cart.getId(), that.cart.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(product.getId(), cart.getId());
    }
}
