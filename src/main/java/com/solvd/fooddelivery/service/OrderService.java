package com.solvd.fooddelivery.service;

import com.solvd.fooddelivery.entity.Address;

import java.util.Optional;

public class OrderService {

    public static Optional<Integer> showDeliveryDistance(Address address) {
        District clientDistrict = address.getDistrict();
        Integer distanceKm = null;
        switch (clientDistrict) {
            case CENTRAL:
                distanceKm = 10;
                break;
            case SOVETSKIY:
                distanceKm = 15;
                break;
            case PERVOMAYSKIY:
                distanceKm = 5;
                break;
            case PARTIZANSKIY:
                distanceKm = 7;
                break;
            case ZAVODSKOY:
                distanceKm = 12;
                break;
            case LENINSKIY:
                distanceKm = 9;
                break;
            case OKTIABRSKIY:
                distanceKm = 20;
                break;
            case MOSKOVSKIY:
                distanceKm = 3;
                break;
            case FRUNZENSKIY:
                distanceKm = 14;
                break;
            default:
                break;
        }
        return Optional.ofNullable(distanceKm);
    }
}
