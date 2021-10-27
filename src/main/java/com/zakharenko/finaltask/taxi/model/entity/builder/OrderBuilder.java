package com.zakharenko.finaltask.taxi.model.entity.builder;

import com.zakharenko.finaltask.taxi.model.entity.Order;

import java.sql.Date;

public interface OrderBuilder {
    OrderBuilder setId(int id);

    OrderBuilder setUserId(int userId);

    public OrderBuilder setTaxiId(int userId);

    OrderBuilder setDeparture(String departure);

    OrderBuilder setArrival(String arrival);

    OrderBuilder setTime_of_order(Date time_of_order);

    OrderBuilder setNumber_of_passengers(int number_of_passengers);

    OrderBuilder setPrice(double price);

    OrderBuilder setDistance(double distance);

    Order build();
}
