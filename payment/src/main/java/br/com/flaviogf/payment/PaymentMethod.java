package br.com.flaviogf.payment;

import java.math.BigDecimal;

public class PaymentMethod {

    private final String description;

    private final BigDecimal value;

    public PaymentMethod(String description, BigDecimal value) {
        this.description = description;
        this.value = value;
    }

    public PaymentMethod(String description) {
        this.description = description;
        this.value = BigDecimal.ZERO;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return this.value;
    }
}
