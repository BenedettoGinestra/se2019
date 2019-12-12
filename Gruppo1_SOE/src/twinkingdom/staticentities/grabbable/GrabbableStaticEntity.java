/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.staticentities.grabbable;

import java.awt.Graphics;
import twinkingdom.Handler;
import twinkingdom.staticentities.StaticEntity;
import twinkingdom.staticentities.StaticEntity;

/**
 *
 * @author SSQ1
 */
public abstract class GrabbableStaticEntity extends StaticEntity{

    public GrabbableStaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        isGrabbable=true;
    }
    
    

    
    
}
