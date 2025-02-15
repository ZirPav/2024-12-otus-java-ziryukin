package ru.otus.api;

import java.util.Map;
import org.jetbrains.annotations.NotNull;
import ru.otus.enums.Denomination;

public interface CashWithdrawalService {
    /**
     * Снятие наличных средств
     * @param requestedAmount кол-во запрашиваемых наличных
     * @param atmCash текущие средства в банкомате
     * @return кол-во снятых наличных
     */
    Integer withdrawal(Integer requestedAmount, @NotNull Map<Denomination, Integer> atmCash);
}
