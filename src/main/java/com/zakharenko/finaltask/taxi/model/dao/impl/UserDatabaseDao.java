package com.zakharenko.finaltask.taxi.model.dao.impl;

import com.zakharenko.finaltask.taxi.model.dao.UserDao;
import com.zakharenko.finaltask.taxi.model.dao.connection.Connector;
import com.zakharenko.finaltask.taxi.model.entity.User;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.zakharenko.finaltask.taxi.model.dao.constant.Constants.*;

public class UserDatabaseDao implements UserDao {

    Logger logger = Logger.getLogger(UserDatabaseDao.class);

    @Override
    public boolean add(User entity) throws NamingException {
        logger.info("UserDatabaseDao#add");
        logger.info(entity.getLogin());
        logger.info(entity.getEmail());
        logger.info(entity.getPassword());
        logger.info(entity.getPhone_number());
        logger.info(entity.getRole());
        logger.info(entity.getStatus());
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)){

            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setString(4, entity.getPhone_number());
            preparedStatement.setString(5, entity.getRole());
            preparedStatement.setString(6, entity.getStatus());

            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e){
            logger.error("UserDatabaseDao#add failed " + e.getCause());
            return false;
        }
        logger.info("UserDatabaseDao#add success");
        return true;
    }

    @Override
    public User getById(int id) throws NamingException, SQLException{
        logger.info("UserDatabaseDao#getById with " + id + " argument start");
        List<User> users;
        User user;
        try(Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)){

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            users = createUserList(resultSet);
            if(!users.isEmpty()){
                user = users.get(0);
            }else {
                logger.warn("User by id wasn't found. Returning null");
                return null;
            }

        }
        logger.info("UserDatabaseDao#getById with " + id + "success");
        return user;
    }

    @Override
    public List<User> getAll() throws NamingException, SQLException {
        logger.info("UserDatabaseDao#getAll");
        List<User> users;

        try(Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS)){

            ResultSet resultSet = preparedStatement.executeQuery();

            users = createUserList(resultSet);

        }
        return users;
    }

    @Override
    public boolean update(User entity) throws NamingException {
        logger.info("UserDatabaseDao#update");
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setString(4, entity.getPhone_number());
            preparedStatement.setString(5, entity.getRole());
            preparedStatement.setString(6, entity.getStatus());
            preparedStatement.setInt(7, entity.getId());

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.error("UserDatabaseDao#update failed, returning false");
            return false;
        }
        logger.info("update " + entity.getEmail() + " success");
        return true;
    }

    @Override
    public boolean delete(Integer id) throws NamingException {
        logger.info("UserDatabaseDao#delete");
        try(Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)){

            preparedStatement.setInt(1 , id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e){
            logger.error("UserDatabaseDao#delete failed, returning false");
            return false;
        }

        logger.info("UserDatabaseDao#delete " + id + " success");
        return true;
    }

    @Override
    public User getByLoginAndPass(String login, String password) throws NamingException, SQLException {
        logger.info("UserDatabaseDao#getByLoginAndPass with " + login + " " + password + " arguments start");

        List<User> users;
        User user;
        try(Connection connection = Connector.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN_AND_PASS)){
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            users = createUserList(resultSet);

            if(!users.isEmpty()){
                user = users.get(0);
            } else {
                logger.warn("User by login and password wasn't found. Returning null");
                return null;
            }
        }

        logger.info("getByLoginAndPass with " + login + " " + password + " arguments success");
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws NamingException, SQLException {
        logger.info("getUserByEmail with " + email + " arguments start");

        List<User> users;
        User user;
        try(Connection connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL)){
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            users = createUserList(resultSet);
            if(!users.isEmpty()){
                user = users.get(0);
            }else {
                logger.warn("User by email wasn't found. Returning null");
                return null;
            }

        }
        logger.info("getByLoginAndPass with " + email + " " + " arguments success");
        return user;
    }


    private List<User> createUserList(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        User user;

        while(resultSet.next()){
            user = new User.UserBuilderImpl()
                    .setId(resultSet.getInt(1))
                    .setLogin(resultSet.getString(2))
                    .setPassword(resultSet.getString(4))
                    .setEmail(resultSet.getString(3))
                    .setPhone(resultSet.getString(5))
                    .setRole(resultSet.getString(6))
                    .setStatus(resultSet.getString(7))
                    .build();
            userList.add(user);
        }
        return userList;
    }
}
