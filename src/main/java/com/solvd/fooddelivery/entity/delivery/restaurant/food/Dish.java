package com.solvd.fooddelivery.entity.delivery.restaurant.food;

import com.solvd.fooddelivery.entity.delivery.restaurant.food.ingredient.Ingredient;
import com.solvd.fooddelivery.exception.NegativeQuantityException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Dish extends Food implements ICook {

    private static final Logger LOGGER = LogManager.getLogger(Dish.class);

    private int dishQuantity;
    private boolean spicy;

    public Dish(String name, Integer prepareTimeMinutes) {
        super(name, prepareTimeMinutes);
    }

    @Override
    public void cook() {
        List<Ingredient> ingredients = getIngredients();
        if (!ingredients.iterator().next().isPresent()) {
            LOGGER.info("There is/are no " + ingredients.iterator().next().getName() + " to cook the " + getName());
        } else {
            LOGGER.info(getDishQuantity() + " " + getName() + " is/are cooked.");
        }
    }

    @Override
    public void prepare(Food food) {

    }


    public int getDishQuantity() {
        return dishQuantity;
    }

    public void setDishQuantity(int dishQuantity) {
        if (dishQuantity < 0) {
            LOGGER.error("Negative dish quantity value.");
            throw new NegativeQuantityException("Negative dish quantity value.");
        }
        this.dishQuantity = dishQuantity;
    }

    public boolean isSpicy() {
        return spicy;
    }

    public void setSpicy(boolean spicy) {
        this.spicy = spicy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Dish dish = (Dish) o;

        if (dishQuantity != dish.dishQuantity) return false;
        return spicy == dish.spicy;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + dishQuantity;
        result = 31 * result + (spicy ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dish{");
        sb.append("dishQuantity=").append(dishQuantity);
        sb.append(", spicy=").append(spicy);
        sb.append('}');
        return sb.toString();
    }
}
