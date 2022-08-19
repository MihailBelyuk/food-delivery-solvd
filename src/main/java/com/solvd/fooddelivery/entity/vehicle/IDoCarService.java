package com.solvd.fooddelivery.entity.vehicle;

import com.solvd.fooddelivery.exception.TooBigValueException;

public interface IDoCarService extends IDoService {

    boolean checkIfEngineOilChangeNeeded() throws TooBigValueException;

    boolean checkIfAirFilterReplacementNeeded();

}
