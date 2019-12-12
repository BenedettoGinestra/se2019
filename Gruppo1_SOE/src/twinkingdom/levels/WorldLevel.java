/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.levels;

import java.awt.Graphics;
import twinkingdom.Handler;
import twinkingdom.world.World;

/**
 *
 * @author Antonia
 */
public class WorldLevel extends Level {

    public WorldLevel(int id, World world, Handler handler) {
        super(id, world, handler);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }

}
