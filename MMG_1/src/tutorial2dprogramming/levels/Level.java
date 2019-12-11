package tutorial2dprogramming.levels;


import java.awt.Graphics;
import tutorial2dprogramming.Handler;
import tutorial2dprogramming.saves.Checkpoint;
import tutorial2dprogramming.world.World;

public abstract class Level {

    private int ID;
    private int life, lifeBar;
    protected World world;
    private Checkpoint checkpoint;
    private boolean levelCompleted, playerAlive;
    private Handler handler;

    public Level(int ID, World world, Handler handler) {
        this.ID = ID;
        this.world = world;
        this.handler = handler;
    }

    // Ricordare di modificare la variabile running in tick
    public abstract void tick();

    public abstract void render(Graphics g);

    public void init(int life, LevelHandler lh) {
        handler.setWorld(this.world);
        world.init();
        world.setPortalObserver(lh);
        
        this.life = life;
       
    }

    public synchronized void startLevel() {

    }

    public synchronized void endLevel() {

        //LevelHandler.howLevelEnded(levelCompleted, playerAlive);
    }

}
