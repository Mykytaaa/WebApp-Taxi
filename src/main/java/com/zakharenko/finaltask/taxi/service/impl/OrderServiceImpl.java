package com.zakharenko.finaltask.taxi.service.impl;

import com.zakharenko.finaltask.taxi.model.dao.OrderDao;
import com.zakharenko.finaltask.taxi.model.dao.exception.DatabaseException;
import com.zakharenko.finaltask.taxi.model.dao.factory.DaoFactory;
import com.zakharenko.finaltask.taxi.model.entity.Order;
import com.zakharenko.finaltask.taxi.model.entity.User;
import com.zakharenko.finaltask.taxi.service.OrderService;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    Logger logger = Logger.getLogger(OrderServiceImpl.class);

    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private final OrderDao orderDao = daoFactory.getOrderDao();


    @Override
    public boolean add(Order entity) throws NamingException {
        logger.info("OrderServiceImpl#add started");
        return orderDao.add(entity);
    }

    @Override
    public Order getById(int id) throws NamingException, DatabaseException {
        logger.info("OrderServiceImpl#getById started");
        try{
            return orderDao.getById(id);
        } catch (SQLException e){
            logger.error("OrderServiceImpl#getById " + e.getCause());
            throw new DatabaseException();
        }
    }

    @Override
    public List getAll() throws NamingException, DatabaseException {
        try {
            logger.info("OrderServiceImpl#getAll started");
            return orderDao.getAll();
        } catch (SQLException e){
            logger.error("OrderServiceImpl#getAll " + e.getCause());
            throw new DatabaseException();
        }

    }

    @Override
    public boolean update(Order entity) throws NamingException {
        logger.info("OrderServiceImpl#update started");
        return orderDao.update(entity);
    }

    @Override
    public boolean delete(Integer id) throws NamingException {
        logger.info("OrderServiceImpl#delete started");
        return orderDao.delete(id);

    }

    @Override
    public List<Order> getUserOrders(int accountId) throws NamingException, DatabaseException {
        logger.info("OrderServiceImpl#getUserOrders started");
        try {
            return orderDao.getAll().stream().filter(a -> a.getUserId() == accountId).collect(Collectors.toList());
        }catch (SQLException e){
            logger.error("OrderServiceImpl#getUserOrders " + e.getCause());
            throw new DatabaseException();
        }
    }

    @Override
    public List<Order> getAllUserOrder(User user) throws NamingException, DatabaseException {
        logger.info("OrderServiceImpl#getAllUserOrder started");
        try {
            return orderDao.getAllUserOrder(user);
        }catch (SQLException e){
            logger.error("OrderServiceImpl#getAllUserOrder " + e.getCause());
            throw new DatabaseException();
        }
    }

    @Override
    public List<Order> getOrdersByDate(Date date) throws NamingException, DatabaseException {
        logger.info("OrderServiceImpl#getOrdersByDate started");
        try {
            return orderDao.getAll().stream().filter(a -> a.getTime_of_order().equals(date)).collect(Collectors.toList());
        } catch (SQLException e){
            logger.error("OrderServiceImpl#getOrdersByDate " + e.getCause());
            throw new DatabaseException();
        }
    }
}
