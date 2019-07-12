package br.com.flaviogf.payment;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RedistributeFromIndex implements RedistributeStrategy {

    private final int fromIndex;

    public RedistributeFromIndex(int fromIndex) {
        this.fromIndex = fromIndex;
    }

    @Override
    public void redistribute(Payment payment) {
        BigDecimal amountPaidUntilFromIndex = BigDecimal.ZERO;

        for (int i = 0; i < fromIndex; i++) {
            amountPaidUntilFromIndex = amountPaidUntilFromIndex.add(payment.getPaymentMethods().get(i).getValue());
        }

        final BigDecimal totalToRedistribute = payment.getTotal().subtract(amountPaidUntilFromIndex);

        final BigDecimal paymentMethodsSize = new BigDecimal(payment.getPaymentMethods().size() - fromIndex);

        final BigDecimal newPaymentMethodValue = totalToRedistribute.divide(paymentMethodsSize, 2, RoundingMode.UP);

        for (int i = fromIndex; i < payment.getPaymentMethods().size(); i++) {
            final PaymentMethod oldPaymentMethod = payment.getPaymentMethods().get(i);
            final String description = oldPaymentMethod.getDescription();
            final PaymentMethod newPaymentMethod = new PaymentMethod(description, newPaymentMethodValue);

            payment.getPaymentMethods().set(i, newPaymentMethod);

            payment.subtractAmount(oldPaymentMethod.getValue());
            payment.addAmount(newPaymentMethodValue);
        }
    }
}
