package br.com.supera.game.store.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @JsonManagedReference
    @OneToMany(mappedBy = "id.cart")
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
