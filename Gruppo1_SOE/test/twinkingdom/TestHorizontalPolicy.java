/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import twinkingdom.Game;
import twinkingdom.Handler;
import twinkingdom.entities.enemy.level1.Bat;
import twinkingdom.gfx.BatAssets;
import twinkingdom.gfx.PlayerAssets;
import twinkingdom.policy.HorizontalPolicy;

/**
 *
 * @author aless
 */
public class TestHorizontalPolicy {
    
    private static HorizontalPolicy Hp1, Hp2;
    private static Player player;
    private static Bat bat;
    private static PlayerAssets playerAssets;
    private static BatAssets batAssets;
    
    public TestHorizontalPolicy() {
        
        player = new Player(new Handler(new Game("Title", 500, 500)), 0.0f, 0.0f, playerAssets);
        bat = new Bat(new Handler(new Game("Title", 500, 500)), 1, 1, 30, 35, batAssets);
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        playerAssets = new PlayerAssets();
        batAssets = new BatAssets();
        
        playerAssets.init();
        batAssets.init();
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
    @Test
    public void testGetMovement() {
        System.out.println("");
        System.out.println("--------getMovement()--------");
        
        System.out.println("State of player is: " + player.getState());
        System.out.println("X of player is: " +  player.getX());
        System.out.println("");
        
        player.setX(10);
        Hp1 = new HorizontalPolicy(player, 0, 10); 
        
        System.out.println("State of player is: " +  player.state);
        System.out.println("X of player is: " + player.getX());
        System.out.println("");
        
        bat.state = bat.rightState;
        
        System.out.println("State of bat is: " + bat.getState());
        System.out.println("X of player is: " +  bat.getX());
        System.out.println("");
        
        bat.setX(50);
        Hp2 = new HorizontalPolicy(bat, 20, 30);
        
        System.out.println("State of player is: " +  player.state);
        System.out.println("X of player" + player.getX());
        System.out.println("");
    }
}
