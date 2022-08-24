package com.solvd.fooddelivery.service;

import com.solvd.fooddelivery.entity.person.Client;
import com.solvd.fooddelivery.entity.person.ICountTimePeriod;

public class ClientService {

    public static Integer countAge(ICountTimePeriod iCountTimePeriod) {
        Client client = (Client) iCountTimePeriod;
        return client.countYears();
    }
}
