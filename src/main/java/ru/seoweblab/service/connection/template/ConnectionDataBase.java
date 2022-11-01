package ru.seoweblab.service.connection.template;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public interface ConnectionDataBase{
    Connection getConnection() throws SQLException;
}
