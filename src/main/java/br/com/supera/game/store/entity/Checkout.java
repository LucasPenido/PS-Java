package br.com.supera.game.store.entity;

import br.com.supera.game.store.enums.CheckoutStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Checkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CheckoutStatus status;

    @OneToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @Transient
    private BigDecimal subtotal;

    public BigDecimal getSubTotal() {
        BigDecimal subTotal = new BigDecimal("0");

        for (ProductsCart productsCart :
                this.cart.getProductsCart()) {
            BigDecimal subtotal =
                    productsCart.getProduct().getPrice().multiply(BigDecimal.valueOf(productsCart.getQuantity()));
            subTotal = subTotal.add(subtotal);
        }

        return subTotal;
    }

    @Transient
    private BigDecimal shipping;

    public BigDecimal getShipping() {
        BigDecimal subTotal = this.getSubTotal();
        BigDecimal limit = new BigDecimal("250");

        if (subTotal.compareTo(limit) >= 0) {
            subTotal = BigDecimal.ZERO;
        } else {
            subTotal = BigDecimal.TEN.multiply(BigDecimal.valueOf(this.cart.getQuantity()));
        }

        return subTotal;
    }

    @Transient
    private BigDecimal total;

    public BigDecimal getTotal() {
        BigDecimal subTotal = this.getSubTotal();
        BigDecimal shipping = this.getShipping();

        return subTotal.add(shipping);
    }
}
