package com.solvd.fooddelivery.entity.person;

import com.solvd.fooddelivery.entity.vehicle.Car;

import java.time.LocalDate;

public class Courier extends Employee {

    private DeliveryType deliveryType;
    private Car car;

    public Courier(String name, LocalDate dateOfBirth) {
        super(name, dateOfBirth);
    }

    public enum DeliveryType {
        CAR("car"),
        BICYCLE("bicycle"),
        ON_FOOT("on foot");

        private final String displayName;

        DeliveryType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Courier courier = (Courier) o;

        if (deliveryType != null ? !deliveryType.equals(courier.deliveryType) : courier.deliveryType != null)
            return false;
        return car != null ? car.equals(courier.car) : courier.car == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (deliveryType != null ? deliveryType.hashCode() : 0);
        result = 31 * result + (car != null ? car.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Courier{");
        sb.append("courierType='").append(deliveryType).append('\'');
        sb.append(", car=").append(car);
        sb.append('}');
        return sb.toString();
    }
}

