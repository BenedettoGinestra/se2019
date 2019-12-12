/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mario
 */
public class Boss2Assets extends EntityAssets{
    
    public static BufferedImage[] boss_right;
    private static Map<String, Map<String, BufferedImage[]>> bossAnimations;
    
    public void init(){
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] boss_down = new BufferedImage[5];
        BufferedImage[] boss_up = new BufferedImage[5];
        BufferedImage[] boss_left = new BufferedImage[5];
        boss_right = new BufferedImage[5];
        BufferedImage[] boss_up_stable = new BufferedImage[5];
        BufferedImage[] boss_right_stable = new BufferedImage[5];
        BufferedImage[] boss_left_stable = new BufferedImage[5];
        BufferedImage[] boss_down_stable = new BufferedImage[5];
        BufferedImage[] boss_down_attack = new BufferedImage[5];
        BufferedImage[] boss_up_attack= new BufferedImage[5];
        BufferedImage[] boss_left_attack= new BufferedImage[5];
        BufferedImage[] boss_right_attack=new BufferedImage[5];
        
        
        int startingX=10;
        int startingY=0;
        int height=250;
        int width = 250;
        int startingXStable = 0;
        int startingYStable=2060;
        int startingXAttack=10;
        int startingYAttack=0;
        SpriteSheet sheet1, sheet2, sheet = null;            
        sheet = new SpriteSheet(ImageLoader.loadImage("/tutorial2dprogramming/images/Enemy_Ranged_v01.png"));


        for(int i=0;i<=4;i++){
            boss_up_attack[i] =  sheet.crop(startingXAttack + i*width, startingYAttack , width -30, height);
            boss_down_attack[i] =  sheet.crop(startingXAttack + i*width, startingYAttack + height, width - 30, height);
            boss_right_attack[i] =  sheet.crop(startingXAttack + i*width, startingYAttack + 2*height, width, height);
            boss_left_attack[i] =  sheet.crop(startingXAttack + i*width, startingYAttack+ 3*height, width, height);
            
            
            
            //sheet = new SpriteSheet(ImageLoader.loadImage("/tutorial2dprogramming/images/EBros_Boss_samurai/Boss_Samurai_Walk.png"));
            if(i<=11){
            boss_up_stable[i] =  sheet.crop(startingXStable + i*width, startingYStable, width - 30 , height);
            boss_down_stable[i] =  sheet.crop(startingXStable + i*width, startingYStable +height, width - 30, height);
            boss_right_stable[i] =  sheet.crop(startingXStable + i*width, startingYStable + 2*height, width - 30, height);
            boss_left_stable[i] =  sheet.crop(startingXStable + i*width, startingYStable+ 3*height, width, height);
            }

            
            boss_up[i] =  sheet.crop(startingX + i*width, startingY , width - 30, height);
            boss_down[i] =  sheet.crop(startingX + i*width, startingY +height, width - 30, height);
            boss_right[i] =  sheet.crop(startingX + i*width, startingY + 2*height, width, height);
            boss_left[i] =  sheet.crop(startingX + i*width, startingY + 3*height, width, height);

        }
        
        
        bossAnimations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> bossUp = new HashMap();
        bossUp.put("run", boss_up);
        bossUp.put("stable", boss_up_stable);
        bossUp.put("attack", boss_up_attack);
        bossAnimations.put("up", bossUp);
        
        //Down state
        Map<String, BufferedImage[]> bossDown = new HashMap();
        bossDown.put("run", boss_down);
        bossDown.put("stable", boss_down_stable);
        bossDown.put("attack", boss_down_attack);
        bossAnimations.put("down", bossDown);
        
        //Left State
        Map<String, BufferedImage[]> bossLeft = new HashMap();
        bossLeft.put("run", boss_left);
        bossLeft.put("stable", boss_left_stable);
        bossLeft.put("attack", boss_left_attack);
        bossAnimations.put("left", bossLeft);
        
        //Right State
        Map<String, BufferedImage[]> bossRight = new HashMap();
        bossRight.put("run", boss_right);
        bossRight.put("stable", boss_right_stable);
        bossRight.put("attack", boss_right_attack);
        bossAnimations.put("right", bossRight);
   
    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return bossAnimations;
    }
    
}