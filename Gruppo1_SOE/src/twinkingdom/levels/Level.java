package twinkingdom.levels;


import java.awt.Graphics;
import twinkingdom.Handler;
import twinkingdom.saves.Checkpoint;
import twinkingdom.world.World;

public abstract class Level {

 private int ID;
    
    private int life;
    /*
    private Checkpoint checkpoint;
    private boolean levelCompleted, playerAlive;
   // protected Handler handler;
*/
    public Level(int ID) {
        this.ID = ID;
    }

    // Ricordare di modificare la variabile running in tick
    public abstract void tick();

    public abstract void render(Graphics g);

 
    public void init(LevelHandler lh) {
        //to override
    }

    public synchronized void startLevel() {

    }

    public synchronized void end() {

        //LevelHandler.howLevelEnded(levelCompleted, playerAlive);
    }
    
    public World getWorld () {
        return null;
    }
}
