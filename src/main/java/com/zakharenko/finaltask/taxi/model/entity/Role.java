package com.zakharenko.finaltask.taxi.model.entity;

public enum Role {

    USER("user"),
    ADMIN("admin");

    private final String accessLevel;

    Role(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getAccessLevel(){
        return accessLevel;
    }
}
