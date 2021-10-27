package com.zakharenko.finaltask.taxi.model.dao;

import com.zakharenko.finaltask.taxi.model.entity.Order;
import com.zakharenko.finaltask.taxi.model.entity.User;

import javax.naming.NamingException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends EntityDao<Integer, Order>{

    List<Order> getUserOrders(int accountId) throws NamingException, SQLException;

    List<Order> getAllUserOrder(User user) throws NamingException, SQLException;

    List<Order> getOrdersByDate(Date date) throws NamingException, SQLException;
}
