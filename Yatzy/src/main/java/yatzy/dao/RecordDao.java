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
import java.util.logging.Level;
import java.util.logging.Logger;
import yatzy.domain.Player;
import yatzy.domain.Record;

public class RecordDao implements Dao<Record, Record> {

    private final Database recordDB;

    public RecordDao(Database db) {
        this.recordDB = db;
        initDB();
    }

    @Override
    public Record findOne(Record record) throws SQLException {
        Connection connection = recordDB.getConnection();
        PreparedStatement searchQuery = connection.prepareStatement("SELECT * FROM Records WHERE name = ? AND scorecard_type = ? AND"
                + " dice_amount = ? AND max_dice_number = ? AND throws_amount = ? ");

        searchQuery.setString(1, record.getPlayer().getName());
        searchQuery.setString(2, record.getScorecardType());
        searchQuery.setInt(3, record.getDiceAmount());
        searchQuery.setInt(4, record.getMaxEyeNumber());
        searchQuery.setInt(5, record.getThrowAmount());

        ResultSet rs = searchQuery.executeQuery();
        boolean found = rs.next();
        if (!found) {
            return null;
        }

        Record r = new Record(new Player(rs.getString("name")),
                rs.getString("scorecard_type"), rs.getInt("dice_amount"),
                rs.getInt("max_dice_number"), rs.getInt("throws_amount"),
                rs.getInt("points"));

        rs.close();
        searchQuery.close();
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
                    rs.getString("scorecard_type"), rs.getInt("dice_amount"),
                    rs.getInt("max_dice_number"), rs.getInt("throws_amount"),
                    rs.getInt("points")));
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

        Record found = findOne(record);

        if (found == null) {
            insertRecord(record);
        } else if (found.getPoints() < record.getPoints()) {
            updateRecord(record);
        }
    }

    @Override
    public void delete(Record record) throws SQLException {
        Connection connection = recordDB.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Records WHERE name = ? AND scorecard_type = ? AND "
                + "dice_amount = ? AND max_dice_number = ? AND throws_amount = ? ");

        stmt.setString(1, record.getPlayer().getName());
        stmt.setString(2, record.getScorecardType());
        stmt.setInt(3, record.getDiceAmount());
        stmt.setInt(4, record.getMaxEyeNumber());
        stmt.setInt(5, record.getThrowAmount());

        stmt.executeUpdate();

        stmt.close();
        connection.close();
    }

    private void insertRecord(Record record) throws SQLException {
        Connection connection = recordDB.getConnection();
        PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO "
                + "Records (name, scorecard_type, dice_amount, max_dice_number, throws_amount, points) VALUES (?, ?, ?, ?, ?, ?)");

        insertStmt.setString(1, record.getPlayer().getName());
        insertStmt.setString(2, record.getScorecardType());
        insertStmt.setInt(3, record.getDiceAmount());
        insertStmt.setInt(4, record.getMaxEyeNumber());
        insertStmt.setInt(5, record.getThrowAmount());
        insertStmt.setInt(6, record.getPoints());
        insertStmt.executeUpdate();
        insertStmt.close();
    }

    private void updateRecord(Record record) throws SQLException {
        Connection connection = recordDB.getConnection();
        PreparedStatement updateStmt = connection.prepareStatement("UPDATE Records SET points = ? WHERE name = ? AND scorecard_type = ? AND "
                + "dice_amount = ? AND max_dice_number = ? AND throws_amount = ? ");

        updateStmt.setInt(1, record.getPoints());
        updateStmt.setString(2, record.getPlayer().getName());
        updateStmt.setString(3, record.getScorecardType());
        updateStmt.setInt(4, record.getDiceAmount());
        updateStmt.setInt(5, record.getMaxEyeNumber());
        updateStmt.setInt(6, record.getThrowAmount());

        updateStmt.executeUpdate();
        updateStmt.close();
    }

    /**
     * Create database if it doesn't exist.
     */
    private void initDB() {
        try {
            Connection connection = recordDB.getConnection();
            PreparedStatement initStmt = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Records ("
                    + "	id integer PRIMARY KEY,"
                    + "	name varchar(200),"
                    + "	scorecard_type varchar(200),"
                    + "	dice_amount integer,"
                    + "	max_dice_number integer,"
                    + "	throws_amount integer,"
                    + "	points integer"
                    + ")");
            initStmt.execute();
        } catch (SQLException ex) {
            System.out.println("Error in database creation!");
        }

    }
}
