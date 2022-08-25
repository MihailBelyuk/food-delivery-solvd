package com.solvd.fooddelivery.entity.delivery.order;

import com.solvd.fooddelivery.entity.delivery.restaurant.food.Dish;
import com.solvd.fooddelivery.entity.person.Client;
import com.solvd.fooddelivery.entity.person.Courier;
import com.solvd.fooddelivery.exception.NegativePriceValueException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class GeneralOrder extends Order {

    private static final Logger LOGGER = LogManager.getLogger(GeneralOrder.class);

    public GeneralOrder(Courier courier, Client client, int deliveryDistance) {
        super(courier, client, deliveryDistance);
    }

    @Override
    public BigDecimal countOrderPriceWithDiscount(List<Dish> dishes) {
        boolean priceCompare = dishes.stream()
                .anyMatch(dish -> dish.getPrice().compareTo(BigDecimal.ZERO) < 0);
        if (priceCompare) {
            LOGGER.error("Negative price value is present.");
            throw new NegativePriceValueException("Negative price value is present.");
        }
        BigDecimal discount = new BigDecimal(getDiscount()).divide(new BigDecimal(100));
        BigDecimal orderPrice = dishes.stream()
                .map(dish -> dish.getPrice().multiply(new BigDecimal(dish.getDishQuantity())))
                .reduce(BigDecimal::add)
                .orElseThrow();
        return orderPrice.subtract(orderPrice.multiply(discount)).setScale(2, RoundingMode.CEILING);
    }
}
