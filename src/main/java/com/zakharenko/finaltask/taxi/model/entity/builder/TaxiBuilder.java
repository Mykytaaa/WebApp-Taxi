package com.zakharenko.finaltask.taxi.model.entity.builder;

import com.zakharenko.finaltask.taxi.model.entity.Taxi;

public interface TaxiBuilder{
    public TaxiBuilder setId(int id);

    public TaxiBuilder setStatus(String status);

    public TaxiBuilder setCar_type(String car_type);

    public TaxiBuilder setCapacity(int capacity);

    public Taxi build();
}
