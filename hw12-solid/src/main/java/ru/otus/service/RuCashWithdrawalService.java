package ru.otus.service;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import org.jetbrains.annotations.NotNull;
import ru.otus.api.CashWithdrawalService;
import ru.otus.enums.Denomination;

public class RuCashWithdrawalService implements CashWithdrawalService {

    @Override
    public Integer withdrawal(final Integer requestedAmount, final @NotNull Map<Denomination, Integer> atmCash) {
        if (requestedAmount == null || requestedAmount <= 0) {
            throw new RuntimeException("Запрашиваемая сумма наличных должна быть больше нуля");
        }
        if (requestedAmount > 200000) {
            throw new RuntimeException("Максимальная сумма снятия 200 000");
        }
        final var sortedAtmCash = new TreeMap<Denomination, Integer>(Comparator.reverseOrder());
        sortedAtmCash.putAll(atmCash);
        int temp = requestedAmount;
        for (Map.Entry<Denomination, Integer> it : sortedAtmCash.entrySet()) {
            if (it.getValue() == null || it.getValue() <= 0) {
                continue;
            }
            if (temp < it.getKey().getDenomination()) {
                continue;
            }
            int atmCashByCurrentDenomination = it.getValue();
            while (temp >= it.getKey().getDenomination() && atmCashByCurrentDenomination > 0) {
                temp -= it.getKey().getDenomination();
                atmCashByCurrentDenomination -= it.getKey().getDenomination();
            }
            it.setValue(atmCashByCurrentDenomination);
        }
        if (temp != 0) {
            throw new RuntimeException("Невозможно выдать данную сумму");
        }
        for (Map.Entry<Denomination, Integer> entry : atmCash.entrySet()) {
            entry.setValue(sortedAtmCash.get(entry.getKey()));
        }
        return requestedAmount;
    }
}
