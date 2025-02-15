package ru.otus;

import java.util.HashMap;
import ru.otus.api.Atm;
import ru.otus.enums.Denomination;

public class Main {
    public static void main(String[] args) {
        final Atm ruAtm = new RuAtm();
        final var deposit = new HashMap<Denomination, Integer>();
        deposit.put(Denomination.TEN, 110);
        deposit.put(Denomination.FIFTY, 450);
        deposit.put(Denomination.TWO_HUNDRED, 800);
        deposit.put(Denomination.TWO_THOUSAND, 4000);
        deposit.put(Denomination.FIVE_THOUSAND, 20000);
        ruAtm.deposit(deposit);
        ruAtm.getCurrentAmountCash();
        ruAtm.withdrawal(12360);
    }
}
