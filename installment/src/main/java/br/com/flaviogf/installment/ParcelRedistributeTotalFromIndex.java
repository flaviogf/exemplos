package br.com.flaviogf.installment;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ParcelRedistributeTotalFromIndex implements ParcelStrategy {

    private final int fromIndex;

    private Installment installment;

    public ParcelRedistributeTotalFromIndex(int fromIndex) {
        this.fromIndex = fromIndex;
    }

    @Override
    public void redistribute(Installment installment) {
        this.installment = installment;

        divideParcels();
    }

    private void divideParcels() {
        boolean isLastPayment = installment.getListOfParcel().size() == fromIndex;

        if (isLastPayment) return;

        BigDecimal amountPaidUntilFromIndex = BigDecimal.ZERO;

        for (int i = 0; i < fromIndex; i++) {
            amountPaidUntilFromIndex = amountPaidUntilFromIndex.add(installment.getListOfParcel().get(i).getValue());
        }

        final BigDecimal totalToRedistribute = installment.getTotal().subtract(amountPaidUntilFromIndex);

        final BigDecimal paymentMethodsSize = new BigDecimal(installment.getListOfParcel().size() - fromIndex);

        final BigDecimal newPaymentMethodValue = totalToRedistribute.divide(paymentMethodsSize, 2, RoundingMode.HALF_UP);

        for (int i = fromIndex; i < installment.getListOfParcel().size(); i++) {
            final Parcel oldParcel = installment.getListOfParcel().get(i);
            final DateTime dueDate = oldParcel.getDueDate();
            final Parcel newPaymentMethod = new Parcel(newPaymentMethodValue, dueDate);

            installment.getListOfParcel().set(i, newPaymentMethod);
        }

        installment.calculateRest();
    }
}
