package com.solvd.fooddelivery.entity.vehicle;

public abstract class SparePart {

    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SparePart sparePart = (SparePart) o;

        return brand != null ? brand.equals(sparePart.brand) : sparePart.brand == null;
    }

    @Override
    public int hashCode() {
        return brand != null ? brand.hashCode() : 0;
    }
}
