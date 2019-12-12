/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import twinkingdom.levels.Level;
import twinkingdom.saves.Checkpoint;

/**
 *
 * @author aless
 */
public class TestCheckpoint {
    
    private static Checkpoint cp1, cp2, cp3;
    private static int lives1, lives2, lives3;
    private static int hp1, hp2, hp3;
    private static int levelID1, levelID2, levelID3;

    
    public TestCheckpoint() {
        
        cp1 = new Checkpoint(lives1, hp1, levelID1);
        cp2 = new Checkpoint(lives2, hp2, levelID2);
        cp3 = new Checkpoint(lives3, hp3, levelID3);
    }
    
    @BeforeClass
    public static void setUpClass() {

        lives1 = 3;
        hp1 = 100;
        levelID1 = 0;
        
        lives2 = 2;
        hp2 = 80;
        levelID2 = 1;
        
        lives3 = 0;
        hp3 = 0;
        levelID3 = 0;
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
    public void testSaveCheckpoint() {
        System.out.println("");
        System.out.println("--------saveCheckpoint()--------");
        
        cp1.saveCheckpoint();               
        
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File("saves/Checkpoint.bin")))) {
            cp1=(Checkpoint)ois.readObject();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Checkpoint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
        
        assertNotNull(cp1);
        assertEquals(cp1.getLife(), 3);
        assertEquals(cp1.getLifeBar(), 100);
        assertEquals(cp1.getLevelID(), 0);
        
        System.out.println("Lives: " + cp1.getLife());
        System.out.println("Healthpoints: " + cp1.getLifeBar());
        System.out.println("Level: " + cp1.getLevelID());
        System.out.println("");
        
        cp2.saveCheckpoint();
        
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File("saves/Checkpoint.bin")))) {
            cp2=(Checkpoint)ois.readObject();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Checkpoint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
        
        assertNotNull(cp2);
        assertEquals(cp2.getLife(), 2);
        assertEquals(cp2.getLifeBar(), 80);
        assertEquals(cp2.getLevelID(), 1);
        
        System.out.println("Lives: " + cp2.getLife());
        System.out.println("Healthpoints: " + cp2.getLifeBar());
        System.out.println("Level: " + cp2.getLevelID());
        
    }
    
    @Test
    public void testLoadCheckpoint() {
        System.out.println("");
        System.out.println("--------loadCheckpoint()--------");
        
        System.out.println("Lives: " + cp3.getLife());
        System.out.println("Healthpoints: " + cp3.getLifeBar());
        System.out.println("Level: " + cp3.getLevelID());
        System.out.println("");
        
        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(new File("saves/Checkpoint.bin")))) {
            oos.writeObject(cp1);
            oos.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        cp3 = cp3.loadCheckpoint();
                
        assertNotNull(cp3);
        assertEquals(cp3.getLife(), cp1.getLife());
        assertEquals(cp3.getLifeBar(), cp1.getLifeBar());
        assertEquals(cp3.getLevelID(), cp1.getLevelID());
        
        System.out.println("Lives: " + cp3.getLife());
        System.out.println("Healthpoints: " + cp3.getLifeBar());
        System.out.println("Level: " + cp3.getLevelID());
        System.out.println("");
        
        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(new File("saves/Checkpoint.bin")))) {
            oos.writeObject(cp2);
            oos.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        cp3 = cp3.loadCheckpoint();
        
        assertNotNull(cp3);
        assertEquals(cp3.getLife(), cp2.getLife());
        assertEquals(cp3.getLifeBar(), cp2.getLifeBar());
        assertEquals(cp3.getLevelID(), cp2.getLevelID());
        
        System.out.println("Lives: " + cp3.getLife());
        System.out.println("Healthpoints: " + cp3.getLifeBar());
        System.out.println("Level: " + cp3.getLevelID());
        
    }
    
    
}
