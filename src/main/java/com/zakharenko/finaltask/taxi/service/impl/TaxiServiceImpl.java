package com.zakharenko.finaltask.taxi.service.impl;

import com.zakharenko.finaltask.taxi.model.dao.TaxiDao;
import com.zakharenko.finaltask.taxi.model.dao.exception.DatabaseException;
import com.zakharenko.finaltask.taxi.model.dao.factory.DaoFactory;
import com.zakharenko.finaltask.taxi.model.entity.Taxi;
import com.zakharenko.finaltask.taxi.model.entity.User;
import com.zakharenko.finaltask.taxi.service.TaxiService;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TaxiServiceImpl implements TaxiService {

    Logger logger = Logger.getLogger(TaxiServiceImpl.class);

    DaoFactory daoFactory = DaoFactory.getInstance();
    TaxiDao taxiDao = daoFactory.getTaxiDao();

    @Override
    public boolean add(Taxi entity) throws NamingException {
        logger.info("TaxiServiceImpl#add started");
        return taxiDao.add(entity);
    }

    @Override
    public Taxi getById(int id) throws NamingException {
        logger.info("TaxiServiceImpl#getById started");
        try {
            return taxiDao.getById(id);
        } catch (SQLException e){
            logger.error("TaxiServiceImpl#getById " + e.getCause());
            throw new DatabaseException();
        }
    }

    @Override
    public List<Taxi> getAll() throws NamingException, DatabaseException {
        logger.info("TaxiServiceImpl#getAll started");
        try{
            return taxiDao.getAll();
        } catch (SQLException e){
            logger.error("TaxiServiceImpl#getAll ", e.getCause());
            throw new DatabaseException();
        }
    }

    @Override
    public boolean update(Taxi entity) throws NamingException {
        logger.info("TaxiServiceImpl#update started");
        return taxiDao.update(entity);
    }

    @Override
    public boolean delete(Integer id) throws NamingException {
        logger.info("TaxiServiceImpl#delete started");
        return taxiDao.delete(id);
    }

    @Override
    public Taxi getTaxiByCapacity(int capacity) throws NamingException {
        logger.info("TaxiServiceImpl#getTaxiByCapacity started");
        try {
            return taxiDao.getAll().stream().filter(a -> a.getCapacity() == capacity)
                    .collect(Collectors.toList()).get(0);
        } catch (SQLException e){
            logger.error("TaxiServiceImpl#getTaxiByCapacity " + e.getCause());
            throw new DatabaseException();
        }
    }

    @Override
    public List<Taxi> getTaxiByCarType(String carType) throws NamingException {
        logger.info("TaxiServiceImpl#getTaxiByCapacity started");
        try {
            return taxiDao.getAll().stream().filter(a -> a.getCar_type().equals(carType))
                    .collect(Collectors.toList());
        } catch (SQLException e){
            logger.error("TaxiServiceImpl#getTaxiByCapacity " + e.getCause());
            throw new DatabaseException();
        }
    }

    @Override
    public boolean updateTaxiStatus(int carId, String carStatus) throws NamingException {
        logger.info("TaxiServiceImpl#updateTaxiStatus started");
        return taxiDao.updateTaxiStatus(carId, carStatus);
    }
}
