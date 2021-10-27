package com.zakharenko.finaltask.taxi.model.entity;

public enum UserStatus {
    ACTIVE("active"),
    NOT_ACTIVE("not_active");

    private final String condition;

    UserStatus(String condition){
        this.condition = condition;
    }

    public String getUserStatus(){
        return condition;
    }
}
