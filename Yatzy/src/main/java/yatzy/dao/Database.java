/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.dao;

/**
 * Database functionalities.
 *
 * @author Riku_L
 */
import java.sql.*;

public class Database {

    private final String dbAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.dbAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbAddress);
    }
}
