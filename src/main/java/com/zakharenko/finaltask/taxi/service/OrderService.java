package com.zakharenko.finaltask.taxi.service;

import com.zakharenko.finaltask.taxi.model.dao.exception.DatabaseException;
import com.zakharenko.finaltask.taxi.model.entity.Order;
import com.zakharenko.finaltask.taxi.model.entity.User;

import javax.naming.NamingException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface OrderService extends EntityService<Integer, Order>{
    List<Order> getUserOrders(int accountId) throws NamingException, DatabaseException;

    List<Order> getAllUserOrder(User user) throws NamingException, DatabaseException;

    List<Order> getOrdersByDate(Date date) throws NamingException, DatabaseException;
}
