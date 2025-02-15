package ru.otus.api;

import java.util.Map;
import org.jetbrains.annotations.NotNull;
import ru.otus.enums.Denomination;

public interface CashCalculationService {
    /**
     * Количество денежных средств в банкомате
     */
    Integer getCurrentCash(@NotNull Map<Denomination, Integer> atmCash);
}
