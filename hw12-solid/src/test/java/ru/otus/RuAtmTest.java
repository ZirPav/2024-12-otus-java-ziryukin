package ru.otus;

import java.util.HashMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.api.Atm;
import ru.otus.enums.Denomination;

public class RuAtmTest {

    @Test
    public void atmTest() {
        final Atm ruAtm = new RuAtm();
        final var deposit = new HashMap<Denomination, Integer>();
        deposit.put(Denomination.TEN, 110);
        deposit.put(Denomination.FIFTY, 450);
        deposit.put(Denomination.TWO_HUNDRED, 800);
        deposit.put(Denomination.TWO_THOUSAND, 4000);
        deposit.put(Denomination.FIVE_THOUSAND, 20000);

        final var beforeDeposit = ruAtm.getCurrentAmountCash();
        Assertions.assertThat(beforeDeposit).isEqualTo(0);

        final var depositResult = ruAtm.deposit(deposit);
        Assertions.assertThat(depositResult).isEqualTo(25360);

        final var afterDeposit = ruAtm.getCurrentAmountCash();
        Assertions.assertThat(afterDeposit).isEqualTo(25360);

        final var withdrawalResult = ruAtm.withdrawal(13260);
        Assertions.assertThat(withdrawalResult).isEqualTo(13260);

        final var afterWithdrawal = ruAtm.getCurrentAmountCash();
        Assertions.assertThat(afterWithdrawal).isEqualTo(12100);
    }
}
