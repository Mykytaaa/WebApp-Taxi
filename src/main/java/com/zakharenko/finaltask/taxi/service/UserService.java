package com.zakharenko.finaltask.taxi.service;


import com.zakharenko.finaltask.taxi.model.dao.exception.DatabaseException;
import com.zakharenko.finaltask.taxi.model.entity.User;

import javax.naming.NamingException;

public interface UserService extends EntityService<Integer, User>{
    User getByLoginAndPass(String login, String password) throws NamingException, DatabaseException;

    User getUserByEmail(String email) throws NamingException, DatabaseException;

}
