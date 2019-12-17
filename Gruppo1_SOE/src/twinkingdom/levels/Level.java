package twinkingdom.levels;


import java.awt.Graphics;
import twinkingdom.Handler;

public abstract class Level {

    private int ID;
    protected Handler handler;
    private LevelHandler lh;

    public Level(int ID, Handler handler) {
        this.ID = ID;
        this.handler = handler;
    }

    public void tick() {
        getInput();
    }

     public void init(LevelHandler lh) {
       this.lh=lh;
       
    }
     
    private void getInput() {

        if (handler.getKeyManager().isPressing()) {


            if (handler.getKeyManager().changeLevel) {
                //if(lh!= null)
                lh.updateLevel();

            }

        }
    }
    

    public abstract void render(Graphics g);

    public abstract void stop();

}
