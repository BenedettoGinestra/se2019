/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.levels;

import java.awt.Graphics;
import twinkingdom.Handler;
import twinkingdom.world.InterWorld;
import twinkingdom.world.World;

/**
 *
 * @author Antonia
 */
public class WorldLevel extends Level {

       private InterWorld world;
        private Handler handler;
        
    public WorldLevel(int id, InterWorld world, Handler handler) {
        super(id);
        this.world = world;
        this.handler=handler;
    }

    @Override
    public void tick() {
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
        super.init(lh);
        handler.setWorld(this.world);
        world.init();
        world.setPortalObserver(lh);
        world.setHealthObserver(lh);
    }
    
    @Override
    public void end() {
        world=null;
    }
    
    @Override
    public InterWorld getWorld () {
        return world;
    }
    

}
