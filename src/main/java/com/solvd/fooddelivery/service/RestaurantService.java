package com.solvd.fooddelivery.service;

import com.solvd.fooddelivery.entity.delivery.Delivery;
import com.solvd.fooddelivery.entity.delivery.ICountQuantity;
import com.solvd.fooddelivery.entity.delivery.restaurant.Restaurant;
import com.solvd.fooddelivery.entity.delivery.restaurant.food.ICook;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RestaurantService {

    public static int countDishes(ICountQuantity iCountQuantity) {
        return iCountQuantity.countQuantity();
    }

    public static void prepareDish(ICook iCook) {
        iCook.cook();
    }

    public static List<Restaurant> checkDishQuantity(Predicate<Restaurant> predicate, Delivery delivery) {
        return delivery.getRestaurants().values().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
