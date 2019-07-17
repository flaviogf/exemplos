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
    }

    private void divideParcels() {
        installment.getListOfParcel().clear();

        DateTime firstParcelDueDate = new DateTime();

        for (int i = 0; i < installment.getNumberOfParcel(); i++) {
            int nextDay = DAYS_UNTIL_THE_NEXT_PARCEL * i;

            BigDecimal parcelValue = installment.getTotal().divide(new BigDecimal(installment.getNumberOfParcel()), 2, RoundingMode.HALF_UP);

            DateTime parcelDueDate = firstParcelDueDate.plusDays(nextDay);

            Parcel parcel = new Parcel(parcelValue, parcelDueDate);

            installment.getListOfParcel().add(parcel);
        }

        installment.calculateRest();
    }
}
