/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming;

import tutorial2dprogramming.gfx.ImageLoader;
import tutorial2dprogramming.display.Display;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Observer;
import java.util.logging.Logger;
import tutorial2dprogramming.gfx.Assets;
import tutorial2dprogramming.gfx.GameCamera;
import tutorial2dprogramming.gui.GameGUI;
import tutorial2dprogramming.gui.GameScenePanel;
import tutorial2dprogramming.input.KeyManager;
import tutorial2dprogramming.levels.BossLevel;
import tutorial2dprogramming.levels.Level;
import tutorial2dprogramming.levels.LevelHandler;
import tutorial2dprogramming.levels.WorldLevel;
import tutorial2dprogramming.world.World;

/**
 *
 * @author mario
 */
public class Game implements Runnable {

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

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
    }

    public void init() throws IOException {
        /*display = new Display(title, width, height);
         display.getFrame().addKeyListener(keyManager);*/

        this.gui = new GameGUI();
        GameScenePanel gameScene = (GameScenePanel) this.gui.getGameScenePanel();
        this.width = gui.getCanvas().getSize().width;
        this.height = gui.getCanvas().getSize().height;

        gui.getFrame().addKeyListener(keyManager);
        Assets.init();
        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);
        gameState = new GameState(handler);
        State.setState(gameState);
        
        lh = new LevelHandler(this.createLevelSequence(), handler);
        


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
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
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

        wl = new WorldLevel(0, new World(handler, "res/worlds/world1/"), handler);
       // bl = new BossLevel(1, new World(handler, "res/worlds/world2.txt"), handler);

        l.add(wl);
        //l.add(bl);

        return l;
    }

    public GameGUI getGui() {
        return gui;
    }
    
    
}