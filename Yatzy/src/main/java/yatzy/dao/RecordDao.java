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
import yatzy.domain.Record;

public class RecordDao implements Dao<Record, String> {

    private final Database recordDB;

    public RecordDao(Database db) {
        this.recordDB = db;
    }

    @Override
    public Record findOne(String name) throws SQLException {
        Connection connection = recordDB.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Records where name = ?");

        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        boolean found = rs.next();
        if (!found) {
            return null;
        }

        Record r = new Record(new Player(rs.getString("name")),
                rs.getString("type"), rs.getInt("points"));

        rs.close();
        stmt.close();
        connection.close();

        return r;
    }

    @Override
    public ArrayList<Record> findAll() throws SQLException {
        Connection connection = recordDB.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Records");

        ResultSet rs = stmt.executeQuery();
        ArrayList<Record> records = new ArrayList<>();
        while (rs.next()) {
            records.add(new Record(new Player(rs.getString("name")),
                    rs.getString("type"), rs.getInt("points")));
        }

        rs.close();
        stmt.close();
        connection.close();

        if (records.isEmpty()) {
            return null;
        }
        return records;
    }

    @Override
    public void saveOrUpdate(Record record) throws SQLException {
        Connection connection = recordDB.getConnection();
        PreparedStatement searchQuery = connection.prepareStatement("SELECT * FROM Records WHERE name = ? AND type = ?");

        String name = record.getPlayer().getName();
        String type = record.getScorecardType();
        int points = record.getPoints();

        searchQuery.setString(1, name);
        searchQuery.setString(2, type);

        ResultSet rs = searchQuery.executeQuery();
        boolean found = rs.next();
        rs.close();
        searchQuery.close();
        if (!found) {
            insertRecord(record);
        } else {
            updateRecord(record);

        }

        connection.close();
    }

    @Override
    public void delete(String name) throws SQLException {
        Connection connection = recordDB.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Records WHERE name = ?");

        stmt.setString(1, name);
        stmt.executeUpdate();

        stmt.close();
        connection.close();
    }

    private void insertRecord(Record record) throws SQLException {
        Connection connection = recordDB.getConnection();
        PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO Records (name, type, points) VALUES (?, ?, ?)");

        String name = record.getPlayer().getName();
        String type = record.getScorecardType();
        int points = record.getPoints();

        insertStmt.setString(1, name);
        insertStmt.setString(2, type);
        insertStmt.setInt(3, points);
        insertStmt.executeUpdate();
        insertStmt.close();
    }

    private void updateRecord(Record record) throws SQLException {
        Connection connection = recordDB.getConnection();
        PreparedStatement updateStmt = connection.prepareStatement("UPDATE Records SET points = ? WHERE name = ? AND type = ?");

        String name = record.getPlayer().getName();
        String type = record.getScorecardType();
        int points = record.getPoints();

        updateStmt.setInt(1, points);
        updateStmt.setString(2, name);
        updateStmt.setString(3, type);
        updateStmt.executeUpdate();
        updateStmt.close();
    }
}
