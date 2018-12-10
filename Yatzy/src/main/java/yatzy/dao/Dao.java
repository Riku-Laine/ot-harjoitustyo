/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.dao;

import java.sql.*;
import java.util.*;

/**
 * Basic Data Access Object Interface.
 *
 * @author Riku_L
 * @param <T> Retrievable object.
 * @param <K> Key for the object.
 */
public interface Dao<T, K> {

    T findOne(K key) throws SQLException;

    List<T> findAll() throws SQLException;

    void saveOrUpdate(T object) throws SQLException;

    void delete(K key) throws SQLException;
}
