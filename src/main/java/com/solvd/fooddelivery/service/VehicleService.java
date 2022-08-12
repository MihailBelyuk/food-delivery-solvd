package com.solvd.fooddelivery.service;

import com.solvd.fooddelivery.entity.vehicle.IDoCarService;
import com.solvd.fooddelivery.entity.vehicle.IRepair;
import com.solvd.fooddelivery.entity.vehicle.Vehicle;
import com.solvd.fooddelivery.entity.vehicle.Wheel;
import com.solvd.fooddelivery.exception.TooBigValueException;

public class VehicleService {

    public static void changeOil(IDoCarService iDoService) throws TooBigValueException {
        iDoService.checkIfEngineOilChangeNeeded();
    }

    public static void checkAirFilter(IDoCarService iDoService) {
        iDoService.checkIfAirFilterReplacementNeeded();
    }

    public static void checkIfRepairIsNeeded(IRepair iRepair) {
        Vehicle vehicle = (Vehicle) iRepair;
        vehicle.checkIfNeedsRepair();
    }

    public static void replaceWheel(IDoCarService iDoCarService) {
        Wheel wheel = new Wheel();
        wheel.setBrand("Good Year");
        iDoCarService.replace(wheel);
    }
}
