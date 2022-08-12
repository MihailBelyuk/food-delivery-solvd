package com.solvd.fooddelivery.entity.delivery.restaurant;

import com.solvd.fooddelivery.entity.delivery.ICountQuantity;
import com.solvd.fooddelivery.entity.delivery.restaurant.food.Dish;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Restaurant implements ICountQuantity {

    private static final Logger LOGGER = LogManager.getLogger(Restaurant.class);

    private List<Dish> dishes;
    private String restaurantName;

    public Restaurant(String restaurantName, List<Dish> dishes) {
        this.restaurantName = restaurantName;
        this.dishes = dishes;
    }

    @Override
    public int countQuantity() {
        LOGGER.info("Amount of dishes: " + this.dishes.size());
        return this.dishes.size();
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant that = (Restaurant) o;

        if (restaurantName != null ? !restaurantName.equals(that.restaurantName) : that.restaurantName != null)
            return false;
        return dishes != null ? dishes.equals(that.dishes) : that.dishes == null;
    }

    @Override
    public int hashCode() {
        int result = restaurantName != null ? restaurantName.hashCode() : 0;
        result = 31 * result + (dishes != null ? dishes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Restaurant{");
        sb.append("restaurantName='").append(restaurantName).append('\'');
        sb.append(", dishes=").append(dishes);
        sb.append('}');
        return sb.toString();
    }
}
