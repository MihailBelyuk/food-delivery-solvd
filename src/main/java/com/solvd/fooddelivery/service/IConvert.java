package com.solvd.fooddelivery.service;

@FunctionalInterface
public interface IConvert<D> {

    D convert(D d);

}
