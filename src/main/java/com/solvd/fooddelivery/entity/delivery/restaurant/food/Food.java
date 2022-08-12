package com.solvd.fooddelivery.entity.delivery.restaurant.food;

import com.solvd.fooddelivery.entity.delivery.restaurant.food.ingredient.Ingredient;

import java.math.BigDecimal;
import java.util.List;

public abstract class Food {

    private List<Ingredient> ingredients;
    private Integer prepareTimeMinutes;
    private BigDecimal price;
    private String name;

    public Food(String name, Integer prepareTimeMinutes) {
        this.name = name;
        this.prepareTimeMinutes = prepareTimeMinutes;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrepareTimeMinutes() {
        return prepareTimeMinutes;
    }

    public void setPrepareTimeMinutes(Integer prepareTimeMinutes) {
        this.prepareTimeMinutes = prepareTimeMinutes;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (ingredients != null ? !ingredients.equals(food.ingredients) : food.ingredients != null) return false;
        if (name != null ? !name.equals(food.name) : food.name != null) return false;
        if (prepareTimeMinutes != null ? !prepareTimeMinutes.equals(food.prepareTimeMinutes) : food.prepareTimeMinutes != null)
            return false;
        return price != null ? price.equals(food.price) : food.price == null;
    }

    @Override
    public int hashCode() {
        int result = ingredients != null ? ingredients.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (prepareTimeMinutes != null ? prepareTimeMinutes.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Food{");
        sb.append("ingredients=").append(ingredients);
        sb.append(", name='").append(name).append('\'');
        sb.append(", prepareTimeMinutes=").append(prepareTimeMinutes);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
