/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import twinkingdom.gfx.ImageLoader;
import twinkingdom.utils.GrabbableStarCollection;

/**
 *
 * @author bened
 */
public class StarsPanel extends ObservingPanel {

    private int stars;
    private BufferedImage img;
    private Graphics g;

    public StarsPanel() throws IOException {
        this.stars = 0;
        this.img = ImageLoader.loadImage("/gui/star_icon.png");
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        
        g.drawImage(img, 0, 0, null);
        g.setColor(new Color(255, 222, 45));
        g.drawString(stars + "/3", 18, 55);
        
    }

    @Override
    public void update(Observable o, Object arg) {
        
        System.out.println("SONO NELL'UPDATE DELLO STARSPANEL");
        
        GrabbableStarCollection sc= (GrabbableStarCollection) o;
        
        stars=sc.getSize();
        
        repaint();
        
    }
    


}
