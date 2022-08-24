package com.solvd.fooddelivery.service;

public class CustomService {

    public static double convertMinutesToHours(IConvert<Double> convertTime, Double timeMinutes) {
        return convertTime.convert(timeMinutes);
    }
}
