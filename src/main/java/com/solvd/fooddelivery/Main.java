package com.solvd.fooddelivery;

import com.solvd.fooddelivery.entity.Address;
import com.solvd.fooddelivery.entity.delivery.Delivery;
import com.solvd.fooddelivery.entity.delivery.order.GeneralOrder;
import com.solvd.fooddelivery.entity.delivery.order.Order;
import com.solvd.fooddelivery.entity.delivery.restaurant.Restaurant;
import com.solvd.fooddelivery.entity.delivery.restaurant.food.Dish;
import com.solvd.fooddelivery.entity.delivery.restaurant.food.Food;
import com.solvd.fooddelivery.entity.delivery.restaurant.food.ingredient.Ingredient;
import com.solvd.fooddelivery.entity.person.*;
import com.solvd.fooddelivery.entity.vehicle.*;
import com.solvd.fooddelivery.exception.DistanceException;
import com.solvd.fooddelivery.exception.TooBigValueException;
import com.solvd.fooddelivery.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {

    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Delivery delivery = new Delivery();

        Director director = new Director("Neskey Dmitri", LocalDate.of(1988, 2, 15));
        director.setName("Vasil' Petrovich");

        Map<String, List<Ingredient>> ingredients = Creator.createIngredients();
        ingredients.values().forEach(ingredientsValue -> ingredientsValue.forEach(ingredient -> ingredient.setPresent(true)));

        Map<String, List<Dish>> dishes = Creator.createDishes(ingredients);
        delivery.setRestaurants(Creator.createFastFoodRestaurants(dishes));
        delivery.setCouriers(Creator.createCouriers());

        dishes.values().forEach(dishes1 -> dishes1.stream()
                .filter(dish -> "Hot wings".equals(dish.getName()))
                .forEach(dish -> dish.setSpicy(true)));

        LOGGER.info(dishes.values().stream()
                .flatMap(Collection::stream)
                .map(Food::getName)
                .collect(Collectors.toList()));

        Map<String, Restaurant> restaurantMap = delivery.getRestaurants();
        LOGGER.info("Show entry set: " + restaurantMap.entrySet());
        LOGGER.info("Show map keys: " + restaurantMap.keySet());

        Car lancer = new Car("Mitsubishi Lancer EVO X", 15_000);
        lancer.setFuelType(Vehicle.FuelType.GASOLINE);
        VehicleService.replaceWheel(lancer);

        Client client = new Client("Hugh Laurie", LocalDate.of(1959, 6, 11));
        client.setId(5);
        client.setCivilVehicle(lancer);
        LOGGER.info("Show client vehicle: " + client.getCivilVehicle().getBrand());

        Address address = new Address();
        address.setCity("Minsk");
        address.setDistrict(District.CENTRAL);
        address.setStreet("Prospekt nezavisimosti");
        address.setHouseNumber("56");
        client.setAddress(address);

        Restaurant restaurant = delivery.getRestaurants().get("burger king");

        List<Dish> orderDishes = new ArrayList<>();
        Dish dish1 = restaurant.getDishes().get(1);
        dish1.setDishQuantity(1);
        dish1.setPrice(new BigDecimal(1));
        Dish dish2 = restaurant.getDishes().get(0);
        dish2.setDishQuantity(1);
        dish2.setPrice(new BigDecimal(1));
        orderDishes.add(dish1);
        orderDishes.add(dish2);

        Dish firstDish = orderDishes.stream()
                .findFirst()
                .orElse(new Dish("Draniki", 120));
        LOGGER.info("First element: " + firstDish);

        long saltUsageCount = orderDishes.stream()
                .flatMap(dish -> dish.getIngredients().stream())
                .filter(ingredient -> "salt".equals(ingredient.getName()))
                .count();
        LOGGER.info("Count salt usage in dishes: " + saltUsageCount);

        List<List<Ingredient>> allIngredients = orderDishes.stream()
                .map(Food::getIngredients)
                .collect(Collectors.toList());
        LOGGER.info("Show all ingredients: " + allIngredients);

        Courier courier = delivery.getCouriers().get(1);
        courier.setCar(new WvGolf("WV Golf", 30_000));
        Car courierCar = courier.getCar();
        courierCar.setOdometerCurrent(1_000_000_000);
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

        Integer deliveryDistance = OrderService.showDeliveryDistance(client.getAddress()).orElseThrow(DistanceException::new);

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

        String beforeRemoval = "Dishes before removal: " + dishes.get("burger king dishes").stream()
                .map(Food::getName)
                .collect(Collectors.toList());
        LOGGER.info(beforeRemoval);

        String afterRemoval = "Dishes keySet after removal: " + dishes.keySet().stream()
                .filter("burger king dishes"::equals)
                .peek(key -> dishes.get(key).remove(0))
                .flatMap(key -> dishes.get(key).stream())
                .map(Food::getName)
                .collect(Collectors.toList());
        LOGGER.info(afterRemoval);

        restaurantMap.remove("kfc");
        LOGGER.info("Restaurants left after removal: " + restaurantMap.values());

        Car wrx = new Car("Subaru WRX STI", 20_000);
        wrx.setFuelType(Vehicle.FuelType.GASOLINE);
        Car skyline = new Car("Nissan Skyline", 10_000);
        skyline.setFuelType(Vehicle.FuelType.GASOLINE);

        List<StringBuilder> fuelTypes = Arrays.stream(Vehicle.FuelType.values())
                .map(ft -> new StringBuilder(ft + "-" + ft.getDisplayName()))
                .collect(Collectors.toList());

        LOGGER.info("Fuel types: " + fuelTypes);

        Set<Car> carSet = new HashSet<>();
        carSet.add(lancer);
        carSet.add(wrx);
        carSet.add(skyline);
        LOGGER.info("Cars in set: ");
        carSet.forEach(car -> LOGGER.info(car.getBrand()));

        carSet.add(lancer);
        LOGGER.info("Car set with one more (same config) lancer added: ");
        carSet.forEach(car -> LOGGER.info(car.getBrand()));
        delivery.getCouriers().forEach(cour -> LOGGER.info(cour.getDeliveryType().getDisplayName()));

        double orderPrepareMinutes = DeliveryService.countDeliveryTime(delivery);

        ICarry<Human<CivilVehicle>> documentCarrier = dir -> LOGGER.info(dir.getName() + " is signing employee contracts.");
        ICarry<Human<CivilVehicle>> orderCarrier = courierOnDelivery -> {
            LOGGER.info("Courier " + courierOnDelivery.getName() + " picked up order from the restaurant.");
            LOGGER.info("Courier " + courierOnDelivery.getName() + " is on his way to the client.");
        };
        HumanService.showDayActivity(orderCarrier, courier);
        HumanService.showDayActivity(documentCarrier, director);

        IConvert<Double> minutesToHoursConverter = (orderPrepareMin) -> orderPrepareMin / 60;
        LOGGER.info("Order prepare minutes, converted to hours: " +
                CustomService.convertMinutesToHours(minutesToHoursConverter, orderPrepareMinutes));

        IChange deliveryTypeChanger = () -> LOGGER.info("Delivery type has been changed.");
        CourierService.changeDeliveryType(deliveryTypeChanger, courier);

        Predicate<Restaurant> checkQuantity = (Restaurant -> (long) Restaurant.getDishes().size() >= 1);
        LOGGER.info("Restaurants with 1 or more dishes in menu" + RestaurantService.checkDishQuantity(checkQuantity, delivery));

        Runnable runnable = () -> LOGGER.info("It's runnable: nothing to obtain and nothing to return.");
        testRunnable(runnable);

        Supplier<Client> supplier = () -> new Client("Kuzmich", LocalDate.of(1957, 11, 18));
        LOGGER.info("Supplier returns new Client: " + getNewClient(supplier).getName());

        Consumer<Client> consumer = vip -> vip.setId(5);
        showClientId(consumer, getNewClient(supplier));

        Function<Client, Long> function = Client::getId;
        LOGGER.info("Returned client id, using Function: " + returnClientId(function, getNewClient(supplier)));

        BiFunction<Integer, Integer, String> biFunction = (number1, number2) -> String.valueOf(number1 + number2);
        LOGGER.info("Two integers in, one string result out: " + showSumAsString(biFunction, 6, 7));

        SparePart enkei = new Wheel();
        enkei.setBrand("Enkei");
        try {
            Class<Car> carClass = (Class<Car>) Class.forName("com.solvd.fooddelivery.entity.vehicle.Car");
            Constructor<Car> carConstructor = carClass.getDeclaredConstructor(String.class, Integer.class);
            Car car = carConstructor.newInstance("Audi", 15_000);
            car.checkIfEngineOilChangeNeeded();
            Method carMethod = carClass.getDeclaredMethod("replace", SparePart.class);
            carMethod.invoke(car, enkei);
            Field field = carClass.getDeclaredField("oilServicePeriod");
            field.setAccessible(true);
            LOGGER.info("Field data: " + field);
        } catch (Exception e) {
            LOGGER.error("Failed to perform reflection.");
        }
    }

    private static void testRunnable(Runnable runnable) {
        runnable.run();
    }

    private static Client getNewClient(Supplier<Client> supplier) {
        return supplier.get();
    }

    private static void showClientId(Consumer<Client> consumer, Client client) {
        consumer.accept(client);
        LOGGER.info("Client new id: " + client.getId());
    }

    private static Long returnClientId(Function<Client, Long> function, Client client) {
        return function.apply(client);
    }

    private static String showSumAsString(BiFunction<Integer, Integer, String> biFunction, int num1, int num2) {
        return biFunction.apply(num1, num2);
    }
}