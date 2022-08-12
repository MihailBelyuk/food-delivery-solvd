package com.solvd.fooddelivery.service;

import com.solvd.fooddelivery.entity.delivery.order.Payment;
import com.solvd.fooddelivery.entity.delivery.restaurant.FastFoodRestaurant;
import com.solvd.fooddelivery.entity.delivery.restaurant.Restaurant;
import com.solvd.fooddelivery.entity.delivery.restaurant.food.Dish;
import com.solvd.fooddelivery.entity.delivery.restaurant.food.FriedChicken;
import com.solvd.fooddelivery.entity.delivery.restaurant.food.Hamburger;
import com.solvd.fooddelivery.entity.delivery.restaurant.food.Pizza;
import com.solvd.fooddelivery.entity.delivery.restaurant.food.ingredient.*;
import com.solvd.fooddelivery.entity.person.Courier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.solvd.fooddelivery.entity.person.CourierType.*;
import static java.time.Month.*;

public class Creator {

    public static List<Courier> createCouriers() {
        List<Courier> couriers = new ArrayList<>();
        Courier andrey = new Courier("Andrey", LocalDate.of(1990, JANUARY, 2));
        andrey.setCourierType(CAR);
        Courier vasia = new Courier("Vasia", LocalDate.of(1991, MARCH, 3));
        vasia.setCourierType(ON_FOOT);
        Courier petya = new Courier("Petya", LocalDate.of(1992, APRIL, 4));
        petya.setCourierType(BICYCLE);
        couriers.add(andrey);
        couriers.add(vasia);
        couriers.add(petya);
        return couriers;
    }

    public static List<Payment> createPaymentTypes() {
        List<Payment> payments = new ArrayList<>();
        Payment cash = new Payment();
        cash.setPaymentType("cash");
        Payment creditCard = new Payment();
        creditCard.setPaymentType("credit card");
        payments.add(cash);
        payments.add(creditCard);
        return payments;
    }

    public static Map<String, List<Ingredient>> createIngredients() {
        Map<String, List<Ingredient>> dishIngredients = new HashMap<>();

        Salt salt = new Salt("salt");
        Cheese mozzarella = new Cheese("mozzarella");
        Dough pizzaDough = new Dough("dr.oetker");
        Ketchup heinz = new Ketchup("heinz");
        Sausage pepperoni = new Sausage("pepperoni");
        ChickenMeat chickenBreast = new ChickenMeat("petruha chicken breast");
        ChickenWings chickenWings = new ChickenWings("petruha chicken wings");
        ChilliPepper habanero = new ChilliPepper("habanero");
        Oil goldenSeed = new Oil("zlata");
        Bun bun = new Bun("hamburger bun");
        Beef aberdeen = new Beef("aberdeen angus beef");

        List<Ingredient> pizzaPepperoni = new ArrayList<>();
        pizzaPepperoni.add(mozzarella);
        pizzaPepperoni.add(pizzaDough);
        pizzaPepperoni.add(pepperoni);
        pizzaPepperoni.add(salt);
        pizzaPepperoni.add(heinz);
        pizzaPepperoni.add(goldenSeed);

        List<Ingredient> pizzaBbq = new ArrayList<>();
        pizzaBbq.add(mozzarella);
        pizzaBbq.add(pizzaDough);
        pizzaBbq.add(salt);
        pizzaBbq.add(heinz);
        pizzaPepperoni.add(salt);
        pizzaBbq.add(goldenSeed);
        pizzaBbq.add(aberdeen);

        List<Ingredient> nuggets = new ArrayList<>();
        nuggets.add(salt);
        nuggets.add(chickenBreast);
        nuggets.add(goldenSeed);

        List<Ingredient> hotWings = new ArrayList<>();
        hotWings.add(salt);
        hotWings.add(chickenWings);
        hotWings.add(habanero);
        hotWings.add(goldenSeed);

        List<Ingredient> strips = new ArrayList<>();
        strips.add(salt);
        strips.add(chickenBreast);
        strips.add(goldenSeed);

        List<Ingredient> whopper = new ArrayList<>();
        whopper.add(bun);
        whopper.add(goldenSeed);
        whopper.add(salt);
        whopper.add(aberdeen);

        List<Ingredient> bigKing = new ArrayList<>();
        bigKing.add(bun);
        bigKing.add(goldenSeed);
        bigKing.add(salt);
        bigKing.add(aberdeen);

        dishIngredients.put("pizza pepperoni", pizzaPepperoni);
        dishIngredients.put("pizza bbq", pizzaBbq);
        dishIngredients.put("nuggets", nuggets);
        dishIngredients.put("hot wings", hotWings);
        dishIngredients.put("strips", strips);
        dishIngredients.put("big king", bigKing);
        dishIngredients.put("whopper", whopper);
        return dishIngredients;
    }

    public static Map<String, List<Dish>> createDishes(Map<String, List<Ingredient>> dishIngredients) {
        Map<String, List<Dish>> restaurantDishes = new HashMap<>();

        List<Dish> pizzaHutDishes = new ArrayList<>();
        Dish pepperoniPizza = new Pizza("Pepperoni", 15);
        pepperoniPizza.setIngredients(dishIngredients.get("pizza pepperoni"));
        pepperoniPizza.setPrice(new BigDecimal(10));
        Dish bbqPizza = new Pizza("BBQ Pizza", 20);
        bbqPizza.setIngredients(dishIngredients.get("pizza bbq"));
        bbqPizza.setPrice(new BigDecimal(9));
        pizzaHutDishes.add(pepperoniPizza);
        pizzaHutDishes.add(bbqPizza);

        List<Dish> kfcDishes = new ArrayList<>();
        Dish hotWings = new FriedChicken("Hot wings", 4);
        hotWings.setIngredients(dishIngredients.get("hot wings"));
        hotWings.setPrice(new BigDecimal(7));
        hotWings.setSpicy(true);
        Dish nuggets = new FriedChicken("Nuggets", 8);
        nuggets.setIngredients(dishIngredients.get("nuggets"));
        nuggets.setPrice(new BigDecimal(9));
        Dish strips = new FriedChicken("Strips", 5);
        strips.setIngredients(dishIngredients.get("strips"));
        strips.setPrice(new BigDecimal(6));
        kfcDishes.add(hotWings);
        kfcDishes.add(nuggets);
        kfcDishes.add(strips);

        List<Dish> burgerKingDishes = new ArrayList<>();
        Dish whopper = new Hamburger("Whopper", 12);
        whopper.setIngredients(dishIngredients.get("whopper"));
        whopper.setPrice(new BigDecimal(10));
        Dish bigKing = new Hamburger("Big King", 11);
        bigKing.setIngredients(dishIngredients.get("big king"));
        bigKing.setPrice(new BigDecimal(9));
        burgerKingDishes.add(whopper);
        burgerKingDishes.add(bigKing);

        restaurantDishes.put("pizza hut dishes", pizzaHutDishes);
        restaurantDishes.put("kfc dishes", kfcDishes);
        restaurantDishes.put("burger king dishes", burgerKingDishes);
        return restaurantDishes;
    }

    public static Map<String, Restaurant> createFastFoodRestaurants(Map<String, List<Dish>> restaurantDishes) {
        Map<String, Restaurant> restaurants = new HashMap<>();

        List<Dish> pizzaHutDishes = restaurantDishes.get("pizza hut dishes");
        Restaurant pizzaHut = new FastFoodRestaurant("Pizza Hut", pizzaHutDishes);
        pizzaHut.setDishes(pizzaHutDishes);

        List<Dish> kfcDishes = restaurantDishes.get("kfc dishes");
        Restaurant kfc = new Restaurant("KFC", kfcDishes);
        kfc.setDishes(kfcDishes);

        List<Dish> burgerKingDishes = restaurantDishes.get("burger king dishes");
        Restaurant burgerKing = new FastFoodRestaurant("Burger King", burgerKingDishes);
        burgerKing.setDishes(burgerKingDishes);

        restaurants.put("pizza hut", pizzaHut);
        restaurants.put("kfc", kfc);
        restaurants.put("burger king", burgerKing);
        return restaurants;

    }
}
