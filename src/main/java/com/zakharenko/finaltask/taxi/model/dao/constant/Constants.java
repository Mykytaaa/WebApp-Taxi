package com.zakharenko.finaltask.taxi.model.dao.constant;

public class Constants {

    public static final String INSERT_USER =
            "INSERT INTO user (login, email, password, phone_number, role, status) " +
                                               "VALUES (?, ?, ?, ?, ?, ?)";
    public static final String INSERT_TAXI =
            "INSERT INTO taxi (status, capacity, car_type) VALUES (?, ?, ?)";
    public static final String INSERT_ORDER =
            "INSERT INTO t_order (time_of_order, number_of_passengers, price, departure, arrival, distance) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
    public static final String INSERT_USER_HAS_ORDER =
            "INSERT INTO user_has_order (user_id, order_id) VALUES (?, ?)";
    public static final String INSERT_CHECK_CAR =
            "INSERT INTO check_car (order_id, taxi_id) VALUES (?, ?)";

    public static final String GET_USER_BY_LOGIN_AND_PASS =
            "SELECT * FROM user WHERE login = ? AND password = ?";
    public static final String GET_ALL_USER_ORDER =
            "SELECT id, time_of_order, " +
                    "number_of_passengers, " +
                    "departure, " +
                    "arrival, " +
                    "price," +
                    "distance " +
                    "FROM t_order " +
                    "INNER JOIN user_has_order ON t_order.id = user_has_order.order_id " +
                    "WHERE user_has_order.user_id = ?";

    public static final String GET_USER_BY_ID =
            "SELECT * FROM user WHERE id = ?";
    public static final String GET_TAXI_BY_ID =
            "SELECT * FROM taxi WHERE id = ?";
    public static final String GET_ORDER_BY_ID =
            "SELECT * FROM t_order WHERE id = ?";
    public static final String GET_ALL_USERS =
            "SELECT * FROM user";
    public static final String GET_ALL_TAXI =
            "SELECT * FROM taxi";
    public static final String GET_ALL_ORDER =
            "SELECT * FROM t_order";
    public static final String GET_TAXI_BY_CAPACITY =
            "SELECT * FROM taxi WHERE capacity = ?";
    public static final String GET_TAXI_BY_CAR_TYPE =
            "SELECT * FROM taxi WHERE car_type = ?";
    public static final String GET_USER_BY_EMAIL =
            "SELECT * FROM user WHERE email = ?";
    public static final String GET_USER_ORDERS =
            "SELECT * FROM user_has_order WHERE user_id = ?";
    public static final String GET_USER_ORDERS_BY_DATE =
            "SELECT * FROM user_has_order WHERE time_of_order = ?";

    public static final String UPDATE_ORDER =
            "UPDATE t_order SET " +
                    "time_of_order = ?, number_of_passengers = ?, price = ?, departure = ?, arrival = ?, distance = ? WHERE id = ?";
    public static final String UPDATE_USER =
            "UPDATE user SET " +
                    "login = ?, email = ?, password = ?, phone_number = ?, role = ?, status = ? WHERE id = ?";
    public static final String UPDATE_TAXI =
            "UPDATE taxi SET " +
                    "status = ?, capacity = ?, car_type = ? WHERE id = ?";
    public static final String UPDATE_TAXI_STATUS =
            "UPDATE taxi SET status = ? WHERE id = ?";

    public static final String DELETE_USER =
            "DELETE FROM user WHERE id = ?";
    public static final String DELETE_TAXI =
            "DELETE FROM taxi WHERE id = ?";
    public static final String DELETE_ORDER =
            "DELETE FROM t_order WHERE id = ?";
}
