package br.com.flaviogf.installment;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InstallmentTest {

    @Test
    public void shouldHasNumberOfPlots() {
        ParcelStrategy parcelStrategy = new ParcelThirtyDaysStrategy(new BigDecimal(1000), 1);

        Installment installment = new Installment(parcelStrategy);

        int expected = 1;

        int result = installment.getNumberOfPlots();

        assertEquals(expected, result);
    }

    @Test
    public void shouldHasTotal() {
        ParcelStrategy parcelStrategy = new ParcelThirtyDaysStrategy(new BigDecimal(1000), 1);

        Installment installment = new Installment(parcelStrategy);

        BigDecimal expected = new BigDecimal(1000);
        expected = expected.setScale(2, BigDecimal.ROUND_UP);

        BigDecimal result = installment.getTotal();

        assertEquals(expected.doubleValue(), result.doubleValue(), 0.01);
    }

    @Test
    public void shouldListOfPlotsHasOneParcelWhenUseParcelStepMonthStrategyWithNumberOfPlotsEqualToOne() {
        ParcelStrategy parcelStrategy = new ParcelThirtyDaysStrategy(new BigDecimal(1000), 1);

        Installment installment = new Installment(parcelStrategy);

        int expected = 1;

        List<Parcel> listOfPlots = installment.getListOfPlots();

        int result = listOfPlots.size();

        assertEquals(expected, result);
    }

    @Test
    public void shouldListOfPlotsHasTwoParcelWhenUseParcelStepMonthStrategyWithNumberOfPlotsEqualToTwo() {
        ParcelStrategy parcelStrategy = new ParcelThirtyDaysStrategy(new BigDecimal(1000), 2);

        Installment installment = new Installment(parcelStrategy);

        int expected = 2;

        List<Parcel> listOfPlots = installment.getListOfPlots();

        int result = listOfPlots.size();

        assertEquals(expected, result);
    }
}

