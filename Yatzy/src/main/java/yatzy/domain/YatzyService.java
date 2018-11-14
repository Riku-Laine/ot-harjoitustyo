/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.domain;

import java.util.Random;

/**
 *
 * @author Riku_L
 */
public class YatzyService {
    
    public void startOnePlayerGame(){
        Player player = new Player("ShouldThisBeHere"); // pelaajaluokka pois? Ei, koska kaksi pelaajaa
        
        System.out.println("Peli alkaa");
                
        Random random = new Random();
        
        int nmbrs = (int) Math.round(random.nextDouble()*6+0.5);
        
        System.out.println(nmbrs);
    }
    
}
