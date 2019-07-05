package br.com.flaviogf.installment;

import org.joda.time.Days;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ParcelThirtyDaysStrategyTest {

    @Test
    public void shouldListOfPlotsHasOneParcelWhenNumberOfPlotsEqualToOne() {
        BigDecimal total = new BigDecimal(1000);

        ParcelStrategy parcelStrategy = new ParcelThirtyDaysStrategy(total, 1);

        int result = parcelStrategy.getListOfParcel().size();

        int expected = 1;

        assertEquals(expected, result);
    }

    @Test
    public void shouldListOfPlotsHasTwoParcelWhenNumberOfPlotsEqualToTwo() {
        BigDecimal total = new BigDecimal(1000);

        ParcelStrategy parcelStrategy = new ParcelThirtyDaysStrategy(total, 2);

        int result = parcelStrategy.getListOfParcel().size();

        int expected = 2;

        assertEquals(expected, result);
    }

    @Test
    public void shouldSumListOfPlotsIsEqualToTotal() {
        BigDecimal total = new BigDecimal(1000);

        total = total.setScale(2, BigDecimal.ROUND_UP);

        ParcelStrategy parcelStrategy = new ParcelThirtyDaysStrategy(total, 3);

        BigDecimal result = new BigDecimal(0);

        for (Parcel parcel : parcelStrategy.getListOfParcel()) {
            result = result.add(parcel.getValue());
        }

        assertEquals(total.doubleValue(), result.doubleValue(), 0.01);
    }

    @Test
    public void shouldNextParcelIsThirtyDaysAheadOfPreviousParcel() {
        BigDecimal total = new BigDecimal(1000);

        ParcelStrategy parcelStrategy = new ParcelThirtyDaysStrategy(total, 2);

        Parcel firstParcel = parcelStrategy.getListOfParcel().get(0);
        Parcel secondParcel = parcelStrategy.getListOfParcel().get(1);

        int result = Days.daysBetween(firstParcel.getDueDate(), secondParcel.getDueDate()).getDays();

        int expected = 30;

        assertEquals(expected, result);
    }
}
