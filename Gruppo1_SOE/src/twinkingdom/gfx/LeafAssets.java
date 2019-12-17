/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.gfx;

/**
 *
 * @author mario
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mario
 */
public class LeafAssets extends EntityAssets{
    

    private static Map<String, Map<String, BufferedImage[]>> leafAnimations;
    
    @Override
    public void init(){
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] leaf_down = new BufferedImage[4];
        BufferedImage[] leaf_up = new BufferedImage[4];
        BufferedImage[] leaf_left = new BufferedImage[4];
        BufferedImage[] leaf_right = new BufferedImage[4];
        BufferedImage[] leaf_up_stable = new BufferedImage[4];
        BufferedImage[] leaf_right_stable = new BufferedImage[4];
        BufferedImage[] leaf_left_stable = new BufferedImage[4];
        BufferedImage[] leaf_down_stable = new BufferedImage[4];
        BufferedImage[] leaf_down_attack = new BufferedImage[4];
        BufferedImage[] leaf_up_attack= new BufferedImage[4];
        BufferedImage[] leaf_left_attack= new BufferedImage[4];
        BufferedImage[] leaf_right_attack=new BufferedImage[4];
        
        
        int startingX=0;
        int startingY=0;
        int height= 32;
        int width = 32;
        int startingXStable = 0;
        int startingYStable=0;
        int startingXAttack=0;
        int startingYAttack=0;
        SpriteSheet sheet = null;            
        sheet = new SpriteSheet(ImageLoader.loadImage("/twinkingdom/images/foglia.png"));


        for(int i=0;i<4;i++){
            leaf_down_attack[i] =  sheet.crop(startingXAttack , startingYAttack , width, height);
            leaf_right_attack[i] =  sheet.crop(startingXAttack , startingYAttack , width, height);
            leaf_up_attack[i] =  sheet.crop(startingXAttack  , startingYAttack, width, height);
            leaf_left_attack[i] =  sheet.crop(startingXAttack  , startingYAttack, width, height);
            
            
            
            //sheet = new SpriteSheet(ImageLoader.loadImage("/twinkingdom/images/EBros_leaf_samurai/leaf_Samurai_Walk.png"));
            leaf_down_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);
            leaf_right_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);
            leaf_up_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);
            leaf_left_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack, width, height);

            
            leaf_down[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);
            leaf_right[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);
            leaf_up[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);
            leaf_left[i] =  sheet.crop(startingXAttack + i*width , startingYAttack, width, height);

        }
        
        
        leafAnimations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> leafUp = new HashMap();
        leafUp.put("run", leaf_up);
        leafUp.put("stable", leaf_up_stable);
        leafUp.put("attack", leaf_up_attack);
        leafAnimations.put("up", leafUp);
        
        //Down state
        Map<String, BufferedImage[]> leafDown = new HashMap();
        leafDown.put("run", leaf_down);
        leafDown.put("stable", leaf_down_stable);
        leafDown.put("attack", leaf_down_attack);
        leafAnimations.put("down", leafDown);
        
        //Left State
        Map<String, BufferedImage[]> leafLeft = new HashMap();
        leafLeft.put("run", leaf_left);
        leafLeft.put("stable", leaf_left_stable);
        leafLeft.put("attack", leaf_left_attack);
        leafAnimations.put("left", leafLeft);
        
        //Right State
        Map<String, BufferedImage[]> leafRight = new HashMap();
        leafRight.put("run", leaf_right);
        leafRight.put("stable", leaf_right_stable);
        leafRight.put("attack", leaf_right_attack);
        leafAnimations.put("right", leafRight);
   
    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return leafAnimations;
    }
    

}

