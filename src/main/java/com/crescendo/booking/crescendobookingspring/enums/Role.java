package com.crescendo.booking.crescendobookingspring.enums;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ADMIN"),
    MANAGER("MANAGER"),
    CUSTOMER("CUSTOMER");

    private final String value;

    Role(String value) {
        this.value = value;
    }
}

