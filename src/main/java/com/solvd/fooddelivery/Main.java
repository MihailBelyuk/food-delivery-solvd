package com.solvd.fooddelivery;

import com.solvd.fooddelivery.entity.Address;
import com.solvd.fooddelivery.entity.delivery.Delivery;
import com.solvd.fooddelivery.entity.delivery.order.GeneralOrder;
import com.solvd.fooddelivery.entity.delivery.order.Order;
import com.solvd.fooddelivery.entity.delivery.restaurant.Restaurant;
import com.solvd.fooddelivery.entity.delivery.restaurant.food.Dish;
import com.solvd.fooddelivery.entity.delivery.restaurant.food.ingredient.Ingredient;
import com.solvd.fooddelivery.entity.person.Client;
import com.solvd.fooddelivery.entity.person.Courier;
import com.solvd.fooddelivery.entity.person.Employee;
import com.solvd.fooddelivery.entity.vehicle.Car;
import com.solvd.fooddelivery.entity.vehicle.WvGolf;
import com.solvd.fooddelivery.exception.TooBigValueException;
import com.solvd.fooddelivery.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.*;

public class Main {

    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }

    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Delivery delivery = new Delivery();

        Map<String, List<Ingredient>> ingredients = Creator.createIngredients();
        for (Map.Entry<String, List<Ingredient>> entry : ingredients.entrySet()) {
            List<Ingredient> ingredientsList = entry.getValue();
            for (Ingredient ingredient : ingredientsList) {
                ingredient.setPresent(true);
            }
        }

        Map<String, List<Dish>> dishes = Creator.createDishes(ingredients);
        delivery.setRestaurants(Creator.createFastFoodRestaurants(dishes));
        delivery.setCouriers(Creator.createCouriers());

        Map<String, Restaurant> restaurantMap = delivery.getRestaurants();
        LOGGER.info("Show entry set: " + restaurantMap.entrySet());
        LOGGER.info("Show map keys: " + restaurantMap.keySet());

        Car lancer = new Car("Mitsubishi Lancer EVO X", 15_000);
        VehicleService.replaceWheel(lancer);

        Client client = new Client("Hugh Laurie", LocalDate.of(1959, 6, 11));
        client.setId(5);
        client.setCivilVehicle(lancer);
        LOGGER.info("Show client vehicle: " + client.getCivilVehicle().getBrand());

        Address address = new Address();
        address.setCity("Minsk");
        address.setDistrict("Central");
        address.setStreet("Prospekt nezavisimosti");
        address.setHouseNumber("56");
        client.setAddress(address);

        Restaurant restaurant = delivery.getRestaurants().get("burger king");

        List<Dish> orderDishes = new ArrayList<>();
        Dish dish1 = restaurant.getDishes().get(1);
        dish1.setDishQuantity(0);
        Dish dish2 = restaurant.getDishes().get(0);
        dish2.setDishQuantity(0);
        orderDishes.add(dish1);
        orderDishes.add(dish2);

        Courier courier = delivery.getCouriers().get(1);
        courier.setCar(new WvGolf("WV Golf", 30_000));
        Car courierCar = courier.getCar();
        courierCar.setOdometerCurrent(1000_000_000);
        courierCar.setNextAirFilterService(45_000);

        try {
            VehicleService.changeOil(courierCar);
        } catch (TooBigValueException e) {
            LOGGER.error("Caught 'TooBigValueException'. ", e);
        } finally {
            LOGGER.info("Set default value for the odometer in finally block.");
            int odometerDefault = 100_000;
            courierCar.setOdometerCurrent(odometerDefault);
        }

        VehicleService.checkAirFilter(courierCar);
        VehicleService.checkIfRepairIsNeeded(courierCar);

        int deliveryDistance = OrderService.showDeliveryDistance(client.getAddress());

        List<Order> orders = new ArrayList<>();
        GeneralOrder order = new GeneralOrder(courier, client, deliveryDistance);
        order.setDiscount(30);
        order.setRestaurant(restaurant);
        order.setDishes(orderDishes);
        orders.add(order);

        delivery.setOrders(orders);

        LOGGER.info("Order delivery time: " + DeliveryService.countDeliveryTime(delivery) + " minutes");
        LOGGER.info("Order price: " + DeliveryService.countOrderPrice(delivery));
        LOGGER.info("Client age: " + ClientService.countAge(client));
        LOGGER.info("Order price with discount: " + order.countOrderPriceWithDiscount(order.getDishes()));

        Employee employee = new Courier("Zhenya", LocalDate.of(2000, 4, 19));
        employee.setHiringDate(LocalDate.of(2020, 5, 12));
        LOGGER.info("Working period: " + employee.countWorkingPeriod());

        DeliveryService.showPersonInfo(employee);
        DeliveryService.showPersonInfo(client);

        LOGGER.info("Number of couriers working in delivery." + DeliveryService.countCourierQuantity(delivery));
        LOGGER.info("Number of dishes in restaurant menu." + RestaurantService.countDishes(restaurant));

        List<Dish> dishes1 = order.getDishes();
        dishes1.forEach(RestaurantService::prepareDish);

        if (dishes.containsKey("burger king dishes")) {
            dishes.get("burger king dishes").remove(0);
            LOGGER.info("Dishes keySet after removal: " + dishes.keySet());
        }

        restaurantMap.remove("kfc");
        LOGGER.info("Restaurants left after removal: " + restaurantMap.values());

        Car wrx = new Car("Subaru WRX STI", 20_000);
        Car skyline = new Car("Nissan Skyline", 10_000);

        Set<Car> carSet = new HashSet<>();
        carSet.add(lancer);
        carSet.add(wrx);
        carSet.add(skyline);
        LOGGER.info("Cars in set: ");
        carSet.forEach(car -> LOGGER.info(car.getBrand()));

        carSet.add(lancer);
        LOGGER.info("Car set with one more (same config) lancer added: ");
        carSet.forEach(car -> LOGGER.info(car.getBrand()));
    }
}





