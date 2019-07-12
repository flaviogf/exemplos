package br.com.flaviogf.installment;

import org.joda.time.Days;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class InstallmentTest {

    @Test
    public void shouldHasNumberOfPlots() {
        final Installment installment = new Installment(new BigDecimal(1000), 1);

        final int expected = 1;

        final int result = installment.getNumberOfParcel();

        assertEquals(expected, result);
    }

    @Test
    public void shouldHasTotal() {
        final Installment installment = new Installment(new BigDecimal(1000), 1);

        final BigDecimal expected = new BigDecimal(1000);

        final BigDecimal result = installment.getTotal();

        assertEquals(expected, result);
    }

    @Test
    public void shouldSetNumberOfParcel() {
        final Installment installment = new Installment(new BigDecimal(1000), 1);

        installment.setNumberOfParcel(2);

        assertEquals(2, installment.getNumberOfParcel());
    }

    @Test
    public void shouldSetNumberOfParcelNotifyListener() {
        final Installment installment = new Installment(new BigDecimal(1000), 2);

        InstallmentChangeListener installmentChangeListener = mock(InstallmentChangeListener.class);

        installment.setInstallmentChangeListener(installmentChangeListener);

        installment.setNumberOfParcel(3);

        verify(installmentChangeListener, only()).onInstallmentChange();
    }

    @Test
    public void shouldSetNumberOfParcelUpdateListOfParcel() {
        final Installment installment = new Installment(new BigDecimal(1000), 2);

        InstallmentChangeListener installmentChangeListener = mock(InstallmentChangeListener.class);

        installment.setInstallmentChangeListener(installmentChangeListener);

        installment.setNumberOfParcel(3);

        final int result = installment.getListOfParcel().size();

        final int expected = 3;

        assertEquals(expected, result);
    }

    @Test
    public void shouldListOfParcelHasOneParcelWhenUseParcelThirtyDaysStrategyWithNumberOfPlotsEqualToOne() {
        final Installment installment = new Installment(new BigDecimal(1000), 1);

        final int expected = 1;

        final int result = installment.getListOfParcel().size();

        assertEquals(expected, result);
    }

    @Test
    public void shouldListOfParcelHasTwoParcelWhenUseParcelThirtyDaysStrategyWithNumberOfPlotsEqualToTwo() {
        final Installment installment = new Installment(new BigDecimal(1000), 2);

        final int expected = 2;

        final int result = installment.getListOfParcel().size();

        assertEquals(expected, result);
    }

    @Test
    public void shouldSumListOfParcelEqualToTotal() {
        final BigDecimal total = new BigDecimal(1000).setScale(2, RoundingMode.UP);

        Installment installment = new Installment(total, 3);

        BigDecimal result = BigDecimal.ZERO;

        for (Parcel parcel : installment.getListOfParcel()) {
            result = result.add(parcel.getValue());
        }

        assertEquals(total, result);
    }

    @Test
    public void shouldNextParcelIsThirtyDaysAheadOfPreviousParcel() {
        final BigDecimal total = new BigDecimal(1000);

        final Installment installment = new Installment(total, 2);

        final Parcel firstParcel = installment.getListOfParcel().get(0);
        final Parcel secondParcel = installment.getListOfParcel().get(1);

        int result = Days.daysBetween(firstParcel.getDueDate(), secondParcel.getDueDate()).getDays();

        int expected = 30;

        assertEquals(expected, result);
    }

    @Test
    public void shouldUpdateParcelValueUpdateListOfParcel() {
        final Installment installment = new Installment(new BigDecimal(1000), 2);

        final int index = 0;

        installment.updateParcelValue(index, new BigDecimal(750), new ParcelRedistributeTotalExceptIndex(index));

        final BigDecimal result = installment.getListOfParcel().get(0).getValue();

        final BigDecimal expected = new BigDecimal(750);

        assertEquals(expected, result);
    }

    @Test
    public void shouldUpdateParcelValueNotifyListener() {
        final Installment installment = new Installment(new BigDecimal(1000), 2);

        final int index = 0;

        InstallmentChangeListener listener = mock(InstallmentChangeListener.class);

        installment.setInstallmentChangeListener(listener);

        installment.updateParcelValue(index, new BigDecimal(750), new ParcelRedistributeTotalExceptIndex(index));

        verify(listener, only()).onInstallmentChange();
    }
}
