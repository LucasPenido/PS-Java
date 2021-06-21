package br.com.supera.game.store.dto;

import br.com.supera.game.store.enums.CheckoutStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutDTO {
    private long id;
    private Date date;
    private CheckoutStatus status;
    private CartDTO cart;
    private BigDecimal shipping;
    private BigDecimal subTotal;
    private BigDecimal total;
}
