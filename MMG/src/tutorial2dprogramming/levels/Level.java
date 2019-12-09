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

    public void init(int life) {
        handler.setWorld(this.world);
        world.init();
        //world.getEntityManager().getPlayer().setNumLives(life);
        /* Le prossime righe di codice sono tutte pezzotte */
        //world.getEntityManager().getPlayer().setHealth(100);
        //lifeBar = world.getEntityManager().getPlayer().getHealth();
        //this.life = life;
        checkpoint = new Checkpoint(life, lifeBar, ID);
        checkpoint.saveCheckpoint();
        // checkpoint.saveCheckpoint();
        // Caricamento vignette... ancora da definire
    }

    public synchronized void startLevel() {

    }

    public synchronized void endLevel() {

        //LevelHandler.howLevelEnded(levelCompleted, playerAlive);
    }

}
