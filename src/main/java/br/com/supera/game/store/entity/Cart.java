package br.com.supera.game.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @ManyToOne
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "cart")
    private List<ProductsCart> productsCart = new ArrayList<>();

    @Transient
    private int quantity;

    public int getQuantity() {
        int total = 0;

        for (ProductsCart productCart :
                this.productsCart) {
            total += productCart.getQuantity();
        }

        return total;
    }
}
