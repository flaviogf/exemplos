package br.com.flaviogf.payment;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PaymentTest {

    @Test
    public void shouldAddPaymentMethod() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        final PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(50.00));

        payment.addPaymentMethod(creditCard);

        final int result = payment.getPaymentMethods().size();

        final int expected = 1;

        assertEquals(expected, result);
    }

    @Test
    public void shouldAddPaymentMethodIncreaseAmountPaid() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        final PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(50.00));

        payment.addPaymentMethod(creditCard);

        final BigDecimal result = payment.getAmountPaid();

        final BigDecimal expected = new BigDecimal(50.00).setScale(2, RoundingMode.UP);

        assertEquals(expected, result);
    }

    @Test
    public void shouldAddPaymentMethodWithValueZeroAddPaymentMethodWithRemainingValue() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        final PaymentMethod creditCard = new PaymentMethod("Credit Card");
        final PaymentMethod card = new PaymentMethod("Card");

        payment.addPaymentMethod(creditCard);
        payment.addPaymentMethod(card);

        final BigDecimal result = payment.getAmountPaid();

        final BigDecimal expected = new BigDecimal(100.00).setScale(2, RoundingMode.UP);

        assertEquals(expected, result);
    }

    @Test
    public void shouldAddPaymentRedistributeAmountPaidInPaymentMethods() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        final PaymentMethod creditCard = new PaymentMethod("Credit Card");
        final PaymentMethod card = new PaymentMethod("Card");
        final PaymentMethod money = new PaymentMethod("Money");

        payment.addPaymentMethod(creditCard);
        payment.addPaymentMethod(card);
        payment.addPaymentMethod(money);

        final List<PaymentMethod> paymentMethods = payment.getPaymentMethods();

        final PaymentMethod firstMethod = paymentMethods.get(0);
        final PaymentMethod secondMethod = paymentMethods.get(1);

        final BigDecimal expected = new BigDecimal(33.34).setScale(2, RoundingMode.DOWN);

        assertEquals(expected, firstMethod.getValue());
        assertEquals(expected, secondMethod.getValue());
    }

    @Test
    public void shouldAddPaymentMethodSubtractRest() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        final PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(50.00));

        payment.addPaymentMethod(creditCard);

        final BigDecimal result = payment.getRest();

        final BigDecimal expected = new BigDecimal(50.00);

        assertEquals(expected, result);
    }

    @Test
    public void shouldRestEqualToZeroWhenAmountPaidIsGreaterThanTotal() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        final PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(150.00));

        payment.addPaymentMethod(creditCard);

        final BigDecimal result = payment.getRest();

        final BigDecimal expected = new BigDecimal(0.0);

        assertEquals(expected, result);
    }

    @Test
    public void shouldRestEqualToZeroWhenAmountPaidIsEqualToTotal() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        final PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(100.00));

        payment.addPaymentMethod(creditCard);

        final BigDecimal result = payment.getRest();

        final BigDecimal expected = new BigDecimal(0.0);

        assertEquals(expected, result);
    }

    @Test
    public void shouldAddPaymentNotifyListener() {
        PaymentChangedListener paymentChangedListener = mock(PaymentChangedListener.class);

        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        payment.setOnPaymentChangedListener(paymentChangedListener);

        final PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(100.00));

        payment.addPaymentMethod(creditCard);

        verify(paymentChangedListener, atLeastOnce()).onPaymentChange();
    }

    @Test
    public void shouldRemovePaymentMethodByIndex() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        final PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(50.00));

        payment.addPaymentMethod(creditCard);

        payment.removePaymentMethod(0);

        final int result = payment.getPaymentMethods().size();

        final int expected = 0;

        assertEquals(expected, result);
    }

    @Test
    public void shouldRemovePaymentSubtractAmountPaid() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        final PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(50.00));

        payment.addPaymentMethod(creditCard);

        payment.removePaymentMethod(0);

        final BigDecimal result = payment.getAmountPaid();

        final BigDecimal expected = BigDecimal.ZERO.setScale(2, RoundingMode.UP);

        assertEquals(expected, result);
    }

    @Test
    public void shouldRemovePaymentIgnorePaymentWhenNotExists() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        final PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(50.00));

        payment.addPaymentMethod(creditCard);

        payment.removePaymentMethod(10);

        final int expected = 1;

        final int result = payment.getPaymentMethods().size();

        assertEquals(expected, result);
    }

    @Test
    public void shouldReturnAmountPaid() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        final PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(150.00));

        payment.addPaymentMethod(creditCard);

        final BigDecimal expected = new BigDecimal(150.00).setScale(2, RoundingMode.UP);

        final BigDecimal result = payment.getAmountPaid();

        assertEquals(expected, result);
    }

    @Test
    public void shouldAmountPaidReturnTotalWhenAmountPaidIsGreaterThanTotalByUpToFiveCents() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        final PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(100.05));

        payment.addPaymentMethod(creditCard);

        final BigDecimal expected = new BigDecimal(100).setScale(2, RoundingMode.UP);

        final BigDecimal result = payment.getAmountPaid();

        assertEquals(expected, result);
    }

    @Test
    public void shouldSetTotalNotifyListener() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        PaymentChangedListener listener = mock(PaymentChangedListener.class);

        payment.setOnPaymentChangedListener(listener);

        payment.setTotal(new BigDecimal(200.00), new NotRedistribute());

        verify(listener, atLeastOnce()).onPaymentChange();
    }

    @Test
    public void shouldUpdatePaymentMethod() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        final PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(75.00));
        final PaymentMethod bankCheck = new PaymentMethod("Bank check", new BigDecimal(25.00));

        payment.addPaymentMethod(creditCard);
        payment.addPaymentMethod(bankCheck);

        PaymentMethod expected = new PaymentMethod("Card");

        payment.updatePaymentMethod(1, expected, new NotRedistribute());

        PaymentMethod result = payment.getPaymentMethods().get(1);

        assertEquals(expected, result);
    }

    @Test
    public void shouldUpdatePaymentMethodUpdateAmountPaid() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        final PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(75.00));
        final PaymentMethod bankCheck = new PaymentMethod("Bank check", new BigDecimal(25.00));

        payment.addPaymentMethod(creditCard);
        payment.addPaymentMethod(bankCheck);

        PaymentMethod card = new PaymentMethod("Card");

        payment.updatePaymentMethod(1, card, new NotRedistribute());

        BigDecimal result = payment.getAmountPaid();

        BigDecimal expected = new BigDecimal(75).setScale(2, RoundingMode.UP);

        assertEquals(expected, result);
    }

    @Test
    public void shouldUpdatePaymentMethodNotifyListener() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        final PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(75.00));
        final PaymentMethod bankCheck = new PaymentMethod("Bank check", new BigDecimal(25.00));

        PaymentChangedListener listener = mock(PaymentChangedListener.class);

        payment.addPaymentMethod(creditCard);
        payment.addPaymentMethod(bankCheck);

        payment.setOnPaymentChangedListener(listener);

        PaymentMethod card = new PaymentMethod("Card");

        payment.updatePaymentMethod(1, card, new NotRedistribute());

        verify(listener, only()).onPaymentChange();
    }

    @Test
    public void shouldUpdatePaymentMethodIgnorePaymentWhenNotExists() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        final PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(75.00));
        final PaymentMethod bankCheck = new PaymentMethod("Bank check", new BigDecimal(25.00));

        payment.addPaymentMethod(creditCard);
        payment.addPaymentMethod(bankCheck);

        PaymentMethod card = new PaymentMethod("Card");

        payment.updatePaymentMethod(5, card, new NotRedistribute());

        BigDecimal result = payment.getAmountPaid();

        BigDecimal expected = new BigDecimal(100).setScale(2, RoundingMode.UP);

        assertEquals(expected, result);
    }

    @Test
    public void shouldSetNumberOfPaymentsMethodsAddPaymentMethods() {
        final BigDecimal total = new BigDecimal(100.0);

        final Payment payment = new Payment(total);

        payment.setNumberOfPayments(4);

        final int result = payment.getPaymentMethods().size();

        final int expected = 4;

        assertEquals(expected, result);
    }

    @Test
    public void shouldSetTotalRedistributeWithRedistributeTotal() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        final PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(75.00));
        final PaymentMethod bankCheck = new PaymentMethod("Bank check", new BigDecimal(25.00));

        payment.addPaymentMethod(creditCard);
        payment.addPaymentMethod(bankCheck);

        payment.setTotal(new BigDecimal(200.00), new RedistributeTotal());

        final BigDecimal expected = new BigDecimal(100.00).setScale(2, RoundingMode.UP);

        for (PaymentMethod method : payment.getPaymentMethods()) {
            assertEquals(expected, method.getValue());
        }
    }

    @Test
    public void shouldSetTotalNotRedistributeAmountPaidWhenUseRedistributeTotalAndPaymentMethodsIsEmpty() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        payment.setTotal(new BigDecimal(200.00), new RedistributeTotal());

        final BigDecimal result = payment.getAmountPaid();

        final BigDecimal expected = BigDecimal.ZERO.setScale(2, RoundingMode.UP);

        assertEquals(expected, result);
    }

    @Test
    public void shouldSetTotalNotRedistributeWithNotRedistribute() {
        final BigDecimal total = new BigDecimal(100.00);

        final Payment payment = new Payment(total);

        final PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(75.00));
        final PaymentMethod bankCheck = new PaymentMethod("Bank check", new BigDecimal(25.00));

        payment.addPaymentMethod(creditCard);
        payment.addPaymentMethod(bankCheck);

        payment.setTotal(new BigDecimal(200.00), new NotRedistribute());

        assertEquals(creditCard.getValue(), payment.getPaymentMethods().get(0).getValue());
        assertEquals(bankCheck.getValue(), payment.getPaymentMethods().get(1).getValue());
    }

    @Test
    public void shouldUpdatePaymentMethodRedistributeWithRedistributeFromIndex() {
        final BigDecimal total = new BigDecimal(150.00);

        final Payment payment = new Payment(total);

        final PaymentMethod card = new PaymentMethod("Card", new BigDecimal(100));
        PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(25.00));
        final PaymentMethod bankCheck = new PaymentMethod("Bank check", new BigDecimal(20.00));
        final PaymentMethod money = new PaymentMethod("Money", new BigDecimal(5.00));

        payment.addPaymentMethod(card);
        payment.addPaymentMethod(creditCard);
        payment.addPaymentMethod(bankCheck);
        payment.addPaymentMethod(money);

        creditCard = new PaymentMethod("Credit Card", new BigDecimal(40.00));

        payment.updatePaymentMethod(1, creditCard, new RedistributeFromIndex(2));

        final BigDecimal expected = new BigDecimal(5).setScale(2, RoundingMode.UP);

        for (int i = 2; i < payment.getPaymentMethods().size(); i++) {
            final PaymentMethod method = payment.getPaymentMethods().get(i);

            assertEquals(expected, method.getValue());
        }
    }

    @Test
    public void shouldUpdatePaymentMethodUpdateAmountPaidWithRedistributeFromIndex() {
        final BigDecimal total = new BigDecimal(150.00);

        final Payment payment = new Payment(total);

        final PaymentMethod card = new PaymentMethod("Card", new BigDecimal(100));
        PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(25.00));
        final PaymentMethod bankCheck = new PaymentMethod("Bank check", new BigDecimal(20.00));
        final PaymentMethod money = new PaymentMethod("Money", new BigDecimal(5.00));

        payment.addPaymentMethod(card);
        payment.addPaymentMethod(creditCard);
        payment.addPaymentMethod(bankCheck);
        payment.addPaymentMethod(money);

        creditCard = new PaymentMethod("Credit Card", new BigDecimal(40.00));

        payment.updatePaymentMethod(1, creditCard, new RedistributeFromIndex(2));

        final BigDecimal expected = new BigDecimal(150).setScale(2, RoundingMode.UP);

        final BigDecimal result = payment.getAmountPaid();

        assertEquals(expected, result);
    }

    @Test
    public void shouldUpdatePaymentMethodUpdateAmountPaidWithRedistributeFromIndexNotRedistributeValuesWhenUpdateLastPaymentMethod() {
        final BigDecimal total = new BigDecimal(150.00);

        final Payment payment = new Payment(total);

        final PaymentMethod card = new PaymentMethod("Card", new BigDecimal(100));
        final PaymentMethod creditCard = new PaymentMethod("Credit Card", new BigDecimal(25.00));
        final PaymentMethod bankCheck = new PaymentMethod("Bank check", new BigDecimal(20.00));
        PaymentMethod money = new PaymentMethod("Money", new BigDecimal(5.00));

        payment.addPaymentMethod(card);
        payment.addPaymentMethod(creditCard);
        payment.addPaymentMethod(bankCheck);
        payment.addPaymentMethod(money);

        money = new PaymentMethod("Money", new BigDecimal(40.00));

        payment.updatePaymentMethod(3, money, new RedistributeFromIndex(4));

        final BigDecimal expected = new BigDecimal(40.00);

        final BigDecimal result = payment.getPaymentMethods().get(3).getValue();

        assertEquals(expected, result);
    }
}
