package com.solvd.fooddelivery.entity.vehicle;

import com.solvd.fooddelivery.exception.TooBigValueException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bicycle extends CivilVehicle {

    private static final Logger LOGGER = LogManager.getLogger(Bicycle.class);

    private String size;

    @Override
    public void replace(SparePart sparePart) {
        LOGGER.info(sparePart.getClass().getName() + " " + sparePart.getBrand() + " has been replaced.");
    }

    @Override
    public void change() throws TooBigValueException {

    }

    public Bicycle(String brand) {
        super(brand);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bicycle bicycle = (Bicycle) o;

        return size != null ? size.equals(bicycle.size) : bicycle.size == null;
    }

    @Override
    public int hashCode() {
        return size != null ? size.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bicycle{");
        sb.append("size='").append(size).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
