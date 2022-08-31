package com.solvd.fooddelivery.entity.vehicle;

import com.solvd.fooddelivery.exception.TooBigValueException;

public interface IDoService extends IReplace<SparePart> {

    void diagnose() throws TooBigValueException;

}
