package br.com.flaviogf.payment;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RedistributeTotal implements RedistributeStrategy {

    @Override
    public void redistribute(Payment payment) {
        final int paymentMethodsQuantity = payment.getPaymentMethods().size();

        final BigDecimal paymentValue = payment.getTotal().divide(new BigDecimal(paymentMethodsQuantity), 2, RoundingMode.UP);

        for (int i = 0; i < paymentMethodsQuantity; i++) {
            final String description = payment.getPaymentMethods().get(i).getDescription();
            final PaymentMethod paymentMethod = new PaymentMethod(description, paymentValue);
            payment.getPaymentMethods().set(i, paymentMethod);
        }

        BigDecimal amountPaid = BigDecimal.ZERO;

        for (PaymentMethod method : payment.getPaymentMethods()) {
            amountPaid = amountPaid.add(method.getValue());
        }

        payment.setAmountPaid(amountPaid);
    }
}
