package br.com.flaviogf.installment;

import org.joda.time.DateTime;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ParcelTest {

    @Test
    public void shouldHasValue() {
        Parcel parcel = new Parcel(new BigDecimal(1000));

        BigDecimal expected = new BigDecimal(1000);

        BigDecimal result = parcel.getValue();

        assertEquals(expected.doubleValue(), result.doubleValue(), 0.0001);
    }

    @Test
    public void shouldHasDueDate() {
        DateTime today = new DateTime();

        Parcel parcel = new Parcel(new BigDecimal(1000), today);

        assertEquals(today, parcel.getDueDate());
    }

    @Test
    public void shouldDueDateEqualToTodayWhenNotIsInform() {
        Parcel parcel = new Parcel(new BigDecimal(1000));

        DateTime result = parcel.getDueDate();

        DateTime expected = new DateTime();

        assertEquals(expected.dayOfYear(), result.dayOfYear());
    }
}
