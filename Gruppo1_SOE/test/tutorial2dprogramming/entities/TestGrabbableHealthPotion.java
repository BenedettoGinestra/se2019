/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.entities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tutorial2dprogramming.Game;
import tutorial2dprogramming.Handler;
import tutorial2dprogramming.gfx.PlayerAssets;
import tutorial2dprogramming.staticentities.grabbable.GrabbableHealthPotion;

/**
 *
 * @author aless
 */
public class TestGrabbableHealthPotion {
    
    private static GrabbableHealthPotion potion;
    private static Player player;
    private static PlayerAssets playerAssets;
    
    public TestGrabbableHealthPotion() {
        
        potion = new GrabbableHealthPotion (new Handler(new Game("Title", 500, 500)), 1, 1, 15, 22);
        player = new Player(new Handler(new Game("Title", 500, 500)), 0.0f, 0.0f, playerAssets);
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        playerAssets = new PlayerAssets();
        playerAssets.init();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testActionOnCollision() {
        System.out.println("---------actionOnCollision()---------");
        
        player.setHealth(6);
        System.out.println("Health: " + player.getHealth());
        System.out.println("Max health: " + player.getMaxHealth());
        System.out.println("");
        
        System.out.println("First test");
        potion.actionOnCollision(player);
        assertEquals(player.getHealth(), 9);
        System.out.println("");
        
        System.out.println("Second test");
        potion.actionOnCollision(player);
        assertEquals(player.getHealth(), player.getMaxHealth());
    }
}
