package com.zakharenko.finaltask.taxi.model.dao.impl;

import com.zakharenko.finaltask.taxi.model.dao.OrderDao;
import com.zakharenko.finaltask.taxi.model.dao.connection.Connector;
import com.zakharenko.finaltask.taxi.model.entity.Order;
import com.zakharenko.finaltask.taxi.model.entity.Taxi;
import com.zakharenko.finaltask.taxi.model.entity.User;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.zakharenko.finaltask.taxi.model.dao.constant.Constants.*;

public class OrderDatabaseDao implements OrderDao {

    Logger logger = Logger.getLogger(OrderDatabaseDao.class);

    @Override
    public boolean add(Order entity) throws NamingException {
        logger.info("OrderDatabaseDao#add");
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDate(1, entity.getTime_of_order());
            preparedStatement.setInt(2, entity.getNumber_of_passengers());
            preparedStatement.setDouble(3, entity.getPrice());
            preparedStatement.setString(4, entity.getDeparture());
            preparedStatement.setString(5, entity.getArrival());
            preparedStatement.setDouble(6, entity.getDistance());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                entity.setId(resultSet.getInt(1));
            }
            connection.commit();

            insertUserHasOrder(entity.getId(), entity.getUserId());
            insertCheckCar(entity.getId(), entity.getTaxiId());
        } catch (SQLException e) {
            logger.error("OrderDatabaseDao#add failed ", e.getCause());
            return false;
        }
        logger.info("OrderDatabaseDao#add success");
        return true;
    }

    private boolean insertUserHasOrder(int orderId, int userId) throws NamingException, SQLException {
        logger.info("OrderDatabaseDao#insertUserHasOrder");
        Connection connection = Connector.getInstance().getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_HAS_ORDER)){
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, orderId);

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e){
            logger.error(e);
            connection.rollback();
            return false;
        } finally {
            logger.info("OrderDatabaseDao#insertUserHasOrder closing connection");
            connection.close();
        }
        logger.info("OrderDatabaseDao#insertUserHasOrder success");
        return true;
    }
    private boolean insertCheckCar(int orderId, int taxiId) throws NamingException, SQLException {
        logger.info("OrderDatabaseDao#insertCheckCar");
        logger.info(orderId);
        logger.info(taxiId);
        Connection connection = Connector.getInstance().getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CHECK_CAR)){
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, taxiId);

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e){
            logger.error(e);
            connection.rollback();
            return false;
        } finally {
            logger.info("OrderDatabaseDao#insertCheckCar closing connection");
            connection.close();
        }
        logger.info("OrderDatabaseDao#insertCheckCar success");
        return true;
    }

        @Override
    public Order getById(int id) throws NamingException, SQLException {
        logger.info("OrderDatabaseDao#getById with " + id + " argument start");
        List<Order> orderList;
        Order order;
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            orderList = createOrderList(resultSet);
            if(!orderList.isEmpty()){
                order = orderList.get(0);
            } else {
                logger.warn("Order by id wasn't found. Returning null");
                return null;
            }
        }
        logger.info("OrderDatabaseDao#getById with " + id + "success");
        return order;
    }

    @Override
    public List<Order> getAll() throws NamingException, SQLException {
        logger.info("OrderDatabaseDao#getAll");
        List<Order> orderList;

        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDER)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            orderList = createOrderList(resultSet);

        }
        return orderList;
    }

    @Override
    public List<Order> getAllUserOrder(User user) throws NamingException, SQLException {
        logger.info("OrderDatabaseDao#getAllUserOrder with "  + user.getId() +  " argument start");
        List<Order> orderList;
        try(Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USER_ORDER)){
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = createOrderList(resultSet);

        }
        logger.info("OrderDatabaseDao#getAllUserOrder getAllUserTaxi " + user.getId() + " success");
        return orderList;
    }

    @Override
    public boolean update(Order entity) throws NamingException {
        logger.info("OrderDatabaseDao#update");
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER)) {
            preparedStatement.setDate(1, entity.getTime_of_order());
            preparedStatement.setInt(2, entity.getNumber_of_passengers());
            preparedStatement.setDouble(3, entity.getPrice());
            preparedStatement.setString(4, entity.getDeparture());
            preparedStatement.setString(5, entity.getArrival());
            preparedStatement.setDouble(6, entity.getDistance());
            preparedStatement.setInt(7, entity.getId());

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e){
            logger.error("OrderDatabaseDao#update failed, returning false");
            return false;
        }
        logger.info("OrderDatabaseDao#update " + entity.getId() + " success");
        return true;
    }

    @Override
    public boolean delete(Integer id) throws NamingException {
        logger.info("OrderDatabaseDao#delete");
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e){
            logger.error("OrderDatabaseDao#delete failed, returning false");
            return false;
        }

        logger.info("OrderDatabaseDao#delete " + id + " success");
        return true;
    }

    private List<Order> createOrderList(ResultSet resultSet) throws SQLException {
        List<Order> orderList = new ArrayList<>();

        while (resultSet.next()) {
            Order order = new Order.OrderBuilderImpl()
                    .setId(resultSet.getInt("id"))
                    .setTime_of_order(resultSet.getDate("time_of_order"))
                    .setDistance(resultSet.getDouble("distance"))
                    .setArrival(resultSet.getString("arrival"))
                    .setDeparture(resultSet.getString("departure"))
                    .setPrice(resultSet.getDouble("price"))
                    .setNumber_of_passengers(resultSet.getInt("number_of_passengers"))
                    .build();
            orderList.add(order);
        }
        return orderList;
    }

    @Override
    public List<Order> getUserOrders(int accountId) throws NamingException, SQLException {
        logger.info("OrderDatabaseDao#getUserOrders");
        List<Order> orderList;

        try(Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_ORDERS)){
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = createOrderList(resultSet);
        }
        logger.info("OrderDatabaseDao#getUserOrders successful");
        return orderList;
    }

    @Override
    public List<Order> getOrdersByDate(Date date) throws NamingException, SQLException{
        logger.info("OrderDatabaseDao#getOrdersByDate");
        List<Order> orderList;

        try(Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_ORDERS_BY_DATE)){
            preparedStatement.setDate(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = createOrderList(resultSet);
        }
        logger.info("OrderDatabaseDao#getUserOrders successful");
        return orderList;
    }


}
