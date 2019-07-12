package br.com.flaviogf.installment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Installment {

    private final BigDecimal total;

    private int numberOfParcel;

    private InstallmentChangeListener installmentChangeListener;

    private ParcelStrategy strategy;

    private List<Parcel> listOfParcel;

    public Installment(BigDecimal total, int numberOfParcel) {
        this.total = total;
        this.numberOfParcel = numberOfParcel;
        this.strategy = new ParcelRedistributeTotal();
        this.listOfParcel = new ArrayList<>();

        this.strategy.redistribute(this);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<Parcel> getListOfParcel() {
        return listOfParcel;
    }

    public void setNumberOfParcel(int numberOfParcel) {
        this.numberOfParcel = numberOfParcel;

        listOfParcel.clear();

        strategy.redistribute(this);

        onInstallmentChange();
    }

    public void setInstallmentChangeListener(InstallmentChangeListener installmentChangeListener) {
        this.installmentChangeListener = installmentChangeListener;
    }

    public int getNumberOfParcel() {
        return numberOfParcel;
    }

    public void updateParcelValue(int index, BigDecimal value, ParcelStrategy parcelStrategy) {
        Parcel oldParcel = listOfParcel.get(index);
        Parcel newParcel = new Parcel(value, oldParcel.getDueDate());

        listOfParcel.set(index, newParcel);

        parcelStrategy.redistribute(this);

        onInstallmentChange();
    }

    private void onInstallmentChange() {
        if (installmentChangeListener == null) {
            return;
        }

        installmentChangeListener.onInstallmentChange();
    }
}
