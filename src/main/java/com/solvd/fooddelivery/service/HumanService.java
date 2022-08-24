package com.solvd.fooddelivery.service;

import com.solvd.fooddelivery.entity.person.Human;
import com.solvd.fooddelivery.entity.person.ICarry;
import com.solvd.fooddelivery.entity.vehicle.CivilVehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HumanService {

    private static final Logger LOGGER = LogManager.getLogger(HumanService.class);

    public static void showDayActivity(ICarry<Human<CivilVehicle>> carrier, Human<CivilVehicle> human) {
        LOGGER.info(human.getName() + " started his working day");
        carrier.carry(human);
        LOGGER.info(human.getName() + " went on a lunch break");
    }

}
