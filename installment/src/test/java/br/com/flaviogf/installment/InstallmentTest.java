package br.com.flaviogf.installment;

import org.joda.time.Days;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

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

        installment.setNumberOfParcel(2, new ParcelRedistributeTotal());

        assertEquals(2, installment.getNumberOfParcel());
    }

    @Test
    public void shouldSetNumberOfParcelNotifyListener() {
        final Installment installment = new Installment(new BigDecimal(1000), 2);

        InstallmentChangeListener installmentChangeListener = mock(InstallmentChangeListener.class);

        installment.setInstallmentChangeListener(installmentChangeListener);

        installment.setNumberOfParcel(3, new ParcelRedistributeTotal());

        verify(installmentChangeListener, only()).onInstallmentChange();
    }

    @Test
    public void shouldSetNumberOfParcelUpdateListOfParcel() {
        final Installment installment = new Installment(new BigDecimal(1000), 2);

        InstallmentChangeListener installmentChangeListener = mock(InstallmentChangeListener.class);

        installment.setInstallmentChangeListener(installmentChangeListener);

        installment.setNumberOfParcel(3, new ParcelRedistributeTotal());

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

        installment.updateParcelValue(index, new BigDecimal(750), new ParcelRedistributeTotalFromIndex(index + 1));

        final BigDecimal result = installment.getListOfParcel().get(0).getValue();

        final BigDecimal expected = new BigDecimal(750).setScale(2, RoundingMode.UP);

        assertEquals(expected, result);
    }

    @Test
    public void shouldUpdateParcelValueNotifyListener() {
        final Installment installment = new Installment(new BigDecimal(1000), 2);

        final int index = 0;

        InstallmentChangeListener listener = mock(InstallmentChangeListener.class);

        installment.setInstallmentChangeListener(listener);

        installment.updateParcelValue(index, new BigDecimal(750), new ParcelRedistributeTotalFromIndex(index));

        verify(listener, only()).onInstallmentChange();
    }

    @Test
    public void shouldUpdateParcelRedistributeParcelExceptIndexWhenUseParcelRedistributeTotalExpectIndex() {
        final Installment installment = new Installment(new BigDecimal(1000), 2);

        installment.setNumberOfParcel(3, new ParcelRedistributeTotal());

        final int index = 0;

        installment.updateParcelValue(index, new BigDecimal(750), new ParcelRedistributeTotalFromIndex(index + 1));

        final Parcel firstParcel = installment.getListOfParcel().get(0);
        final Parcel secondParcel = installment.getListOfParcel().get(1);
        final Parcel thirdParcel = installment.getListOfParcel().get(2);

        assertEquals(new BigDecimal(750).setScale(2, RoundingMode.UP), firstParcel.getValue());
        assertEquals(new BigDecimal(125).setScale(2, RoundingMode.UP), secondParcel.getValue());
        assertEquals(new BigDecimal(125).setScale(2, RoundingMode.UP), thirdParcel.getValue());
    }

    @Test
    public void shouldUpdateParcelRedistributeParcelExceptIndexWhenUseParcelRedistributeTotalExceptIndexWithOneParcel() {
        final Installment installment = new Installment(new BigDecimal(1000), 2);

        installment.setNumberOfParcel(2, new ParcelRedistributeTotal());

        final int index = 0;

        installment.updateParcelValue(index, new BigDecimal(750), new ParcelRedistributeTotalFromIndex(index + 1));

        final Parcel firstParcel = installment.getListOfParcel().get(0);
        final Parcel secondParcel = installment.getListOfParcel().get(1);

        assertEquals(new BigDecimal(750).setScale(2, RoundingMode.UP), firstParcel.getValue());
        assertEquals(new BigDecimal(250).setScale(2, RoundingMode.UP), secondParcel.getValue());
    }

    @Test
    public void shouldUpdateParcelIgnoreWhenUseParcelRedistributeTotalExpectIndexWithIndexOutOfRange() {
        final Installment installment = new Installment(new BigDecimal(1000), 2);

        installment.setNumberOfParcel(1, new ParcelRedistributeTotal());

        final int index = 10;

        installment.updateParcelValue(index, new BigDecimal(750), new ParcelRedistributeTotalFromIndex(index));

        final Parcel firstParcel = installment.getListOfParcel().get(0);

        assertEquals(new BigDecimal(1000).setScale(2, RoundingMode.UP), firstParcel.getValue());
    }

    @Test
    public void shouldSetNumberOfParcelWithNotRedistributeNotRedistribute() {
        final Installment installment = new Installment(new BigDecimal(1000), 2);

        installment.setNumberOfParcel(2, new ParcelRedistributeTotal());

        installment.updateParcelValue(0, new BigDecimal(750), new ParcelRedistributeTotalFromIndex(1));

        installment.setNumberOfParcel(2, new ParcelNotRedistributeStrategy());

        final Parcel firstParcel = installment.getListOfParcel().get(0);
        final Parcel secondParcel = installment.getListOfParcel().get(1);

        assertEquals(firstParcel.getValue(), new BigDecimal(750).setScale(2, RoundingMode.UP));
        assertEquals(secondParcel.getValue(), new BigDecimal(250).setScale(2, RoundingMode.UP));
    }

    @Test
    public void shouldNumberOfParcelIsEqualToSizeListOfParcel() {
        final List<Parcel> listOfParcels = Arrays.asList(new Parcel(new BigDecimal(100)), new Parcel(new BigDecimal(200)));

        final Installment installment = new Installment(listOfParcels);

        final int result = installment.getNumberOfParcel();

        final int expected = 2;

        assertEquals(expected, result);
    }

    @Test
    public void shouldTotalIsEqualToSumListOfParcel() {
        final List<Parcel> listOfParcels = Arrays.asList(new Parcel(new BigDecimal(100)), new Parcel(new BigDecimal(200)));

        final Installment installment = new Installment(listOfParcels);

        final BigDecimal result = installment.getTotal();

        final BigDecimal expected = new BigDecimal(300);

        assertEquals(expected, result);
    }
}
