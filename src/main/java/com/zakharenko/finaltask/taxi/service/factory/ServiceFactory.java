package com.zakharenko.finaltask.taxi.service.factory;

import com.zakharenko.finaltask.taxi.service.OrderService;
import com.zakharenko.finaltask.taxi.service.TaxiService;
import com.zakharenko.finaltask.taxi.service.UserService;
import com.zakharenko.finaltask.taxi.service.impl.OrderServiceImpl;
import com.zakharenko.finaltask.taxi.service.impl.TaxiServiceImpl;
import com.zakharenko.finaltask.taxi.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

public class ServiceFactory {
    private static final Logger logger = Logger.getLogger(ServiceFactory.class);

    private final UserService userService = new UserServiceImpl();
    private final TaxiService taxiService = new TaxiServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    private static ServiceFactory instance;

    public static ServiceFactory getInstance(){
        if (instance == null) {
            logger.info("Create ServiceFactory");
            instance = new ServiceFactory();
        }
        return instance;
    }

    private ServiceFactory(){}

    public UserService getUserService(){
        logger.info("Get User Service");
        return userService;
    }
    public TaxiService getTaxiService(){
        logger.info("Get Taxi Service");
        return taxiService;
    }
    public OrderService getOrderService(){
        logger.info("Get Order Service");
        return orderService;
    }

}
