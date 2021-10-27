package com.zakharenko.finaltask.taxi.model.dao.impl;

import com.zakharenko.finaltask.taxi.model.dao.TaxiDao;
import com.zakharenko.finaltask.taxi.model.dao.connection.Connector;
import com.zakharenko.finaltask.taxi.model.entity.Taxi;
import com.zakharenko.finaltask.taxi.model.entity.User;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.zakharenko.finaltask.taxi.model.dao.constant.Constants.*;

public class TaxiDatabaseDao implements TaxiDao {

    Logger logger = Logger.getLogger(TaxiDatabaseDao.class);

    @Override
    public boolean add(Taxi entity) throws NamingException {
        logger.info("TaxiDatabaseDao#add");
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TAXI)){
            preparedStatement.setString(1, entity.getStatus());
            preparedStatement.setInt(2, entity.getCapacity());
            preparedStatement.setString(3, entity.getCar_type());

            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            logger.error("TaxiDatabaseDao#add failed ", e.getCause());
            return false;
        }
        logger.info("TaxiDatabaseDao#add success");
        return true;
    }

    @Override
    public Taxi getById(int id) throws NamingException, SQLException {
        logger.info("TaxiDatabaseDao#getById with " + id + " argument start");
        Taxi taxi;
        List<Taxi> taxiList;
        try(Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_TAXI_BY_ID)){

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            taxiList = createTaxiList(resultSet);
            if(!taxiList.isEmpty()){
                taxi = taxiList.get(0);
            } else {
                logger.warn("Taxi by id wasn't found. Returning null");
                return null;
            }

        }
        logger.info("TaxiDatabaseDao#getById with " + id + "success");
        return taxi;
    }

    @Override
    public List<Taxi> getAll() throws NamingException, SQLException {
        logger.info("TaxiDatabaseDao#getAll");
        List<Taxi> taxiList;

        try(Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_TAXI)){

            ResultSet resultSet = preparedStatement.executeQuery();

            taxiList = createTaxiList(resultSet);

        }
        logger.info("TaxiDatabaseDao#getAll success");
        return taxiList;
    }

    @Override
    public boolean update(Taxi entity) throws NamingException {
        logger.info("TaxiDatabaseDao#update");
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TAXI)) {

            preparedStatement.setString(1, entity.getStatus());
            preparedStatement.setInt(2, entity.getCapacity());
            preparedStatement.setString(3, entity.getCar_type() );
            preparedStatement.setInt(4, entity.getId());

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e){
            logger.error("TaxiDatabaseDao#update failed, returning false");
            return false;
        }
        logger.info("TaxiDatabaseDao#update " + entity.getStatus() + " " + entity.getCapacity() + " " + entity.getCar_type() +  " success");
        return true;
    }

    @Override
    public boolean delete(Integer id) throws NamingException {
        logger.info("TaxiDatabaseDao#delete");
        try(Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TAXI)){

            preparedStatement.setInt(1 , id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e){
            logger.error("TaxiDatabaseDao#delete failed, returning false");
            return false;
        }

        logger.info("TaxiDatabaseDao#delete " + id + " success");
        return true;
    }

    @Override
    public Taxi getTaxiByCapacity(int capacity) throws NamingException, SQLException {
        logger.info("TaxiDatabaseDao#getTaxiByCapacity with " + capacity + " argument start");
        List<Taxi> taxiList;
        Taxi taxi;
        try(Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_TAXI_BY_CAPACITY)){
            preparedStatement.setInt(1 , capacity);
            ResultSet resultSet = preparedStatement.executeQuery();


            taxiList = createTaxiList(resultSet);
            if (!taxiList.isEmpty()){
                taxi = taxiList.get(0);
            } else {
                logger.warn("Taxi by capacity wasn't found. Returning null");
                return null;
            }
        }
        logger.info("TaxiDatabaseDao#getTaxiByCapacity with " + capacity + "success");
        return taxi;
    }

    @Override
    public List<Taxi> getTaxiByCarType(String carType) throws NamingException, SQLException {
        logger.info("TaxiDatabaseDao#getTaxiByCarType with " + carType + " argument start");
        List<Taxi> taxi;
        try(Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_TAXI_BY_CAR_TYPE)){
            preparedStatement.setString(1 , carType);
            ResultSet resultSet = preparedStatement.executeQuery();
            taxi = createTaxiList(resultSet);
        }
        logger.info("TaxiDatabaseDao#getTaxiByCarType with " + carType + " success");
        return taxi;
    }

    @Override
    public boolean updateTaxiStatus(int carId, String carStatus) throws NamingException {
        logger.info("TaxiDatabaseDao#updateTaxiStatus with " + carStatus + " argument start");

        try(Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TAXI_STATUS)) {

            preparedStatement.setString(1, carStatus);
            preparedStatement.setInt(2, carId);

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e){
            logger.error("TaxiDatabaseDao#updateTaxiStatus failed, returning false");
            return false;
        }
        logger.info("TaxiDatabaseDao#update " + carId + " " +  carStatus + " success");
        return true;
    }

    private List<Taxi> createTaxiList(ResultSet resultSet) throws SQLException {
        List<Taxi> taxiList = new ArrayList<>();
        Taxi taxi;

        while(resultSet.next()){
            taxi =  new Taxi.TaxiBuilderImpl()
                    .setId(resultSet.getInt("id"))
                    .setStatus(resultSet.getString("status"))
                    .setCar_type(resultSet.getString("car_type"))
                    .setCapacity(resultSet.getByte("capacity"))
                    .build();
            taxiList.add(taxi);
        }
        return taxiList;
    }
}