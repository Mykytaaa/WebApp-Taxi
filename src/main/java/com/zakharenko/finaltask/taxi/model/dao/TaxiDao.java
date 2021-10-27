package com.zakharenko.finaltask.taxi.model.dao;

import com.zakharenko.finaltask.taxi.model.entity.Taxi;
import com.zakharenko.finaltask.taxi.model.entity.User;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface TaxiDao extends EntityDao<Integer, Taxi>{
    Taxi getTaxiByCapacity(int capacity) throws NamingException, SQLException;

    List<Taxi> getTaxiByCarType(String carType) throws NamingException, SQLException;

    boolean updateTaxiStatus(int carId, String carStatus) throws NamingException;

}
