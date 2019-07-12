package br.com.flaviogf.payment;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PaymentMethodTest {

    @Test
    public void shouldDefaultValueIsEqualToZero() {
        PaymentMethod paymentMethod = new PaymentMethod("Credit Card");

        BigDecimal result = paymentMethod.getValue();

        BigDecimal expected = BigDecimal.ZERO;

        assertEquals(expected, result);
    }
}
