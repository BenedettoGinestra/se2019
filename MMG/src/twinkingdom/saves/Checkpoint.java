package tutorial2dprogramming.saves;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import tutorial2dprogramming.levels.LevelHandler;

public class Checkpoint implements Observer, Serializable{
    
    private int life, lifeBar, levelID;
    private String path = "saves/Checkpoint.bin";
    
    public Checkpoint (int life, int lifeBar, int levelID) {
        this.life=life;
        this.lifeBar=lifeBar;
        this.levelID=levelID;
        saveCheckpoint();
    }
    
    public Checkpoint() {
        
    }
    
    
    public Checkpoint loadCheckpoint() {
        String line = "";
        Checkpoint ck1;
        
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File(path)))) {
            ck1=(Checkpoint)ois.readObject();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Checkpoint.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        
        setVariables(ck1);
        
        return ck1;
    }

    public void saveCheckpoint() {
        
        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(new File(path)))) {
            oos.writeObject(this);
            oos.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    public int getLife() {
        return life;
    }

    public int getLifeBar() {
        return lifeBar;
    }

    public int getLevelID() {
        return levelID;
    }

    public void setVariables(Checkpoint ck1){
        setLife(ck1.getLife());
        setLevelID(ck1.getLevelID());
        setLifeBar(ck1.getLifeBar());
    }
    
    public void setLife(int life) {
        this.life = life;
    }

    public void setLifeBar(int lifeBar) {
        this.lifeBar = lifeBar;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }

    @Override
    public void update(Observable o, Object arg) {
        LevelHandler lh=(LevelHandler) o;
        setLevelID(lh.getLevelID());
        setLifeBar(10);
        setLife(lh.getNumLives());
        saveCheckpoint();
    }
    
   }