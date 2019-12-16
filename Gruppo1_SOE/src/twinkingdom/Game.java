/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom;

import twinkingdom.gfx.ImageLoader;
import twinkingdom.display.Display;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;
import twinkingdom.gfx.Assets;
import twinkingdom.gfx.GameCamera;
import twinkingdom.gui.GameGUI;
import twinkingdom.gui.GameScenePanel;
import twinkingdom.gui.Health;
import twinkingdom.input.KeyManager;
import twinkingdom.input.UtilKeyManager;
import twinkingdom.levels.BossLevel;
import twinkingdom.levels.Level;
import twinkingdom.levels.LevelHandler;
import twinkingdom.levels.WorldLevel;
import twinkingdom.saves.Checkpoint;
import twinkingdom.world.Dungeon;
import twinkingdom.world.InterWorld;
import twinkingdom.world.World;

/**
 *
 * @author mario
 */
public class Game implements Runnable, Observer {

    private Display display;
    private int width, height;
    private Thread thread;
    private boolean running = false;
    private String title;
    private BufferStrategy bs;
    private Graphics g;
    private Image image;
    private State gameState;
    private KeyManager keyManager;
    private GameCamera gameCamera;
    private Handler handler;

    private GameGUI gui;

    private LevelHandler lh;
    private Checkpoint checkpoint;
    private Checkpoint ck;
    
    private InterWorld world1;
    private Dungeon world2;
    private UtilKeyManager ukm;
    

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        checkpoint=new Checkpoint();
    }

    public void init() throws IOException {
        /*display = new Display(title, width, height);
         display.getFrame().addKeyListener(keyManager);*/
        ck=checkpoint.loadCheckpoint();
        if(ck==null)
            ck= new Checkpoint(3,10,0);

        this.gui = new GameGUI(ck);
        GameScenePanel gameScene = (GameScenePanel) this.gui.getGameScenePanel();
        this.width = gui.getCanvas().getSize().width;
        this.height = gui.getCanvas().getSize().height;

        gui.getFrame().addKeyListener(keyManager);
        Assets.init();
        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);
        gameState = new GameState(handler);
        State.setState(gameState);
        
        lh = new LevelHandler(this.createLevelSequence(), handler, ck);
        lh.addObserver(gui.getLevelNamePanel());
        lh.addObserver(ck);
        
       // ukm=new UtilKeyManager(lh);
       // gui.getFrame().addKeyListener(ukm);
        
    }

    public void tick() {
        keyManager.tick();
        
        if(lh.getLevel() != null)
            lh.getLevel().tick();
      

    }

    public void render() {
        //Buffer strategy è un buffer che permette di scrivere sul buffer prima di scriver sullo screen
        bs = gui.getCanvas().getBufferStrategy();
        if (bs == null) {
            gui.getCanvas().createBufferStrategy(3);
            return;
        }
        //Graphics is like a pennarello magico che può disegnare cose sul canvas
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        g.drawImage(image, 0, 0, null);

        if (State.getState() != null) {
            State.getState().render(g);
        } 
        
        //Draw HERE
        //g.drawImage(image, 0, 0, null);
        //Stop draw
        bs.show();
        //g.dispose();

    }

    @Override
    public void run() {

        try {
            init();
        } catch (IOException ex) {
            System.exit(1);
        }

        int fps = 60;

        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;

            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                delta--;
                ticks++;
            }
            if (timer >= 1000000000) {
              //  System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
           // lh.checkPlayerLives();
        }

        stop();

    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (Exception ex) {
        }
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
       public LinkedList<Level> createLevelSequence() {
        LinkedList<Level> l = new LinkedList<>();
        WorldLevel wl;
        BossLevel bl;

        world1=new InterWorld(handler, "res/worlds/world1/",this,ck);
        world2=new Dungeon(handler, "res/worlds/world2/",this,ck);
        
        
        wl = new WorldLevel(0, world1, handler);
        bl = new BossLevel(1, world2 , handler);

        l.add(wl);
        l.add(bl);

        return l;
    }

    public GameGUI getGui() {
        return gui;
    }
 
    @Override
    public void update(Observable o, Object arg) {
       
       Health h=(Health) o;
       
       if(h.getLives()<1) {
           System.out.println("GAME OVER");
           ck=new Checkpoint(3,10,0);
           System.exit(0);
       }
       
   }
    
    public Checkpoint getCheckpoint() {
        return ck;
    }
    
    public LevelHandler getLevelHandler() {
        return lh;
    }
       
}
