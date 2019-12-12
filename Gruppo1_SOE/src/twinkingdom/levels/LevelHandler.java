/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.levels;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import twinkingdom.Handler;
import twinkingdom.gui.GameGUI;
import twinkingdom.gui.Health;
import twinkingdom.saves.Checkpoint;

/**
 * L'handler legge il checkpoint e il livello lo scrive. Level ha un metodo
 * getSonoInGioco, che mi dice se sono ancora in gioco oppure no. Level mi dice
 * quando sono arrivato alla fine del livello, per consentirmi di far passare il
 * gioco al livello successivo.
 *
 * @author Antonia
 */
public class LevelHandler extends Observable implements Observer {

    //attributi aggiunti per la gestione della timeline
    private LinkedList<Level> l = new LinkedList<Level>();
    private Level currentLevel = null;
    //private Checkpoint ck1;
    private Checkpoint ck;
   // private GameCheckpoint ck1;
    public static boolean lifeLost = false;
    public static boolean gameOver = false;
    public static boolean changeLevel = false;
    private int numLives=0;
    //Handler
    private Handler handler;
    private int levelID = 0;
    
    //Life
//    private Life life;

    //Class observers' list
    private List<Observer> observers;
    
    public LevelHandler(LinkedList<Level> l, Handler handler, Checkpoint ck) {
        this.handler = handler;
        this.ck = ck;
        this.l = l;
        observers=new LinkedList<Observer>();
        init();
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }
    
    public void setLevel(int levelID) {
        
        System.out.println("SONO NEL SETLEVEL");
        
        currentLevel = l.get(levelID);
        currentLevel.init(numLives,this);

        for (Observer o : observers) {
            o.update(this, this);
        }
        
    }

    public Level getLevel() {
        return currentLevel;
    }

    // inizializza il livello
    public void init() {

      System.out.println("CHECKPOINT DAL LEVEL HANDLER INIT");

        levelID=ck.getLevelID();
        numLives=ck.getLife();
        
        setLevel(levelID);

    }

    //cambia il livello puntando a quello successivo nella lista
    public void updateLevel() {
        
        System.out.println("Sono nell'update Level del LH");
        levelID++;

        if (levelID<l.size())
            setLevel(levelID);
        else {
            levelID=0;
            setLevel(levelID);
        }
        
    }

    public void returnBack() {
        
        
        
        System.out.println("CHECKPOINT DAL LEVEL HANDLER RETURN BACK");
        
        
        
        setLevel(levelID);

    }

    //il gameOver deve essere gestito dal Game
    public void gameOver() {
        
        System.out.println("SONO NEL GAMEOVER");
        
        levelID=0; 
        
        setLevel(levelID);
        
        init();
    }

    //da cancellare
    public void checkPlayerLives() {

        if (changeLevel) {
            changeLevel = false;
            updateLevel();
        } else if (lifeLost) {
            lifeLost = false; //portato qui da me
            if (currentLevel.world.getEntityManager().getPlayer().getNumLives() > 1) {
                numLives--;   
               // life.setLifes(numLives);
                returnBack();
            } else {
                numLives = 3;
                gameOver = false;
                gameOver();
            }

        }

        //Provvisorio per prova poi non servirà più
        if (gameOver) {
            numLives = 3;
            gameOver = false;
            gameOver();
        }
    }
    
    public int getLevelID() {
        return levelID;
    }

    public int getNumLives() {
        return numLives;
    }
    
   @Override
    public void update(Observable o, Object arg) {
 
        
        try {
            
        Health h=(Health) o;
        numLives=h.getLives();
        
        if (numLives>=1) {
        for (Observer ob : observers) {
            ob.update(this, this);
           }
         }
        }
        catch (Exception ex) {
            updateLevel();
        }
        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
