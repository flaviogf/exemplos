package br.com.flaviogf.installment;

import java.math.BigDecimal;
import java.util.List;

public interface ParcelStrategy {

    List<Parcel> getListOfParcel();

    BigDecimal getTotal();
}
