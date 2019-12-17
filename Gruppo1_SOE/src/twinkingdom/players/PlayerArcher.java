/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.players;

import twinkingdom.Handler;
import twinkingdom.entities.Player;
import twinkingdom.gfx.ArrowAssets;
import twinkingdom.gfx.PlayerAssets;
import twinkingdom.players.state.DownArcherState;
import twinkingdom.players.state.LeftArcherState;
import twinkingdom.players.state.RightArcherState;
import twinkingdom.players.state.UpArcherState;

/**
 *
 * @author mario
 */
public class PlayerArcher extends Player{

    public PlayerArcher(Handler handler, float x, float y, PlayerAssets pAssets, int numLife) {
        super(handler, x, y, pAssets, numLife);
        ArrowAssets arrowAsset = new ArrowAssets();
        arrowAsset.init();
        rightState=new RightArcherState(this, pAssets);
        leftState=new LeftArcherState(this, pAssets);
        downState=new DownArcherState(this, pAssets);
        upState=new UpArcherState(this, pAssets);
        setState(downState);
    }
    
    
    
    
    
}
