package com.zakharenko.finaltask.taxi.model.entity;

import com.zakharenko.finaltask.taxi.model.entity.builder.OrderBuilder;

import java.sql.Date;


public class Order extends Entity{
    private String departure;
    private String arrival;
    private Date time_of_order;
    private int number_of_passengers;
    private int userId;
    private int taxiId;
    private double price;
    private double distance;

    public Order(){}

    private Order(Order.OrderBuilderImpl builder){
        super(builder.id);
        this.departure = builder.departure;
        this.arrival = builder.arrival;
        this.taxiId = builder.taxiId;
        this.time_of_order = builder.time_of_order;
        this.number_of_passengers = builder.number_of_passengers;
        this.price = builder.price;
        this.distance = builder.distance;
        this.userId = builder.userId;
    }

    public static class OrderBuilderImpl implements OrderBuilder {
        private int id;
        private String departure;
        private String arrival;
        private Date time_of_order;
        private int number_of_passengers;
        private int userId;
        private int taxiId;
        private double price;
        private double distance;

        public OrderBuilder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        @Override
        public OrderBuilder setTaxiId(int taxiId) {
            this.taxiId = taxiId;
            return this;
        }

        public OrderBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public OrderBuilder setDeparture(String departure) {
            this.departure = departure;
            return this;
        }

        public OrderBuilder setArrival(String arrival) {
            this.arrival = arrival;
            return this;
        }

        @Override
        public OrderBuilder setTime_of_order(Date time_of_order) {
            this.time_of_order = time_of_order;
            return this;
        }

        public OrderBuilder setNumber_of_passengers(int number_of_passengers) {
            this.number_of_passengers = number_of_passengers;
            return this;
        }

        public OrderBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public OrderBuilder setDistance(double distance) {
            this.distance = distance;
            return this;
        }

        public Order build(){
            return new Order(this);
        }
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Date getTime_of_order() {
        return time_of_order;
    }

    public void setTime_of_order(Date time_of_order) {
        this.time_of_order = time_of_order;
    }

    public int getNumber_of_passengers() {
        return number_of_passengers;
    }

    public void setNumber_of_passengers(int number_of_passengers) {
        this.number_of_passengers = number_of_passengers;
    }

    public double getPrice() {
        return price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(int taxiId) {
        this.taxiId = taxiId;
    }

}