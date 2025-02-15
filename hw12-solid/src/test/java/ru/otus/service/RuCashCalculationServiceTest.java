package ru.otus.service;

import java.util.HashMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.enums.Denomination;

public class RuCashCalculationServiceTest {

    private RuCashCalculationService cashCalculationService;

    @BeforeEach
    void setUp() {
        cashCalculationService = new RuCashCalculationService();
    }

    @Test
    public void getCurrentCashTest() {
        final var atmCash = new HashMap<Denomination, Integer>();
        atmCash.put(Denomination.TEN, 130);
        atmCash.put(Denomination.FIFTY, 350);
        atmCash.put(Denomination.FIVE_THOUSAND, 5000);
        final var currentCash = cashCalculationService.getCurrentCash(atmCash);

        Assertions.assertThat(currentCash).isEqualTo(5480);
    }

    @Test
    public void getCurrentCashIfCashIsAbsentTest() {
        final var atmCash = new HashMap<Denomination, Integer>();
        final var currentCash = cashCalculationService.getCurrentCash(atmCash);

        Assertions.assertThat(currentCash).isEqualTo(0);
    }
}
