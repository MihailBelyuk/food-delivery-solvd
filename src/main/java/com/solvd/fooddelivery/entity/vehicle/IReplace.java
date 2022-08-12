package com.solvd.fooddelivery.entity.vehicle;

public interface IReplace<P extends SparePart> {

    void replace(P sparePart);

}
