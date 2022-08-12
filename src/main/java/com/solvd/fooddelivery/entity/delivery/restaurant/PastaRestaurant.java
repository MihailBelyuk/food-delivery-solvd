package com.solvd.fooddelivery.entity.delivery.restaurant;

import com.solvd.fooddelivery.entity.delivery.restaurant.food.Dish;

import java.util.List;

public class PastaRestaurant extends Restaurant {

    public PastaRestaurant(String restaurantName, List<Dish> dishes) {
        super(restaurantName, dishes);
    }
}
