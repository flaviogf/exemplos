package br.com.flaviogf.installment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Installment {

    private BigDecimal total;

    private int numberOfParcel;

    private List<Parcel> listOfParcel;

    private ParcelStrategy strategy;

    private InstallmentChangeListener installmentChangeListener;

    public Installment(BigDecimal total, int numberOfParcel) {
        this.total = total;
        this.numberOfParcel = numberOfParcel;
        this.listOfParcel = new ArrayList<>();
        this.strategy = new ParcelRedistributeTotal();

        this.strategy.redistribute(this);
    }

    public Installment(List<Parcel> listOfParcel) {
        this.total = BigDecimal.ZERO;
        this.numberOfParcel = listOfParcel.size();
        this.listOfParcel = listOfParcel;
        this.strategy = new ParcelRedistributeTotal();

        calculateTotal();
        calculateRest();
    }

    public BigDecimal getTotal() {
        return total;
    }

    public int getNumberOfParcel() {
        return numberOfParcel;
    }

    public void setNumberOfParcel(int numberOfParcel, ParcelStrategy strategy) {
        this.numberOfParcel = numberOfParcel;

        strategy.redistribute(this);

        onInstallmentChange();
    }

    public List<Parcel> getListOfParcel() {
        return listOfParcel;
    }

    public void setInstallmentChangeListener(InstallmentChangeListener installmentChangeListener) {
        this.installmentChangeListener = installmentChangeListener;
    }

    public void updateParcelValue(int index, BigDecimal value, ParcelStrategy parcelStrategy) {
        try {
            Parcel oldParcel = listOfParcel.get(index);
            Parcel newParcel = new Parcel(value, oldParcel.getDueDate());

            listOfParcel.set(index, newParcel);

            parcelStrategy.redistribute(this);

            onInstallmentChange();
        } catch (IndexOutOfBoundsException ignore) {
        }
    }

    public void calculateRest() {
        BigDecimal amountPaid = BigDecimal.ZERO;

        final int firstParcelIndex = 0;

        for (Parcel parcel : getListOfParcel()) {
            amountPaid = amountPaid.add(parcel.getValue());
        }

        final BigDecimal rest = getTotal().subtract(amountPaid).setScale(2, RoundingMode.HALF_UP);

        final Parcel firstParcel = getListOfParcel().get(firstParcelIndex);

        if (rest.compareTo(BigDecimal.ZERO) > 0) {
            getListOfParcel().set(firstParcelIndex, new Parcel(firstParcel.getValue().add(rest), firstParcel.getDueDate()));
        } else {
            getListOfParcel().set(firstParcelIndex, new Parcel(firstParcel.getValue().subtract(rest), firstParcel.getDueDate()));
        }
    }

    private void calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (Parcel parcel : listOfParcel) {
            total = total.add(parcel.getValue());
        }

        this.total = total;
    }

    private void onInstallmentChange() {
        if (installmentChangeListener == null) {
            return;
        }

        installmentChangeListener.onInstallmentChange();
    }
}
