package br.com.flaviogf.installment;

import org.joda.time.DateTime;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ParcelTest {

    @Test
    public void shouldHasValue() {
        final Parcel parcel = new Parcel(new BigDecimal(1000));

        final BigDecimal expected = new BigDecimal(1000);

        final BigDecimal result = parcel.getValue();

        assertEquals(expected, result);
    }

    @Test
    public void shouldHasDueDate() {
        final DateTime today = new DateTime();

        final Parcel parcel = new Parcel(new BigDecimal(1000), today);

        assertEquals(today, parcel.getDueDate());
    }

    @Test
    public void shouldDueDateEqualToTodayWhenNotIsInform() {
        final Parcel parcel = new Parcel(new BigDecimal(1000));

        final DateTime result = parcel.getDueDate();

        final DateTime expected = new DateTime();

        assertEquals(expected.dayOfYear(), result.dayOfYear());
    }
}
