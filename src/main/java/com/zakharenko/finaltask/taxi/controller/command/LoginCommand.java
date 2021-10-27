package com.zakharenko.finaltask.taxi.controller.command;

import com.zakharenko.finaltask.taxi.controller.command.utils.CommandUtil;
import com.zakharenko.finaltask.taxi.model.dao.exception.DatabaseException;
import com.zakharenko.finaltask.taxi.model.dao.exception.NotFoundUserException;
import com.zakharenko.finaltask.taxi.model.entity.User;
import com.zakharenko.finaltask.taxi.service.UserService;
import com.zakharenko.finaltask.taxi.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class LoginCommand implements Command{
    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Start execution login");
        ServiceFactory factory = ServiceFactory.getInstance();

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (Objects.nonNull(email) && Objects.nonNull(password)) {

            UserService userService = factory.getUserService();
            try {
                User user = userService.getUserByEmail(email);

                if (Objects.nonNull(user) && CommandUtil.verifyPass(password, user.getPassword())) {
                    req.getSession().setAttribute("user", user);
                    req.getSession().setAttribute("email1", email);
                    req.getSession().setAttribute("globalUserId", user.getId());
                } else {
                    throw new NotFoundUserException();
                }

                String page = CommandUtil.getUserPageByRole(user.getRole());

                logger.info("redirection to " + page);

                resp.sendRedirect(page);

            } catch (NotFoundUserException e) {
                req.setAttribute("notFound", true);
                CommandUtil.goToPage(req, resp, "/");
            } catch (NamingException | DatabaseException throwable) {
                throw new RuntimeException(throwable);
            }
        }
    }
}
