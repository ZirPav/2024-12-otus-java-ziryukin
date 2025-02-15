package ru.otus.service;

import java.util.Map;
import org.jetbrains.annotations.NotNull;
import ru.otus.api.CashDepositService;
import ru.otus.enums.Denomination;

public class RuCashDepositService implements CashDepositService {

    @Override
    public Integer deposit(
            final Map<Denomination, Integer> deposit, final @NotNull Map<Denomination, Integer> atmCash) {
        if (deposit == null || deposit.isEmpty() || deposit.values().stream().anyMatch(it -> it == null || it <= 0)) {
            throw new RuntimeException(
                    "Депозит не может отсутствовать и не может содержать пустые значения вносимых денежных средств");
        }
        deposit.forEach((key, value) -> atmCash.merge(key, value, Integer::sum));
        return deposit.values().stream().reduce(Integer::sum).orElseThrow();
    }
}
