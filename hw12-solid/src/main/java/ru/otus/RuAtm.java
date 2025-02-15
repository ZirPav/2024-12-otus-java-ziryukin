package ru.otus;

import java.util.HashMap;
import java.util.Map;
import ru.otus.api.Atm;
import ru.otus.api.CashCalculationService;
import ru.otus.api.CashDepositService;
import ru.otus.api.CashWithdrawalService;
import ru.otus.enums.Denomination;
import ru.otus.service.RuCashCalculationService;
import ru.otus.service.RuCashDepositService;
import ru.otus.service.RuCashWithdrawalService;

public class RuAtm implements Atm {
    /**
     * Ключ - номинал денежных средств
     * Значение - суммарное кол-во денежных средств данного номинала(это не количество банкнот!)
     */
    private final Map<Denomination, Integer> atmCash = new HashMap<>();

    private final CashDepositService depositService;
    private final CashWithdrawalService withdrawalService;
    private final CashCalculationService amountCalculationService;

    public RuAtm() {
        this.depositService = new RuCashDepositService();
        this.withdrawalService = new RuCashWithdrawalService();
        this.amountCalculationService = new RuCashCalculationService();
    }

    @Override
    public Integer deposit(final Map<Denomination, Integer> depositAmount) {
        return depositService.deposit(depositAmount, atmCash);
    }

    @Override
    public Integer withdrawal(final Integer withdrawalAmount) {
        return withdrawalService.withdrawal(withdrawalAmount, atmCash);
    }

    @Override
    public Integer getCurrentAmountCash() {
        return amountCalculationService.getCurrentCash(atmCash);
    }
}
