/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Riku_L
 */
public class KassapaateTest {
    
    private Kassapaate kassa;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }
    
    @Test
    public void kassanSaldoAlussaOikein() {
        assertEquals(100000, kassa.kassassaRahaa());      
    }
    
    @Test
    public void myytyjenLounaidenMaaraAlussaOikein() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty() +  kassa.edullisiaLounaitaMyyty());      
    }
    
    @Test
    public void kateisostoToimiiEdullinenRahaaRiittavasti() {
        int vaihtoraha = kassa.syoEdullisesti(500);
        
        assertEquals(100240, kassa.kassassaRahaa());
        assertEquals(500-240, vaihtoraha);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoToimiiMaukasRahaaRiittavasti() {
        int vaihtoraha = kassa.syoMaukkaasti(500);
        
        assertEquals(100400, kassa.kassassaRahaa());
        assertEquals(500-400, vaihtoraha);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoToimiiEdullinenRahaEiRiita() {
        int vaihtoraha = kassa.syoEdullisesti(200);
        
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(200, vaihtoraha);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoToimiiMaukasRahaEiRiita() {
        int vaihtoraha = kassa.syoMaukkaasti(200);
        
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(200, vaihtoraha);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoToimiiVeloitusKortinSaldoMaukasRahaaRiittavasti() {
        Maksukortti kortti = new Maksukortti(1000);
        
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(1000-400, kortti.saldo());
        
    }

    @Test
    public void korttiostoToimiiLounaidenMaaraMaukasRahaaRiittavasti() {
        Maksukortti kortti = new Maksukortti(1000);
        
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoToimiiVeloitusKortinSaldoEdullinenRahaaRiittavasti() {
        Maksukortti kortti = new Maksukortti(1000);
        
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(1000-240, kortti.saldo());
        
    }

    @Test
    public void korttiostoToimiiLounaidenMaaraEdullinenRahaaRiittavasti() {
        Maksukortti kortti = new Maksukortti(1000);
        
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoToimiiLounaidenMaaraEdullinenRahaEiRiita() {
        Maksukortti kortti = new Maksukortti(200);
        
        assertFalse(kassa.syoEdullisesti(kortti));
        assertFalse(kassa.syoMaukkaasti(kortti));
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortinLatausToimii() {
        Maksukortti kortti = new Maksukortti(0);
        
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(1000, kortti.saldo());
        assertEquals(101000, kassa.kassassaRahaa());
        
        kassa.lataaRahaaKortille(kortti, -1000);
        
        assertEquals(1000, kortti.saldo());
        assertEquals(101000, kassa.kassassaRahaa());
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
