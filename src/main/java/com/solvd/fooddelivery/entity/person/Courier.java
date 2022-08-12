package com.solvd.fooddelivery.entity.person;

import com.solvd.fooddelivery.entity.vehicle.Car;

import java.time.LocalDate;

public class Courier extends Employee {

    private CourierType courierType;
    private Car car;

    public Courier(String name, LocalDate dateOfBirth) {
        super(name, dateOfBirth);
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public CourierType getCourierType() {
        return courierType;
    }

    public void setCourierType(CourierType courierType) {
        this.courierType = courierType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Courier courier = (Courier) o;

        if (courierType != null ? !courierType.equals(courier.courierType) : courier.courierType != null) return false;
        return car != null ? car.equals(courier.car) : courier.car == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (courierType != null ? courierType.hashCode() : 0);
        result = 31 * result + (car != null ? car.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Courier{");
        sb.append("courierType='").append(courierType).append('\'');
        sb.append(", car=").append(car);
        sb.append('}');
        return sb.toString();
    }
}

