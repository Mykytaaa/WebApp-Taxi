package com.zakharenko.finaltask.taxi.model.entity;

public enum TaxiStatus {
    AVAILABLE("available"),
    NOT_AVAILABLE("not_available"),
    ON_TRIP("on_trip");

    private final String condition;

    TaxiStatus(String status){
        this.condition = status;
    }

    public String getTaxiStatus(){
        return condition;
    }
}
