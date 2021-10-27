package com.zakharenko.finaltask.taxi.model.dao.connection;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Connector {
    public static final Logger logger = Logger.getLogger(Connector.class);

    private static Connector connector;
    private final DataSource dataSource;

    private Connector() throws NamingException{
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        dataSource = (DataSource) envContext.lookup("jdbc/Taxi");
    }

    public static synchronized Connector getInstance() throws NamingException {
        if (connector == null) {
            connector = new Connector();
        }
        return connector;
    }

    public Connection getConnection() throws SQLException {
        logger.info("Creating connection");
        return dataSource.getConnection();
    }
}
