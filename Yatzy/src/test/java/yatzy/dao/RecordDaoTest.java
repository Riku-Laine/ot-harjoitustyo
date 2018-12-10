/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.dao;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import yatzy.domain.Player;
import yatzy.domain.Record;

/**
 *
 * @author Riku_L
 */
public class RecordDaoTest {

    @Rule
    public TemporaryFolder dbFolder = new TemporaryFolder();

    public RecordDaoTest() {
    }

    private RecordDao recordDao;
    private Database database;
    private Record testRecord;
    private File dbFile;

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws ClassNotFoundException, IOException {
        dbFile = dbFolder.newFile("recordTestDatabase.db");
        System.out.println(dbFile.getName());
        this.database = new Database("jdbc:sqlite:" + dbFile.getAbsolutePath());

        this.recordDao = new RecordDao(database);

        this.testRecord = new Record(new Player("testPerson"), "fakeScorecard", 9, 9, 9, 99);
    }

    @Test
    public void initialDataBaseExists() {
        try {
            this.recordDao.findAll();
        } catch (SQLException ex) {
            fail();
        }
    }

    @Test
    public void canInsertRecords() {
        try {
            this.recordDao.saveOrUpdate(testRecord);
        } catch (SQLException ex) {
            fail();
        }
    }

    @Test
    public void findsInserted() {
        Record found = null;
        try {
            assertEquals(null, this.recordDao.findOne(testRecord));
            this.recordDao.saveOrUpdate(testRecord);
            found = this.recordDao.findOne(testRecord);
        } catch (SQLException ex) {
            fail("Fail in connection!");
        }

        if (found == null) {
            fail("Nothing was returned!");
        }
    }

    @Test
    public void findsAllInserted() {
        ArrayList<Record> found = new ArrayList<>();

        Record testRecord2 = new Record(new Player("testPerson_2"), "fakeScorecard", 1, 1, 1, 11);
        try {
            this.recordDao.saveOrUpdate(testRecord);
            this.recordDao.saveOrUpdate(testRecord2);
            found = this.recordDao.findAll();
        } catch (SQLException ex) {
            fail("Fail in connection!");
        }

        assertEquals("testPerson", found.get(0).getPlayer().getName());
        assertEquals("testPerson_2", found.get(1).getPlayer().getName());
    }

    @Test
    public void canUpdateRecord() {
        Record recordUpdate = new Record(new Player("testPerson"), "fakeScorecard", 9, 9, 9, 100);
        try {
            this.recordDao.saveOrUpdate(testRecord);
            assertEquals(99, this.recordDao.findOne(testRecord).getPoints());
            this.recordDao.saveOrUpdate(recordUpdate);
            assertEquals(100, this.recordDao.findOne(testRecord).getPoints());
        } catch (SQLException ex) {
            fail("Fail in updating!");
        }
    }

    @Test
    public void nullReturnTests() {
        Record fakeRecord = new Record(new Player("fakePerson"), "fakeScorecard", 9, 9, 9, 100);
        try {
            assertEquals(null, this.recordDao.findAll());
            assertEquals(null, this.recordDao.findOne(null));
            assertEquals(null, this.recordDao.findOne(fakeRecord));
        } catch (SQLException ex) {
            fail("Fail in returning null!");
        }
    }

    @Test
    public void canDeleteRecord() {
        try {
            this.recordDao.saveOrUpdate(testRecord);
            assertEquals(testRecord, this.recordDao.findOne(testRecord));
            this.recordDao.delete(testRecord);
            assertEquals(null, this.recordDao.findOne(testRecord));
        } catch (SQLException ex) {
            fail("Fail in deletion!");
        }
    }

    @After
    public void tearDown() {
        dbFolder.delete();
    }

}
