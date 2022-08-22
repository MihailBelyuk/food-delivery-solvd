package com.solvd.fooddelivery.service;

import com.solvd.fooddelivery.entity.delivery.Delivery;
import com.solvd.fooddelivery.entity.delivery.ICountQuantity;
import com.solvd.fooddelivery.entity.delivery.order.Order;
import com.solvd.fooddelivery.entity.delivery.restaurant.food.Dish;
import com.solvd.fooddelivery.entity.person.Client;
import com.solvd.fooddelivery.entity.person.Courier;
import com.solvd.fooddelivery.entity.person.Human;
import com.solvd.fooddelivery.entity.vehicle.CivilVehicle;
import com.solvd.fooddelivery.exception.EmptyOrderException;
import com.solvd.fooddelivery.exception.NegativeQuantityException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class DeliveryService {

    private static final Logger LOGGER = LogManager.getLogger(DeliveryService.class);

    private static final int MINUTES_IN_HOUR = 60;

    public static double countDeliveryTime(Delivery delivery) {
        List<Order> orders = delivery.getOrders();
        Order order = orders.get(0);
        Courier courier = order.getCourier();
        Client client = order.getClient();
        int courierSpeed = CourierService.adjustCourierSpeed(courier.getDeliveryType());
        double clientDistance = OrderService.showDeliveryDistance(client.getAddress());
        List<Dish> orderDishes = order.getDishes();
        int orderPrepareTime = orderDishes.stream()
                .mapToInt(dish -> dish.getDishQuantity() * dish.getDishQuantity())
                .sum();
        if (orderPrepareTime == 0) {
            LOGGER.info("Failed to count delivery time, because there are no items in the order.");
            throw new EmptyOrderException("Failed to count delivery time, because there are no items in the order.");
        }
        return clientDistance / courierSpeed * MINUTES_IN_HOUR + orderPrepareTime;
    }

    public static BigDecimal countOrderPrice(Delivery delivery) {
        List<Order> orders = delivery.getOrders();
        Order order = orders.get(0);
        BigDecimal orderPrice = new BigDecimal(0);
        List<Dish> dishes = order.getDishes();
        dishes.forEach(dish -> dish.getPrice().multiply(new BigDecimal(dish.getDishQuantity()))); // TODO: 8/22/2022  
        for (Dish dish : order.getDishes()) {
            BigDecimal dishSetPrice = dish.getPrice().multiply(new BigDecimal(dish.getDishQuantity()));
            orderPrice = orderPrice.add(dishSetPrice);
        }
        return orderPrice.setScale(2, RoundingMode.CEILING);
    }

    public static void showPersonInfo(Human<CivilVehicle> human) {
        LOGGER.info(human.getName() + " " + human.getDateOfBirth().toString());
    }

    public static int countCourierQuantity(ICountQuantity iCountQuantity) throws NegativeQuantityException {
        if (iCountQuantity.countQuantity() < 0) {
            LOGGER.error("Negative quantity value caught.");
            throw new NegativeQuantityException("Negative quantity value caught.");
        }
        return iCountQuantity.countQuantity();
    }
}
