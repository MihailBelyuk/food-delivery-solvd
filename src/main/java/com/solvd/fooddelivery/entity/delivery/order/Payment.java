package com.solvd.fooddelivery.entity.delivery.order;

public class Payment {

    private String paymentType;

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        return paymentType != null ? paymentType.equals(payment.paymentType) : payment.paymentType == null;
    }

    @Override
    public int hashCode() {
        return paymentType != null ? paymentType.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Payment{");
        sb.append("paymentType='").append(paymentType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
