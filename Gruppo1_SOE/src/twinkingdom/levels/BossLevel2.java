/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.levels;

import java.awt.Graphics;
import twinkingdom.Handler;
import twinkingdom.world.Dungeon2;
import twinkingdom.world.Dungeon3;

/**
 *
 * @author Antonia
 */
public class BossLevel2 extends Level{
    
     private Dungeon2 world;
     private Handler handler;
    
    public BossLevel2(int id, Dungeon2 world, Handler handler) {
        super(id,handler);
        this.world = world;
        this.handler=handler;
    }

    @Override
    public void tick() {
        super.tick();
        if(world!=null)
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        if(world!=null)
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
    }
    
    
    @Override
    public void stop() {
        world.clearWorld();
    }
       
}
