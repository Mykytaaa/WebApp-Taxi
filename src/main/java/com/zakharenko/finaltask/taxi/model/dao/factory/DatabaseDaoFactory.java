package com.zakharenko.finaltask.taxi.model.dao.factory;

import com.zakharenko.finaltask.taxi.model.dao.OrderDao;
import com.zakharenko.finaltask.taxi.model.dao.TaxiDao;
import com.zakharenko.finaltask.taxi.model.dao.UserDao;
import com.zakharenko.finaltask.taxi.model.dao.impl.OrderDatabaseDao;
import com.zakharenko.finaltask.taxi.model.dao.impl.TaxiDatabaseDao;
import com.zakharenko.finaltask.taxi.model.dao.impl.UserDatabaseDao;
import org.apache.log4j.Logger;

public class DatabaseDaoFactory extends DaoFactory{

    private static Logger logger = Logger.getLogger(DatabaseDaoFactory.class);


    private UserDao userDao = new UserDatabaseDao();
    private TaxiDao taxiDao = new TaxiDatabaseDao();
    private OrderDao orderDao = new OrderDatabaseDao();

    @Override
    public TaxiDao getTaxiDao() {
        logger.info("Get TaxiDatabaseDao");
        return taxiDao;
    }

    @Override
    public UserDao getUserDao() {
        logger.info("Get UserDatabaseDao");
        return userDao;
    }

    @Override
    public OrderDao getOrderDao() {
        logger.info("Get OrderDatabaseDao");
        return orderDao;
    }
}
