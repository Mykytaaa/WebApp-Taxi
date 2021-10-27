package com.zakharenko.finaltask.taxi.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zakharenko.finaltask.taxi.controller.command.Command;
import com.zakharenko.finaltask.taxi.controller.command.commandfactory.CommandFactory;
import com.zakharenko.finaltask.taxi.controller.command.utils.CommandUtil;
import com.zakharenko.finaltask.taxi.model.dao.exception.NotFoundOperationException;
import org.apache.log4j.Logger;

import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(DispatcherServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            doPost(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String path = req.getRequestURI();

        path = path.substring(path.indexOf("view") - 1);
        logger.info(path);
        Command command = null;
        try {
            command = CommandFactory.getCommand(path);
            logger.info(path);

            if(path.equals("/view/confirmationOrder")){
                String currentlyTaxiIdString = req.getRequestURL() + "?" + req.getQueryString();
                int currentlyTaxiId = Integer.parseInt(currentlyTaxiIdString.substring(currentlyTaxiIdString.lastIndexOf("="))
                        .replaceAll("=", ""));
                req.getSession().setAttribute("currentlyTaxiId", currentlyTaxiId);
            }

            command.execute(req, resp);
        } catch (NotFoundOperationException e) {
            logger.info("not found in DispatcherServlet");
            CommandUtil.goToPage(req, resp, "/WEB-INF/view/errorPage.jsp");
        } catch (StringIndexOutOfBoundsException | NumberFormatException e){
            command.execute(req, resp);
        }

    }
}
