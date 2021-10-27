package com.zakharenko.finaltask.taxi.service;

import com.zakharenko.finaltask.taxi.model.dao.exception.DatabaseException;
import com.zakharenko.finaltask.taxi.model.entity.Taxi;
import com.zakharenko.finaltask.taxi.model.entity.User;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface TaxiService extends EntityService<Integer, Taxi>{
    Taxi getTaxiByCapacity(int capacity) throws NamingException, DatabaseException;

    List<Taxi> getTaxiByCarType(String carType) throws NamingException, DatabaseException;

    boolean updateTaxiStatus(int carId, String carStatus) throws NamingException;
}
