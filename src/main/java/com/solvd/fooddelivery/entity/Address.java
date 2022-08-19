package com.solvd.fooddelivery.entity;

import com.solvd.fooddelivery.service.District;

public class Address {

    private String city;
    private District district;
    private String street;
    private String houseNumber;
    private int flatNumber;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (flatNumber != address.flatNumber) return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (district != null ? !district.equals(address.district) : address.district != null) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        return houseNumber != null ? houseNumber.equals(address.houseNumber) : address.houseNumber == null;
    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (houseNumber != null ? houseNumber.hashCode() : 0);
        result = 31 * result + flatNumber;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("city='").append(city).append('\'');
        sb.append(", district='").append(district).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", houseNumber='").append(houseNumber).append('\'');
        sb.append(", flatNumber=").append(flatNumber);
        sb.append('}');
        return sb.toString();
    }
}
