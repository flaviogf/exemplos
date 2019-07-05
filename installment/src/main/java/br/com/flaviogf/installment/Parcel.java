package br.com.flaviogf.installment;

import org.joda.time.DateTime;

import java.math.BigDecimal;

public class Parcel {

    private BigDecimal value;

    private DateTime dueDate;

    public Parcel(BigDecimal value) {
        this.value = value;
        this.dueDate = new DateTime();
    }

    public Parcel(BigDecimal value, DateTime dueDate) {
        this.value = value;
        this.dueDate = dueDate;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public DateTime getDueDate() {
        return this.dueDate;
    }
}
