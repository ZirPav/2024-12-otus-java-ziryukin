package ru.otus.enums;

import org.jetbrains.annotations.NotNull;

public enum Denomination {
    TEN(10),
    FIFTY(50),
    ONE_HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000),
    TWO_THOUSAND(2000),
    FIVE_THOUSAND(5000),
    ;

    private final Integer denomination;

    Denomination(@NotNull Integer denomination) {
        this.denomination = denomination;
    }

    public Integer getDenomination() {
        return denomination;
    }
}
