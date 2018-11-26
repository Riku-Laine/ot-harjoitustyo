/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.dao;

/**
 *
 * @author Riku_L
 */

import java.sql.*;

public class RecordDatabase {
    
    private final String dbAddress;

    public RecordDatabase(String databaseAddress) throws ClassNotFoundException {
        this.dbAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbAddress);
    }
}
