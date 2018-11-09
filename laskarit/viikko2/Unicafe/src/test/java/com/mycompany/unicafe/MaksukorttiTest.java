package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());      
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(15);
        assertEquals("saldo: 0.25", kortti.toString());      
    }
    
    @Test
    public void saldoVaheneeOikeinKunRahaaRiittavasti() {
        kortti.otaRahaa(5);
        assertEquals(5, kortti.saldo());      
    }
    
    @Test
    public void saldoEiVaheneKunRahaaEiOleRiittavasti() {
        kortti.otaRahaa(15);
        assertEquals("saldo: 0.10", kortti.toString());      
    }
    
    @Test
    public void saldoPalauttaaSaldon() {
        assertEquals(10, kortti.saldo());      
    }
}
