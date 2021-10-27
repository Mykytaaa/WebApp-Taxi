package com.zakharenko.finaltask.taxi.controller.command;

import com.zakharenko.finaltask.taxi.controller.command.utils.CommandUtil;
import com.zakharenko.finaltask.taxi.model.entity.Order;
import com.zakharenko.finaltask.taxi.model.entity.User;
import com.zakharenko.finaltask.taxi.service.OrderService;
import com.zakharenko.finaltask.taxi.service.TaxiService;
import com.zakharenko.finaltask.taxi.service.factory.ServiceFactory;
import org.apache.log4j.Logger;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ConfirmationOfOrderCommand implements Command{
    private static final Logger logger = Logger.getLogger(ConfirmationOfOrderCommand.class);
    private static final String DEFAULT_PAGE = "/WEB-INF/view/confirmationOrder.jsp";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("ConfirmationOfOrderCommand start");

        int currentlyTaxiId = (int) req.getSession().getAttribute("currentlyTaxiId");
        User user = (User) req.getSession().getAttribute("user");

        try {

            ServiceFactory factory = ServiceFactory.getInstance();
            OrderService orderService = factory.getOrderService();
            TaxiService taxiService = factory.getTaxiService();


            byte numberOfPassengers = Byte.parseByte(req.getParameter("numberOfPassengers"));
            String arrival = req.getParameter("arrival");
            String departure = req.getParameter("departure");

            if (Objects.nonNull(arrival) && Objects.nonNull(departure) && Objects.nonNull(numberOfPassengers)) {

                Order order = new Order.OrderBuilderImpl()
                        .setArrival(arrival)
                        .setDeparture(departure)
                        .setNumber_of_passengers(numberOfPassengers)
                        .setTime_of_order(CommandUtil.getDate())
                        .setPrice(CommandUtil.getPrice())
                        .setDistance(CommandUtil.getDistance())
                        .setUserId((int) req.getSession().getAttribute("globalUserId"))
                        .setTaxiId(currentlyTaxiId)
                        .build();

                orderService.add(order);
                taxiService.updateTaxiStatus(currentlyTaxiId, "not_available");

                String page = CommandUtil.getUserProfileByRole(user.getRole());
                logger.info("redirection to " + page);
                resp.sendRedirect(page);
            } else {
                logger.info("go to confirmationOrder");
                CommandUtil.goToPage(req, resp, DEFAULT_PAGE);
            }
        } catch (SQLException | NamingException throwables) {
            throw new RuntimeException();

        } catch (NumberFormatException e){
            logger.info("go to confirmationOrder");
            CommandUtil.goToPage(req, resp, DEFAULT_PAGE);
        }
    }

}
