package com.zakharenko.finaltask.taxi.model.entity;

import com.zakharenko.finaltask.taxi.model.entity.builder.TaxiBuilder;

public class Taxi extends Entity{
    private String status;
    private String car_type;
    private int capacity;

    public Taxi(){}

    private Taxi(Taxi.TaxiBuilderImpl builder){
        super(builder.id);
        this.status = builder.status;
        this.car_type = builder.car_type;
        this.capacity = builder.capacity;
    }

    public static class TaxiBuilderImpl implements TaxiBuilder {
        private int id;
        private String status;
        private String car_type;
        private int capacity;

        public TaxiBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public TaxiBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public TaxiBuilder setCar_type(String car_type) {
            this.car_type = car_type;
            return this;
        }

        public TaxiBuilder setCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Taxi build(){
            return new Taxi(this);
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
