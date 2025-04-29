package ru.otus.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.enums.Denomination;

public class RuCashWithdrawalServiceTest {

    private RuCashWithdrawalService withdrawalService;

    @BeforeEach
    void setUp() {
        withdrawalService = new RuCashWithdrawalService();
    }

    @DisplayName("Успешное снятие средств")
    @Test
    public void withdrawalTest() {
        final var atmCash = new HashMap<Denomination, Integer>();
        atmCash.put(Denomination.TEN, 1350);
        atmCash.put(Denomination.FIFTY, 550);
        atmCash.put(Denomination.FIVE_HUNDRED, 500);
        atmCash.put(Denomination.FIVE_THOUSAND, 5000);

        final var withdrawnCash = withdrawalService.withdrawal(1230, atmCash);

        Assertions.assertThat(withdrawnCash).isEqualTo(1230);
        Assertions.assertThat(atmCash.get(Denomination.TEN)).isEqualTo(1170);
        Assertions.assertThat(atmCash.get(Denomination.FIVE_THOUSAND)).isEqualTo(5000);
    }

    @DisplayName("Запрашиваемая сумма некорректна: 1237")
    @Test
    public void incorrectAmountWithdrawalTest() {
        final var atmCash = new HashMap<Denomination, Integer>();
        atmCash.put(Denomination.TEN, 1350);
        atmCash.put(Denomination.FIFTY, 550);
        atmCash.put(Denomination.FIVE_HUNDRED, 500);
        atmCash.put(Denomination.FIVE_THOUSAND, 5000);

        RuntimeException exception =
                assertThrows(RuntimeException.class, () -> withdrawalService.withdrawal(1237, atmCash));

        Assertions.assertThat(exception.getMessage()).isEqualTo("Невозможно выдать данную сумму");
        Assertions.assertThat(atmCash.get(Denomination.TEN)).isEqualTo(1350);
        Assertions.assertThat(atmCash.get(Denomination.FIFTY)).isEqualTo(550);
        Assertions.assertThat(atmCash.get(Denomination.FIVE_HUNDRED)).isEqualTo(500);
        Assertions.assertThat(atmCash.get(Denomination.FIVE_THOUSAND)).isEqualTo(5000);
    }

    @DisplayName("В банкомате недостаточно средств")
    @Test
    public void notEnoughAtmCashWithdrawalTest() {
        final var atmCash = new HashMap<Denomination, Integer>();
        atmCash.put(Denomination.TEN, 1350);
        atmCash.put(Denomination.FIFTY, 550);
        atmCash.put(Denomination.FIVE_HUNDRED, 500);
        atmCash.put(Denomination.FIVE_THOUSAND, 5000);

        RuntimeException exception =
                assertThrows(RuntimeException.class, () -> withdrawalService.withdrawal(10000, atmCash));

        Assertions.assertThat(exception.getMessage()).isEqualTo("Невозможно выдать данную сумму");
        Assertions.assertThat(atmCash.get(Denomination.TEN)).isEqualTo(1350);
        Assertions.assertThat(atmCash.get(Denomination.FIFTY)).isEqualTo(550);
        Assertions.assertThat(atmCash.get(Denomination.FIVE_HUNDRED)).isEqualTo(500);
        Assertions.assertThat(atmCash.get(Denomination.FIVE_THOUSAND)).isEqualTo(5000);
    }
}
