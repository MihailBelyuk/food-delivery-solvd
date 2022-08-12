package com.solvd.fooddelivery.entity.vehicle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Vehicle implements IRepair, IDoService {

    private static final Logger LOGGER = LogManager.getLogger(Vehicle.class);

    private String brand;
    private int maxSpeed;
    private boolean needsRepair;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    @Override
    public void checkIfNeedsRepair() {
        if (isNeedsRepair()) {
            LOGGER.info("Repair is NEEDED.");
        } else {
            LOGGER.info("Repair is NOT needed.");
        }
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isNeedsRepair() {
        return needsRepair;
    }

    public void setNeedsRepair(boolean needsRepair) {
        this.needsRepair = needsRepair;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (maxSpeed != vehicle.maxSpeed) return false;
        return brand != null ? brand.equals(vehicle.brand) : vehicle.brand == null;
    }

    @Override
    public int hashCode() {
        int result = maxSpeed;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vehicle{");
        sb.append("maxSpeed=").append(maxSpeed);
        sb.append(", brand='").append(brand).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
