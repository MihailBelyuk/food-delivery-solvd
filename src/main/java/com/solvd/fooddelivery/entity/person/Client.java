package com.solvd.fooddelivery.entity.person;

import com.solvd.fooddelivery.entity.vehicle.CivilVehicle;

import java.time.LocalDate;

public class Client extends Human<CivilVehicle> {

    private long id;

    public Client(String name, LocalDate dateOfBirth) {
        super(name, dateOfBirth);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Client client = (Client) o;

        return id == client.id;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
