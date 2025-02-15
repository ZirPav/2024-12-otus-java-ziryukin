package ru.otus.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.enums.Denomination;

public class RuCashDepositServiceTest {

    private RuCashDepositService cashDepositService;

    @BeforeEach
    void setUp() {
        cashDepositService = new RuCashDepositService();
    }

    @Test
    public void depositTest() {
        final var atmCash = new HashMap<Denomination, Integer>();
        atmCash.put(Denomination.TEN, 130);
        atmCash.put(Denomination.FIFTY, 350);
        atmCash.put(Denomination.FIVE_THOUSAND, 5000);
        final var deposit = new HashMap<Denomination, Integer>();
        deposit.put(Denomination.TEN, 10);
        deposit.put(Denomination.FIFTY, 50);
        deposit.put(Denomination.TWO_HUNDRED, 600);

        final var depositResult = cashDepositService.deposit(deposit, atmCash);

        Assertions.assertThat(depositResult).isEqualTo(660);
        Assertions.assertThat(atmCash.get(Denomination.TEN)).isEqualTo(140);
        Assertions.assertThat(atmCash.get(Denomination.FIFTY)).isEqualTo(400);
        Assertions.assertThat(atmCash.get(Denomination.TWO_HUNDRED)).isEqualTo(600);
        Assertions.assertThat(atmCash.get(Denomination.FIVE_THOUSAND)).isEqualTo(5000);
    }

    @Test
    public void depositIsNullTest() {
        final var atmCash = new HashMap<Denomination, Integer>();

        final var runtimeException =
                assertThrows(RuntimeException.class, () -> cashDepositService.deposit(null, atmCash));

        final var actualMessage = runtimeException.getMessage();
        Assertions.assertThat(actualMessage)
                .isEqualTo(
                        "Депозит не может отсутствовать и не может содержать пустые значения вносимых денежных средств");
    }

    @Test
    public void depositIfAtmCashIsAbsentTest() {
        final var atmCash = new HashMap<Denomination, Integer>();
        final var deposit = new HashMap<Denomination, Integer>();
        deposit.put(Denomination.TEN, 10);
        deposit.put(Denomination.FIFTY, 50);
        deposit.put(Denomination.TWO_HUNDRED, 600);

        final var depositResult = cashDepositService.deposit(deposit, atmCash);

        Assertions.assertThat(depositResult).isEqualTo(660);
        Assertions.assertThat(atmCash.get(Denomination.TEN)).isEqualTo(10);
        Assertions.assertThat(atmCash.get(Denomination.FIFTY)).isEqualTo(50);
        Assertions.assertThat(atmCash.get(Denomination.TWO_HUNDRED)).isEqualTo(600);
    }
}
