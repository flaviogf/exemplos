package br.com.flaviogf.installment;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ParcelRedistributeTotal implements ParcelStrategy {

    private static final int DAYS_UNTIL_THE_NEXT_PARCEL = 30;

    private Installment installment;

    @Override
    public void redistribute(Installment installment) {
        this.installment = installment;

        divideParcels();
        calculateFirstValue();
    }

    private void divideParcels() {
        DateTime firstParcelDueDate = new DateTime();

        for (int i = 0; i < installment.getNumberOfParcel(); i++) {
            int nextDay = DAYS_UNTIL_THE_NEXT_PARCEL * i;

            BigDecimal parcelValue = installment.getTotal().divide(new BigDecimal(installment.getNumberOfParcel()), 2, RoundingMode.UP);

            DateTime parcelDueDate = firstParcelDueDate.plusDays(nextDay);

            Parcel parcel = new Parcel(parcelValue, parcelDueDate);

            installment.getListOfParcel().add(parcel);
        }
    }

    private void calculateFirstValue() {
        BigDecimal totalParcel = new BigDecimal(0);

        int firstParcelIndex = 0;

        for (Parcel parcel : installment.getListOfParcel()) {
            totalParcel = totalParcel.add(parcel.getValue());
        }

        BigDecimal rest = installment.getTotal().subtract(totalParcel);

        Parcel lastParcel = installment.getListOfParcel().get(firstParcelIndex);

        installment.getListOfParcel().set(firstParcelIndex, new Parcel(lastParcel.getValue().add(rest), lastParcel.getDueDate()));
    }
}
