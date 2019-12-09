/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.levels;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import tutorial2dprogramming.Handler;
import tutorial2dprogramming.gui.GameGUI;
import tutorial2dprogramming.saves.Checkpoint;

/**
 * L'handler legge il checkpoint e il livello lo scrive. Level ha un metodo
 * getSonoInGioco, che mi dice se sono ancora in gioco oppure no. Level mi dice
 * quando sono arrivato alla fine del livello, per consentirmi di far passare il
 * gioco al livello successivo.
 *
 * @author Antonia
 */
public class LevelHandler extends Observable {

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
        
        currentLevel = l.get(levelID);
        currentLevel.init(numLives);

        for (Observer o : observers) {
            o.update(this, this);
        }
        
    }

    public Level getLevel() {
        return currentLevel;
    }

    // inizializza il livello
    public void init() {

        //vecchio checkpoint
        /*    
        String[] saves = ck.loadCheckpoint();
        
        numLives=Integer.parseInt(saves[0]);
        
        System.out.println("IL NUMERO DI VITE è: "+saves[0]);
        */
        
                System.out.println("CHECKPOINT DAL LEVEL HANDLER INIT");

        
     //   ck1=ck.loadCheckpoint();
       
        /*
        if (ck1== null) {
            ck1=new GameCheckpoint(3,120,0);
           // levelID = 0;
           // numLives=3;
        }
*/
        levelID=ck.getLevelID();
        numLives=ck.getLife();
        /*
        life=new Life(numLives);
        life.addObserver(GameGUI.getHealthBar());
        life.setLifes(numLives);
        */
        
        setLevel(levelID);

    }

    //cambia il livello puntando a quello successivo nella lista
    public void updateLevel() {
        
        
        levelID++;

        if (levelID<l.size())
            setLevel(levelID);
        else
            gameOver();
        
    }

    public void returnBack() {
        
        /*
        String[] saves = ck.loadCheckpoint();
        
        levelID = Integer.parseInt(saves[2]);

                */
        
        System.out.println("CHECKPOINT DAL LEVEL HANDLER RETURN BACK");
        
        //ck1=ck.loadCheckpoint();
        
        /*
        if(ck1==null)
            ck1=new GameCheckpoint(3,120,0);
        */
        
        //levelID=ck1.getLevelID();
        
        setLevel(levelID);

    }

    //il gameOver deve essere gestito dal Game
    public void gameOver() {
        
        levelID=0; 
        
        setLevel(levelID);
        
        init();
    }

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
    
}
