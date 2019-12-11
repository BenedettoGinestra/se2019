/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.levels;

import java.awt.Graphics;
import tutorial2dprogramming.Handler;
import tutorial2dprogramming.world.World;

/**
 *
 * @author Antonia
 */
public class BossLevel extends Level {

    public BossLevel(int id, World world, Handler handler) {
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
