package com.solvd.fooddelivery.service;

import com.solvd.fooddelivery.entity.person.Courier;

import java.util.Optional;

public class CourierService {

    public static Optional<Integer> adjustCourierSpeed(Courier.DeliveryType courierType) {
        Integer speed = null;
        switch (courierType) {
            case BICYCLE:
                speed = 15;
                break;
            case CAR:
                speed = 60;
                break;
            case ON_FOOT:
                speed = 5;
                break;
            default:
                break;
        }
        return Optional.ofNullable(speed);
    }

    public static void changeDeliveryType(IChange changer, Courier courier) {
        courier.setDeliveryType(Courier.DeliveryType.BICYCLE);
        changer.change();
    }
}
