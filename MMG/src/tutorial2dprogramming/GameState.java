/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming;

import java.awt.Graphics;
import tutorial2dprogramming.entities.Player;
import tutorial2dprogramming.world.World;

/**
 *
 * @author mario
 */
public class GameState extends State{
    
    private Player player;
    //private World world;
    //private Tree tree;
    
    public GameState(Handler handler){
        super(handler);
        //this.world = world ; //Wnew World(handler, "res/worlds/world1/");
      //  handler.setWorld(world);
        //player = new Player(handler, 100, 100);
    }

    @Override
    public void tick() {
        this.handler.getWorld().tick();//world.tick();
        //player.tick();
    }

    @Override
    public void render(Graphics g) {
        handler.getWorld().render(g);//world.render(g);
        //player.render(g);
    }
    
}
