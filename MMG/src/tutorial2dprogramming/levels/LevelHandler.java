/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.levels;

import java.util.LinkedList;
import tutorial2dprogramming.Handler;
import tutorial2dprogramming.saves.Checkpoint;

/**
 * L'handler legge il checkpoint e il livello lo scrive. Level ha un metodo
 * getSonoInGioco, che mi dice se sono ancora in gioco oppure no. Level mi dice
 * quando sono arrivato alla fine del livello, per consentirmi di far passare il
 * gioco al livello successivo.
 *
 * @author Antonia
 */
public class LevelHandler {

    //attributi aggiunti per la gestione della timeline
    private LinkedList<Level> l = new LinkedList<Level>();
    private Level currentLevel = null;
    private Checkpoint ck;
    public static boolean lifeLost = false;
    public static boolean gameOver = false;
    public static boolean changeLevel = false;
    private int numLives = 3;

    //Handler
    private Handler handler;
    private int levelID = 0;

    public LevelHandler(LinkedList<Level> l, Handler handler) {
        this.handler = handler;
        ck = new Checkpoint();
        this.l = l;
        init();
    }

    public void setLevel(int levelID) {
        currentLevel = l.get(levelID);
        currentLevel.init(numLives);
    }

    public Level getLevel() {
        return currentLevel;
    }

    // inizializza il livello
    public void init() {

        String[] saves = ck.loadCheckpoint();

        if (saves == null) {
            levelID = 0;
        } else {

            levelID = Integer.parseInt(saves[2]);
        }

        setLevel(levelID);

    }

    //cambia il livello puntando a quello successivo nella lista
    public void updateLevel() {
        levelID++;

        setLevel(levelID);
    }

    public void returnBack() {
        String[] saves = ck.loadCheckpoint();
        levelID = Integer.parseInt(saves[2]);

        setLevel(levelID);

    }

    //il gameOver deve essere gestito dal Game
    public void gameOver() {
        setLevel(0);
    }

    public void checkPlayerLives() {

        if (changeLevel) {
            changeLevel = false;
            updateLevel();
        } else if (lifeLost) {
            if (currentLevel.world.getEntityManager().getPlayer().getNumLives() > 1) {
                numLives--;
                lifeLost = false;
                returnBack();
            } else {
                lifeLost = false;
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

}
