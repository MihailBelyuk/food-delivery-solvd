package com.solvd.fooddelivery.entity.delivery;

import com.solvd.fooddelivery.entity.Address;
import com.solvd.fooddelivery.entity.delivery.order.Order;
import com.solvd.fooddelivery.entity.delivery.order.Payment;
import com.solvd.fooddelivery.entity.delivery.restaurant.Restaurant;
import com.solvd.fooddelivery.entity.person.Courier;
import com.solvd.fooddelivery.entity.person.Director;

import java.util.List;
import java.util.Map;

public class Delivery implements ICountQuantity {

    private Map<String, Restaurant> restaurants;
    private List<Courier> couriers;
    private List<Payment> payments;
    private List<Order> orders;
    private Address address;
    private Director director;

    @Override
    public int countQuantity() {
        return couriers.size();
    }

    public Map<String, Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Map<String, Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public List<Courier> getCouriers() {
        return couriers;
    }

    public void setCouriers(List<Courier> couriers) {
        this.couriers = couriers;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Delivery delivery = (Delivery) o;

        if (restaurants != null ? !restaurants.equals(delivery.restaurants) : delivery.restaurants != null)
            return false;
        if (couriers != null ? !couriers.equals(delivery.couriers) : delivery.couriers != null) return false;
        if (payments != null ? !payments.equals(delivery.payments) : delivery.payments != null) return false;
        if (orders != null ? !orders.equals(delivery.orders) : delivery.orders != null) return false;
        if (address != null ? !address.equals(delivery.address) : delivery.address != null) return false;
        return director != null ? director.equals(delivery.director) : delivery.director == null;
    }

    @Override
    public int hashCode() {
        int result = restaurants != null ? restaurants.hashCode() : 0;
        result = 31 * result + (couriers != null ? couriers.hashCode() : 0);
        result = 31 * result + (payments != null ? payments.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Delivery{");
        sb.append("restaurants=").append(restaurants);
        sb.append(", couriers=").append(couriers);
        sb.append(", payments=").append(payments);
        sb.append(", orders=").append(orders);
        sb.append(", address=").append(address);
        sb.append(", director=").append(director);
        sb.append('}');
        return sb.toString();
    }
}
