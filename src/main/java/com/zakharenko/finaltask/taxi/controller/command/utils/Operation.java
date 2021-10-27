package com.zakharenko.finaltask.taxi.controller.command.utils;

public enum Operation {

    LOGIN("/view/login"),
    REGISTRATION("/view/registration"),
    LOGOUT("/view/logout"),

    ADMIN_MENU("/view/admin/mainPageAdmin"),
    ADMIN_PROFILE("/view/admin/profileAdmin"),

    CLIENT_MENU("/view/client/mainPageUser"),
    CLIENT_PROFILE("/view/client/profileUser"),

    ORDERING_TAXI("/view/makingOrder"),
    ORDERING_HISTORY("/view/orderingHistory"),
    CONFIRMATION_ORDER("/view/confirmationOrder");

    private final String command;

    public String getCommand() {
        return command;
    }

    Operation(String command) {
        this.command = command;
    }

}
