package com.solvd.fooddelivery.entity.vehicle;

public interface IRepair extends IReplace<SparePart> {

    void checkIfNeedsRepair();

}
