package br.com.supera.game.store.dto;

import br.com.supera.game.store.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductsCartDTO {
    private int quantity;
    private ProductDTO product;
}
