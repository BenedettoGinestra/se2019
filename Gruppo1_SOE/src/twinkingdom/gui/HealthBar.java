/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import twinkingdom.saves.Checkpoint;

/**
 *
 * @author bened
 */
public class HealthBar extends ObservingPanel {
    
    private BufferedImage healthTrapeze, heartIcon;
    public int value, max, lifes;
    private final int barWidth, barHeight;
    private final Color barColor, barBorderColor;
    
    public HealthBar(Checkpoint ck) throws IOException {
        
        this.barWidth = 317;
        this.barHeight = 23;
        this.max = 10;
        this.value = 10;
        this.lifes = ck.getLife();
        this.barColor = new Color(137,0,0);
        this.barBorderColor = new Color(232,142,0);
        
        healthTrapeze = ImageIO.read(this.getClass().getResource("/gui/healthbg.png"));
        heartIcon = ImageIO.read(this.getClass().getResource("/gui/heart_icon.png"));

    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        // Drawing bar filling according to actual left health 
        g.setColor(this.barColor);
        int health = (int)Math.floor((double)this.barWidth/(double)max * (double)value);
        g.fillRect(10,18,health,23);
        
        // Drawing bar contours... Just graphics
        g.setColor(this.barBorderColor);
        g.drawRect(10, 18, this.barWidth, this.barHeight);
        
        // Drawing text information of the remaining life
        g.drawImage(healthTrapeze, 240,41, null);
        g.setColor(Color.white);
        String remainingLife = String.format("%1$"+10+ "s", this.value + "/" + this.max + " HP");
        g.drawString(remainingLife, 251, 53);
        
        // Drawing hearts whose numbers is = number of remaining lifes
        if(this.lifes > 6) {
            g.drawImage(heartIcon, 10, 50, null);
            g.setColor(Color.WHITE);
            g.drawString("X" + this.lifes, 50, 70);
            return;
        }
        for(int i = 0; i < this.lifes; i++) {
            g.drawImage(heartIcon, 10 + i * 40, 50, null);
        }

        
    }

    @Override
    public void update(Observable o, Object arg) {
        Health v = (Health) o; //To change body of generated methods, choose Tools | Templates.
        this.value = v.getHealthPoints();
        this.lifes = v.getLives();
        this.max = v.getMaxHealthPoints();
        this.repaint();
    }
    
}
