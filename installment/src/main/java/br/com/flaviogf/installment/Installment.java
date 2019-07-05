package br.com.flaviogf.installment;

import java.math.BigDecimal;
import java.util.List;

public class Installment {

    private ParcelStrategy strategy;

    public Installment(ParcelStrategy strategy) {
        this.strategy = strategy;
    }

    public int getNumberOfPlots() {
        return strategy.getListOfParcel().size();
    }

    public List<Parcel> getListOfPlots() {
        return strategy.getListOfParcel();
    }

    public BigDecimal getTotal() {
        return strategy.getTotal();
    }
}
