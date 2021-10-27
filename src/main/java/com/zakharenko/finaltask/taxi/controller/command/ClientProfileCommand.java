package com.zakharenko.finaltask.taxi.controller.command;


import com.zakharenko.finaltask.taxi.controller.command.utils.CommandUtil;
import com.zakharenko.finaltask.taxi.model.dao.exception.DatabaseException;
import com.zakharenko.finaltask.taxi.model.entity.Order;
import com.zakharenko.finaltask.taxi.model.entity.Taxi;
import com.zakharenko.finaltask.taxi.model.entity.User;
import com.zakharenko.finaltask.taxi.service.OrderService;
import com.zakharenko.finaltask.taxi.service.TaxiService;
import com.zakharenko.finaltask.taxi.service.UserService;
import com.zakharenko.finaltask.taxi.service.factory.ServiceFactory;
import org.apache.log4j.Logger;


import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ClientProfileCommand implements Command {
    private static final Logger logger = Logger.getLogger(ClientProfileCommand.class);


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("ClientProfileCommand start");

        ServiceFactory factory = ServiceFactory.getInstance();

        try {
            OrderService orderService = factory.getOrderService();

            User user = (User) req.getSession().getAttribute("user");

            List<Order> orderList = orderService.getAllUserOrder(user);
            req.setAttribute("orderList", orderList);
        } catch (NamingException | DatabaseException e) {
            throw new RuntimeException(e);
        }

        logger.info("go to profileUser");
        CommandUtil.goToPage(req, resp, "/WEB-INF/view/client/profileUser.jsp");
    }

}