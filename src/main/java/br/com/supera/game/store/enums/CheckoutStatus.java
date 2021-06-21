package br.com.supera.game.store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CheckoutStatus {
    APPROVED("Approved"),
    DECLINED("Declined"),
    IN_PROGRESS("In Progress"),
    CREATED("Created");

    private final String status;
}
