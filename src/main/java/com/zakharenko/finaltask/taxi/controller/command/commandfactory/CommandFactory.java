package com.zakharenko.finaltask.taxi.controller.command.commandfactory;

import com.zakharenko.finaltask.taxi.controller.command.*;
import com.zakharenko.finaltask.taxi.controller.command.utils.Operation;
import com.zakharenko.finaltask.taxi.model.dao.exception.NotFoundOperationException;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    public static final Logger logger = Logger.getLogger(CommandFactory.class);
    public static final Map<String, Command> allCommandMap = new HashMap<>();

    static {
        allCommandMap.put(Operation.REGISTRATION.getCommand(), new RegistrationCommand());
        allCommandMap.put(Operation.LOGIN.getCommand(), new LoginCommand());
        allCommandMap.put(Operation.LOGOUT.getCommand(), new LogoutCommand());
        allCommandMap.put(Operation.CLIENT_MENU.getCommand(), new ClientMenuCommand());
        allCommandMap.put(Operation.CLIENT_PROFILE.getCommand(), new ClientProfileCommand());
        allCommandMap.put(Operation.ORDERING_TAXI.getCommand(), new MakingOrderCommand());
        allCommandMap.put(Operation.CONFIRMATION_ORDER.getCommand(), new ConfirmationOfOrderCommand());
    }

    private CommandFactory() {
    }


    public static Command getCommand(String url) throws NotFoundOperationException {
        Command command = allCommandMap.get(url);
        logger.info(url);
        if (command == null) {
            throw new NotFoundOperationException();
        }

        return command;
    }
}
