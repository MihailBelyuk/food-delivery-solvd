package com.solvd.fooddelivery.entity.delivery.restaurant.food;

public class Drink extends Food implements IPrepare<Food> {

    public Drink(String name, Integer prepareTimeMinutes) {
        super(name, prepareTimeMinutes);
    }

    @Override
    public void prepare(Food food) {
    }
}
