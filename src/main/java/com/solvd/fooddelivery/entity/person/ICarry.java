package com.solvd.fooddelivery.entity.person;

@FunctionalInterface
public interface ICarry<T> {

    void carry(T t);

}
