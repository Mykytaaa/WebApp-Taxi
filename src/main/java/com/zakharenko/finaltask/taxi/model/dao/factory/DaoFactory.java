package com.zakharenko.finaltask.taxi.model.dao.factory;

import com.zakharenko.finaltask.taxi.model.dao.OrderDao;
import com.zakharenko.finaltask.taxi.model.dao.TaxiDao;
import com.zakharenko.finaltask.taxi.model.dao.UserDao;

public abstract class DaoFactory {
    private static DaoFactory instance;

    public static DaoFactory getInstance(){
        if (instance == null){
            instance = new DatabaseDaoFactory();
        }
        return instance;
    }

    public abstract TaxiDao getTaxiDao();
    public abstract UserDao getUserDao();
    public abstract OrderDao getOrderDao();
}
