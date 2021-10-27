package com.zakharenko.finaltask.taxi.service.impl;

import com.zakharenko.finaltask.taxi.model.dao.UserDao;
import com.zakharenko.finaltask.taxi.model.dao.exception.DatabaseException;
import com.zakharenko.finaltask.taxi.model.dao.factory.DaoFactory;
import com.zakharenko.finaltask.taxi.model.entity.User;
import com.zakharenko.finaltask.taxi.service.UserService;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    Logger logger = Logger.getLogger(UserServiceImpl.class);

    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private final UserDao userDao = daoFactory.getUserDao();

    @Override
    public boolean add(User entity) throws NamingException {
        logger.info("UserServiceImpl#add started");
        return userDao.add(entity);
    }

    @Override
    public User getById(int id) throws NamingException, DatabaseException {
        logger.info("UserServiceImpl#getById started");
        try{
            return userDao.getById(id);
        }catch (SQLException e){
            logger.error("UserServiceImpl#getById ", e.getCause());
            throw new DatabaseException();
        }
    }

    @Override
    public List<User> getAll() throws NamingException, DatabaseException {
        logger.info("UserServiceImpl#getAll started");
        try{
            return userDao.getAll().stream().filter(a -> a.getRole().equals("user"))
                    .collect(Collectors.toList());
        }catch (SQLException e){
            logger.error("UserServiceImpl#getById ", e.getCause());
            throw new DatabaseException();
        }
    }

    @Override
    public boolean update(User entity) throws NamingException {
        logger.info("UserServiceImpl#update started");
        return userDao.update(entity);
    }

    @Override
    public boolean delete(Integer id) throws NamingException {
        logger.info("UserServiceImpl#delete started");
        return userDao.delete(id);
    }

    @Override
    public User getByLoginAndPass(String login, String password) throws NamingException, DatabaseException {
        logger.info("UserServiceImpl#getByLoginAndPass started");
        try{
            return userDao.getByLoginAndPass(login, password);
        }catch (SQLException e){
            logger.error("UserServiceImpl#delete ", e.getCause());
            throw new DatabaseException();
        }
    }

    @Override
    public User getUserByEmail(String email) throws NamingException, DatabaseException {
        logger.info("UserServiceImpl#getUserByEmail started");
        try{
            return userDao.getUserByEmail(email);
        }catch (SQLException e){
            logger.error("UserServiceImpl#getUserByEmail ", e.getCause());
            throw new DatabaseException();
        }
    }
}
