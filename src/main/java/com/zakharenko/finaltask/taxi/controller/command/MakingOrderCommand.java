package com.zakharenko.finaltask.taxi.controller.command;

import com.zakharenko.finaltask.taxi.controller.command.utils.CommandUtil;
import com.zakharenko.finaltask.taxi.model.entity.Taxi;
import com.zakharenko.finaltask.taxi.service.TaxiService;
import com.zakharenko.finaltask.taxi.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MakingOrderCommand implements Command{

    Logger logger = Logger.getLogger(MakingOrderCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("ClientProfileCommand start");

        ServiceFactory factory = ServiceFactory.getInstance();

        try {
            TaxiService taxiService = factory.getTaxiService();

            List<Taxi> taxiList = taxiService.getAll();
            request.setAttribute("taxiList", taxiList);
        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }

        logger.info("go to makingOrder");
        CommandUtil.goToPage(request, response, "/WEB-INF/view/makingOrder.jsp");
    }
}
