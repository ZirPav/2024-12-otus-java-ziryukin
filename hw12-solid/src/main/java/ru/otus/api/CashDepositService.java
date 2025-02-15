package ru.otus.api;

import java.util.Map;
import org.jetbrains.annotations.NotNull;
import ru.otus.enums.Denomination;

public interface CashDepositService {
    /**
     * Внесение наличных средств клиентом
     * @param deposit внесенные наличные средства
     * @param atmCash текущие средства в банкомате
     * @return кол-во внесенных наличных средств
     */
    Integer deposit(Map<Denomination, Integer> deposit, @NotNull Map<Denomination, Integer> atmCash);
}
