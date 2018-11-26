/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
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
        dc2 = new DiceCollection(20, 6);
    }
    
    @Test
    public void hello() {}
}
