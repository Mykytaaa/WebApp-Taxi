package com.zakharenko.finaltask.taxi.controller.command.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

public abstract class CommandUtil {

    private static final Logger logger = Logger.getLogger(CommandUtil.class);

    private CommandUtil() {
    }

    public static void goToPage(HttpServletRequest req, HttpServletResponse resp, String url) throws IOException {
        logger.info("go to page start");
        var requestDispatcher = req.getRequestDispatcher(url);

        logger.info(url);
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error(e);
        }
    }

    public static String getUserPageByRole(String role) {
        String page = "";
        logger.info("getUserPageByRole() has started");
        if (role.equals("admin")) {
            page = "/view/admin/mainPageAdmin";
        } else {
            page = "/view/client/mainPageUser";
        }
        logger.info(page);
        return page;
    }


    public static Date getDate() {
        var cal = Calendar.getInstance();
        var sdf = new SimpleDateFormat("yyyy-MM-dd");
        return Date.valueOf(sdf.format(cal.getTime()));
    }

    public static double getPrice(){
        return 20 + Math.random() * 40;
    }
    public static double getDistance(){
        return 20 + Math.random() * 1000;
    }

    public static String getUserProfileByRole(String role) {
        String page = "";
        logger.info("getUserProfileByRole() has started");
        if (role.equals("admin")) {
            page = "/view/admin/profileAdmin";
        } else if (role.equals("user")) {
            page = "/view/client/profileUser";
        }
        logger.info(page);
        return page;
    }

    public static Optional<String> encrypt(String pass, boolean isVerify) {
        var bcryptHashString = BCrypt.withDefaults().hashToString(12, pass.toCharArray());
        if (isVerify) {
            BCrypt.Result result = BCrypt.verifyer().verify(pass.toCharArray(), bcryptHashString);
            return Optional.of(String.valueOf(result.verified));
        }
        return Optional.of(bcryptHashString);
    }

    public static boolean verifyPass(String pass, String userPass) {
        BCrypt.Result result = BCrypt.verifyer().verify(pass.toCharArray(), userPass);
        return result.verified;
    }

}
