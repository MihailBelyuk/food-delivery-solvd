package com.solvd.fooddelivery.service;

import com.solvd.fooddelivery.entity.person.CourierType;

public class CourierService {

    public static int adjustCourierSpeed(CourierType courierType) {
        int speed = 0;
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
        return speed;
    }
}
