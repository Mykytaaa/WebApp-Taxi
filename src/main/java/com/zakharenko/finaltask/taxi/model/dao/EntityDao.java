package com.zakharenko.finaltask.taxi.model.dao;

import com.zakharenko.finaltask.taxi.model.entity.Entity;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface EntityDao<T, E extends Entity> {

    boolean add(E entity) throws NamingException;

    E getById(int id) throws NamingException, SQLException;

    List<E> getAll() throws NamingException, SQLException;

    boolean update(E entity) throws NamingException;

    boolean delete(T id) throws NamingException;

}
