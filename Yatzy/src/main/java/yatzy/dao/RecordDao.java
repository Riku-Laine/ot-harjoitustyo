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
import java.util.*;
import java.sql.*;
import yatzy.domain.Player;

public class RecordDao implements Dao<Player, Integer> {

    @Override
    public Player findOne(Integer key) throws SQLException {
        // not implemented
        return null;
    }

    @Override
    public List<Player> findAll() throws SQLException {
        // not implemented
        return null;
    }

    @Override
    public Player saveOrUpdate(Player object) throws SQLException {
        // not implemented
        return null;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // not implemented
    }
}
