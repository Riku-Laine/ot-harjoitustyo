package yatzy.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import yatzy.domain.DiceCollection;

/**
 *
 * @author Riku_L
 */
public class DiceCollectionTest {

    public DiceCollectionTest() {
    }

    private DiceCollection dc;
    private DiceCollection dc2;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dc = new DiceCollection();
        dc2 = new DiceCollection(20, 9);
    }

    @Test
    public void initialCollectionNotEmpty() {
        assertFalse(dc.getDies() == null);
        assertFalse(dc2.getDies() == null);
    }

    @Test
    public void collectionSizeInitializedRight() {
        assertEquals(5, dc.getDies().length);
        assertEquals(20, dc2.getDies().length);
    }

    @Test
    public void biggestEyeNumberRight() {
        assertEquals(6, dc.getBiggestEyeNumber());
        assertEquals(9, dc2.getBiggestEyeNumber());
    }

    @Test
    public void initialDiesAreZeroes() {
        for (int i = 0; i < dc.getDies().length; i++) {
            if (dc.getDies()[i] != 0) {
                fail();
            }
        }
        for (int i = 0; i < dc2.getDies().length; i++) {
            if (dc2.getDies()[i] != 0) {
                fail();
            }
        }
    }

    @Test
    public void diesCanBeSet() {
        int[] dies = {1, 2, 3, 4, 5};
        dc.setDies(dies);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, dc.getDies());
    }

    @Test
    public void tooBigEyeNumberThrowsException() {
        int[] dies = {1, 2, 3, 4, 7};
        try {
            dc.setDies(dies);
            fail();
        } catch (Exception ex) {

        }
    }

    @Test
    public void tooLongArrayThrowsException() {
        int[] dies = {1, 2, 3, 4, 5, 6};
        try {
            dc.setDies(dies);
            fail();
        } catch (Exception ex) {

        }
    }

    @Test
    public void diceNumbersAreWithinBoundaries() {
        for (int iteration = 0; iteration < 1000; iteration++) {
            dc.rollAllDies();
            for (int i = 0; i < dc.getDies().length; i++) {
                if (dc.getDies()[i] <= 0 || dc.getDies()[i] > dc.getBiggestEyeNumber()) {
                    fail();
                }
            }
        }
    }
}
