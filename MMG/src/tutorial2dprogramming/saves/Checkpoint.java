package tutorial2dprogramming.saves;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Checkpoint {

    private int life, lifeBar, levelID;
    private String path = "saves/Checkpoint.bin";

    public Checkpoint() {
        // Serve solo per LevelHandler che deve solamente leggere da file
    }

    public Checkpoint(int life, int lifeBar, int levelID) {
        this.life = life;
        this.lifeBar = lifeBar;
        this.levelID = levelID;
    }

    public String[] loadCheckpoint() {
        String line = "";

        try {
            Scanner g = new Scanner(new File(path));
            line = g.nextLine();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return line.split(",");
    }

    public void saveCheckpoint() {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path))) {
            out.write(String.valueOf(life) + "," + String.valueOf(lifeBar) + "," + String.valueOf(levelID));
            out.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
