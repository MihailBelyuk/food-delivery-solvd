package com.solvd.fooddelivery.entity.delivery.restaurant.food;

public class Drink extends Food implements IPrepare<Food> {

    public Drink(String name, Integer prepareTimeMinutes) {
        super(name, prepareTimeMinutes);
    }

    @Override
    public void prepare(Food food) {
    }

    public enum DrinkType {
        WATER("water"),
        COFFEE("coffee"),
        TEA("tea"),
        JUICE("juice"),
        BEER("beer");

        private final String drinkCategory;

        DrinkType(String drinkCategory) {
            this.drinkCategory = drinkCategory;
        }

        public String getDrinkCategory() {
            return drinkCategory;
        }
    }
}
