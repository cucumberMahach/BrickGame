package com.alex6406.brickgame.engine;

import com.badlogic.gdx.Gdx;

import java.io.BufferedReader;
import java.io.IOException;

public class WorldConfig {

    private static final String filename = "levels/config.txt";
    private int startLevel = 1;
    private int endLevel = 1;
    public void load() {
        try {
            BufferedReader bf = Gdx.files.internal(filename).reader(1024);
            while(bf.ready()) {
                String line = bf.readLine();
                if (line.contains("startLevel:")) {
                    startLevel = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.contains("endLevel:")) {
                    endLevel = Integer.parseInt(line.split(":")[1].trim());
                }
            }
            bf.close();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public int getStartLevel() {
        return startLevel;
    }

    public int getEndLevel() {
        return endLevel;
    }
}
