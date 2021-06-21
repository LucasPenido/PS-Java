package br.com.supera.game.store.key;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class ProductsCartKey implements Serializable {

    @Column(name = "product_id")
    private long productId;

    @Column(name = "cart_id")
    private long cartId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ProductsCartKey that = (ProductsCartKey) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(cartId, that.cartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, cartId);
    }
}
