package br.com.flaviogf.installment;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ParcelThirtyDaysStrategy implements ParcelStrategy {

    private static final int DAYS_UNTIL_THE_NEXT_PARCEL = 30;

    private final int numberOfPlots;

    private BigDecimal total;

    private List<Parcel> listOfPlots;

    public ParcelThirtyDaysStrategy(BigDecimal total, int numberOfPlots) {
        this.total = total;
        this.numberOfPlots = numberOfPlots;
    }

    @Override
    public List<Parcel> getListOfParcel() {
        listOfPlots = new ArrayList<>();
        divideParcels();
        calculateFirstValue();
        return listOfPlots;
    }

    private void divideParcels() {
        DateTime firstParcelDueDate = new DateTime();

        for (int i = 0; i < numberOfPlots; i++) {
            int nextDay = DAYS_UNTIL_THE_NEXT_PARCEL * i;

            BigDecimal parcelValue = total.divide(new BigDecimal(numberOfPlots), 2, BigDecimal.ROUND_FLOOR);

            DateTime parcelDueDate = firstParcelDueDate.plusDays(nextDay);

            Parcel parcel = new Parcel(parcelValue, parcelDueDate);

            listOfPlots.add(parcel);
        }
    }

    private void calculateFirstValue() {
        BigDecimal totalParcel = new BigDecimal(0);

        int firstParcelIndex = 0;

        for (Parcel parcel : listOfPlots) {
            totalParcel = totalParcel.add(parcel.getValue());
        }

        BigDecimal rest = getTotal().subtract(totalParcel);

        Parcel lastParcel = listOfPlots.get(firstParcelIndex);

        listOfPlots.set(firstParcelIndex, new Parcel(lastParcel.getValue().add(rest), lastParcel.getDueDate()));
    }

    @Override
    public BigDecimal getTotal() {
        return this.total;
    }
}
