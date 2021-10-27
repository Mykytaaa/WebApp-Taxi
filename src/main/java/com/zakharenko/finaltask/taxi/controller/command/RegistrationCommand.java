package com.zakharenko.finaltask.taxi.controller.command;

import com.zakharenko.finaltask.taxi.controller.command.utils.CommandUtil;
import com.zakharenko.finaltask.taxi.controller.command.utils.ValidationData;
import com.zakharenko.finaltask.taxi.model.dao.exception.AlreadyExistUserException;
import com.zakharenko.finaltask.taxi.model.dao.exception.InvalidDataException;
import com.zakharenko.finaltask.taxi.model.dao.exception.PasswordGenerationException;
import com.zakharenko.finaltask.taxi.model.entity.Role;
import com.zakharenko.finaltask.taxi.model.entity.User;
import com.zakharenko.finaltask.taxi.model.entity.UserStatus;
import com.zakharenko.finaltask.taxi.service.UserService;
import com.zakharenko.finaltask.taxi.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class RegistrationCommand implements Command{

    private static final Logger logger = Logger.getLogger(RegistrationCommand.class);

    private static final String DEFAULT_PAGE = "/WEB-INF/view/registration.jsp";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Execution of RegistrationCommand has been started");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String login = request.getParameter("login");
        String phone_number = request.getParameter("phone_number");

        if (Objects.isNull(login) || Objects.isNull(email) || Objects.isNull(password) || Objects.isNull(phone_number)) {
            logger.warn("Some fields are empty");
            CommandUtil.goToPage(request, response, DEFAULT_PAGE);
        }
        try{
            if (!ValidationData.isEmailValid(email) || !ValidationData.isPasswordValid(password) || !ValidationData.isPhoneNumberValid(phone_number)) {
                logger.warn("Email or password or phone number is not valid");
                throw new InvalidDataException();
            }

            UserService userService = serviceFactory.getUserService();

            if(Objects.nonNull(userService.getUserByEmail(email))){
                logger.info("email already exist");
                throw new AlreadyExistUserException();
            }

            User user = new User.UserBuilderImpl()
                    .setLogin(login)
                    .setPassword(CommandUtil.encrypt(password, false).orElseThrow(PasswordGenerationException::new))
                    .setEmail(email)
                    .setPhone(phone_number)
                    .setRole(Role.USER.getAccessLevel())
                    .setStatus(UserStatus.ACTIVE.getUserStatus())
                    .build();

            userService.add(user);

            logger.info("redirect to login page");
            response.sendRedirect("/");
        } catch (AlreadyExistUserException throwables) {
            request.setAttribute("existUser", true);
            CommandUtil.goToPage(request, response, DEFAULT_PAGE);
        } catch (NamingException | SQLException  | PasswordGenerationException e) {
            throw new RuntimeException(e);
        } catch (InvalidDataException e){
            request.setAttribute("invalidData", true);
            CommandUtil.goToPage(request, response, DEFAULT_PAGE);
        }
    }
}
