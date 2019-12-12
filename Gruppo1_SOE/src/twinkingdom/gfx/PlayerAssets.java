/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mario
 */
public class PlayerAssets extends EntityAssets{
    
    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage[] player_right_stable, player_down_stable, player_left_stable, player_up_stable;
    public static BufferedImage[] player_down_attack, player_up_attack, player_left_attack, player_right_attack;
    public static Map<String, Map<String, BufferedImage[]>> playerAnimations;
    
    public void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/twinkingdom/images/spadaccino.png"));
        //player = sheet.crop(0, 610, 128, 128);
        player_down = new BufferedImage[11];
        player_up = new BufferedImage[11];
        player_left = new BufferedImage[11];
        player_right = new BufferedImage[11];
        player_up_stable = new BufferedImage[11];
        player_right_stable = new BufferedImage[11];
        player_left_stable = new BufferedImage[11];
        player_down_stable = new BufferedImage[11];
        player_down_attack = new BufferedImage[11];
        player_up_attack= new BufferedImage[11];
        player_left_attack= new BufferedImage[11];
        player_right_attack=new BufferedImage[11];
        
        
        int startingX=0;
        int startingY=1000;
        int height=128;
        int width = 128;
        int startingYStable=500;
        int startingYAttack=0;
        for(int i=0;i<11;i++){
            
            player_right_attack[i] =  sheet.crop(startingX + i*width, startingYAttack, width, height);
            player_down_attack[i] =  sheet.crop(startingX + i*width, startingYAttack + height, width, height);
            player_up_attack[i] =  sheet.crop(startingX + i*width, startingYAttack+ 2*height, width, height);
            player_left_attack[i] =  sheet.crop(startingX + i*width, startingYAttack+ 3*height, width, height);
            
            player_right_stable[i] =  sheet.crop(startingX + i*width, startingYStable, width, height);
            player_down_stable[i] =  sheet.crop(startingX + i*width, startingYStable + height, width, height);
            player_up_stable[i] =  sheet.crop(startingX + i*width, startingYStable+ 2*height, width, height);
            player_left_stable[i] =  sheet.crop(startingX + i*width, startingYStable+ 3*height, width, height);
            

            player_down[i] =  sheet.crop(startingX + i*width, startingY, width, height);
            player_left[i] =  sheet.crop(startingX + i*width, startingY + height, width, height);
            player_right[i] =  sheet.crop(startingX + i*width, startingY + 2*height, width, height);
            player_up[i] =  sheet.crop(startingX + i*width, startingY + 3*height, width, height);

        }
        
        
        playerAnimations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> playerUp = new HashMap();
        playerUp.put("run", player_up);
        playerUp.put("stable", player_up_stable);
        playerUp.put("attack", player_up_attack);
        playerAnimations.put("up", playerUp);
        
        //Down state
        Map<String, BufferedImage[]> playerDown = new HashMap();
        playerDown.put("run", player_down);
        playerDown.put("stable", player_down_stable);
        playerDown.put("attack", player_down_attack);
        playerAnimations.put("down", playerDown);
        
        //Left State
        Map<String, BufferedImage[]> playerLeft = new HashMap();
        playerLeft.put("run", player_left);
        playerLeft.put("stable", player_left_stable);
        playerLeft.put("attack", player_left_attack);
        playerAnimations.put("left", playerLeft);
        
        //Right State
        Map<String, BufferedImage[]> playerRight = new HashMap();
        playerRight.put("run", player_right);
        playerRight.put("stable", player_right_stable);
        playerRight.put("attack", player_right_attack);
        playerAnimations.put("right", playerRight);
   
    }
    
    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations(){
        return playerAnimations;
    }
    
}
