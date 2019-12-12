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
import twinkingdom.staticentities.grabbable.GrabbableStar;

/**
 *
 * @author aless
 */
public class TestGrabbableStar {
    
    private static GrabbableStar star;
    
    public TestGrabbableStar() {
        
        star = new GrabbableStar(new Handler(new Game("Title", 500, 500)), 1, 1, 20, 20);
    }
    
    @BeforeClass
    public static void setUpClass() {
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
        System.out.println("");
        System.out.println("--------actionOnCollision()--------");
        
        System.out.println("Star is active: " + star.isActive);
        System.out.println("Star is grabbable: " + star.isGrabbable);
        System.out.println("");
        
        star.actionOnCollision(star);
        assertTrue(star.isActive);
    }
}
