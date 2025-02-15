package ru.otus.api;

import java.util.Map;
import ru.otus.enums.Denomination;

public interface Atm {

    /**
     * Внесение наличных средств
     */
    Integer deposit(final Map<Denomination, Integer> depositAmount);

    /**
     * Снятие наличных средств
     */
    Integer withdrawal(final Integer withdrawalAmount);

    /**
     * Проверка наличия средств в банкомате
     */
    Integer getCurrentAmountCash();
}
