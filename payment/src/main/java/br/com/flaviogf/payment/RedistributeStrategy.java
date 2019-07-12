package br.com.flaviogf.payment;

public interface RedistributeStrategy {

    void redistribute(Payment payment);
}
