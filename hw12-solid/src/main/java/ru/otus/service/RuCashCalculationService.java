package ru.otus.service;

import java.util.Map;
import org.jetbrains.annotations.NotNull;
import ru.otus.api.CashCalculationService;
import ru.otus.enums.Denomination;

public class RuCashCalculationService implements CashCalculationService {

    @Override
    public Integer getCurrentCash(final @NotNull Map<Denomination, Integer> atmCash) {
        return atmCash.values().stream().reduce(Integer::sum).orElse(0);
    }
}
