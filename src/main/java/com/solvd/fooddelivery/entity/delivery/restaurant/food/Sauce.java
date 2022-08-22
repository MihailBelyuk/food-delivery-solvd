package com.solvd.fooddelivery.entity.delivery.restaurant.food;

public class Sauce extends Food implements IPrepare<Food> {

    public Sauce(String name, Integer prepareTimeMinutes) {
        super(name, prepareTimeMinutes);
    }

    @Override
    public void prepare(Food food) {
    }
}
