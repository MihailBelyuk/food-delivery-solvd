package com.solvd.fooddelivery.entity.vehicle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Vehicle implements IRepair, IDoService {

    private static final Logger LOGGER = LogManager.getLogger(Vehicle.class);

    private FuelType fuelType;
    private String brand;
    private int maxSpeed;
    private boolean needsRepair;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    public enum FuelType {
        DIESEL("diesel"),
        GASOLINE("gasoline"),
        GAS("gas"),
        KEROSENE("kerosene");

        private final String fuel;

        FuelType(String fuelType) {
            this.fuel = fuelType;
        }

        public String getFuel() {
            return fuel;
        }
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

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (maxSpeed != vehicle.maxSpeed) return false;
        if (needsRepair != vehicle.needsRepair) return false;
        if (fuelType != vehicle.fuelType) return false;
        return brand != null ? brand.equals(vehicle.brand) : vehicle.brand == null;
    }

    @Override
    public int hashCode() {
        int result = fuelType != null ? fuelType.hashCode() : 0;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + maxSpeed;
        result = 31 * result + (needsRepair ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vehicle{");
        sb.append("fuelType=").append(fuelType);
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", maxSpeed=").append(maxSpeed);
        sb.append(", needsRepair=").append(needsRepair);
        sb.append('}');
        return sb.toString();
    }
}
