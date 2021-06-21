package br.com.supera.game.store.dto;

import br.com.supera.game.store.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private long cartId;
    private Date dateCreated;
    private User user;
    private List<ProductsCartDTO> productsCart = new ArrayList<>();
    private int quantity;
}
