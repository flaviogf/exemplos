package br.com.flaviogf.installment;

public class ParcelRedistributeTotalExceptIndex implements ParcelStrategy {

    private final int index;

    private Installment installment;

    public ParcelRedistributeTotalExceptIndex(int index) {
        this.index = index;
    }

    @Override
    public void redistribute(Installment installment) {
        this.installment = installment;
    }
}
