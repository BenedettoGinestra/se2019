/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.levels;

import java.awt.Graphics;
import java.util.Observable;
import twinkingdom.Handler;
import twinkingdom.gui.Health;
import twinkingdom.utils.GrabbableStarCollection;
import twinkingdom.world.Dungeon1;
import twinkingdom.world.Dungeon3;

/**
 *
 * @author Antonia
 */
public class BossLevel3 extends Level{
    
     private Dungeon3 world;
     private int numLives = 0;
    
    public BossLevel3(int id, Dungeon3 world, Handler handler) {
        super(id,handler);
        this.world = world;
    }

    @Override
    public void tick() {
        super.tick();
        //if(world!=null)
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        //if(world!=null)
        world.render(g);
    }

    @Override
    public void init(LevelHandler lh) {
        System.out.println("SONO NELL'INIT DEL BOSSLEVEL");
        super.init(lh);
        handler.setWorld(this.world);
        world.init();
        world.setPortalObserver(lh);
        world.setHealthObserver(lh);
        world.setBossHealthObserver(this);
    }
    
    
    @Override
    public void stop() {
        world.clearWorld();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        try {
            GrabbableStarCollection gsc = (GrabbableStarCollection) o;
            if (gsc.getSize() == 3) {
                world.getPortal().setUnblocked(true);
            }
        } catch (Exception ex) {
            Health h = (Health) o;
            numLives = h.getLives();

            if (numLives == 0) {
                world.getPortal().setUnblocked(true);
            }
        }
    }
    
}