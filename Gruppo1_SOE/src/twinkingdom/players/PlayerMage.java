/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.players;

import twinkingdom.Handler;
import twinkingdom.entities.Player;
import twinkingdom.gfx.PlayerAssets;
import twinkingdom.players.state.DownMageState;
import twinkingdom.players.state.LeftMageState;
import twinkingdom.players.state.RightMageState;
import twinkingdom.players.state.UpMageState;

/**
 *
 * @author mario
 */
public class PlayerMage extends Player{

    public PlayerMage(Handler handler, float x, float y, PlayerAssets pAssets, int numLives) {
        super(handler, x, y, pAssets, numLives);
        rightState=new RightMageState(this, pAssets);
        leftState=new LeftMageState(this, pAssets);
        downState=new DownMageState(this, pAssets);
        upState=new UpMageState(this, pAssets);
        setState(downState);
    }
    
    public PlayerMage(Player player){
        this(player.getHandler(), player.getX(), player.getY(), new PlayerAssets(), player.getNumLives());
        setHealth(player.getHealth());
    }
    
}
