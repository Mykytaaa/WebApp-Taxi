package com.zakharenko.finaltask.taxi.model.dao;


import com.zakharenko.finaltask.taxi.model.entity.User;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface UserDao extends EntityDao<Integer, User> {

    User getByLoginAndPass(String login, String password) throws NamingException, SQLException;

    User getUserByEmail(String email) throws NamingException, SQLException;

}
