package br.com.flaviogf.payment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Payment {

    private final List<PaymentMethod> paymentMethods;

    private BigDecimal total;

    private BigDecimal amountPaid;

    private PaymentChangedListener paymentChangedListener;

    private RedistributeStrategy defaultRedistributeStrategy;

    public Payment(BigDecimal total) {
        this.total = total;
        this.paymentMethods = new ArrayList<>();
        this.amountPaid = BigDecimal.ZERO;
        this.defaultRedistributeStrategy = new RedistributeTotal();
    }

    public void setTotal(BigDecimal total, RedistributeStrategy redistributeStrategy) {
        this.total = total;

        final boolean canRedistribute = paymentMethods.size() > 0;

        if (canRedistribute) {
            redistributeStrategy.redistribute(this);
        }

        onPaymentChange();
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setOnPaymentChangedListener(PaymentChangedListener paymentChangedListener) {
        this.paymentChangedListener = paymentChangedListener;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public BigDecimal getAmountPaid() {
        BigDecimal rest = total.subtract(amountPaid);

        boolean greaterOrEqualThanFiveNegative = rest.compareTo(new BigDecimal(-0.05)) >= 0;

        boolean lowerOrEqualThanZero = rest.compareTo(BigDecimal.ZERO) <= 0;

        boolean betweenFiveCentsNegativeAndZero = greaterOrEqualThanFiveNegative && lowerOrEqualThanZero;

        if (betweenFiveCentsNegativeAndZero) {
            return total.setScale(2, RoundingMode.UP);
        }

        return amountPaid.setScale(2, RoundingMode.UP);
    }

    public BigDecimal getRest() {
        final BigDecimal rest = total.subtract(amountPaid);

        if (rest.compareTo(BigDecimal.ZERO) > 0) {
            return rest;
        }

        return BigDecimal.ZERO;
    }

    public void addPaymentMethod(PaymentMethod paymentMethod) {
        paymentMethods.add(paymentMethod);

        boolean shouldRedistribute = paymentMethod.getValue().equals(BigDecimal.ZERO);

        if (shouldRedistribute) {
            defaultRedistributeStrategy.redistribute(this);
        } else {
            addAmount(paymentMethod.getValue());
        }

        onPaymentChange();
    }

    public void removePaymentMethod(int index) {
        try {
            PaymentMethod paymentMethod = paymentMethods.get(index);
            paymentMethods.remove(index);
            subtractAmount(paymentMethod.getValue());
        } catch (IndexOutOfBoundsException ignore) {
        }
    }

    public void updatePaymentMethod(int index, PaymentMethod paymentMethod, RedistributeStrategy redistributeStrategy) {
        try {
            PaymentMethod older = paymentMethods.get(index);
            subtractAmount(older.getValue());
            paymentMethods.set(index, paymentMethod);
            addAmount(paymentMethod.getValue());
            redistributeStrategy.redistribute(this);
            onPaymentChange();
        } catch (IndexOutOfBoundsException ignore) {
        }
    }

    public void addAmount(BigDecimal value) {
        amountPaid = amountPaid.add(value);
    }

    public void subtractAmount(BigDecimal value) {
        amountPaid = amountPaid.subtract(value);
    }

    public void setNumberOfPayments(int numberOfPayments) {
        paymentMethods.clear();

        for (int i = 0; i < numberOfPayments; i++) {
            String description = Integer.toString(i + 1);

            addPaymentMethod(new PaymentMethod(description));
        }
    }

    private void onPaymentChange() {
        if (paymentChangedListener == null) {
            return;
        }

        paymentChangedListener.onPaymentChange();
    }
}
