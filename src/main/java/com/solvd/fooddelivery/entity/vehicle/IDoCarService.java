package com.solvd.fooddelivery.entity.vehicle;

import com.solvd.fooddelivery.exception.TooBigValueException;

public interface IDoCarService extends IDoService {

    void checkIfEngineOilChangeNeeded() throws TooBigValueException;

    void checkIfAirFilterReplacementNeeded();

}
