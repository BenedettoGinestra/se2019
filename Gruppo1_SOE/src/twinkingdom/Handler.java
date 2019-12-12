/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom;

import twinkingdom.gfx.GameCamera;
import twinkingdom.input.KeyManager;
import twinkingdom.world.World;

/**
 *
 * @author mario
 * classe che permette di accedere alle classi  Game, World, GameCamera e KeyManager
 */
public class Handler {
    
    private Game game;
    private World world;

    public Handler(Game game) {
        this.game = game;
    }
    

    
    public GameCamera getGameCamera(){
        return game.getGameCamera();
    }
    
    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }
    
    public int getWidth(){
        return game.getWidth();
    }
    
    public int getHeight(){
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }
    
    public void setWorld(World world){
        this.world = world;
    }

  
}
