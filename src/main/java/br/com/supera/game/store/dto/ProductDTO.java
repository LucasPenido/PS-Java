package br.com.supera.game.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    public long id;
    public String name;
    public BigDecimal price;
    public short score;
    public String image;

}
