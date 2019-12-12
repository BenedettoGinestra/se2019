/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import tutorial2dprogramming.saves.Checkpoint;

/**
 *
 * @author bened
 */
public class LevelNamePanel extends ObservingPanel {
    private Checkpoint ck;
    public LevelNamePanel(Checkpoint ck) {
        super();
        this.ck=ck;

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.drawString(LevelUtilities.levels.get(ck.getLevelID()+1), 85, 20);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
