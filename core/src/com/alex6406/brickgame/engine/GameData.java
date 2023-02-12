package com.alex6406.brickgame.engine;

public class GameData {
    private final Config config;
    private int[] data;

    public GameData(Config config2) {
        this.config = config2;
    }

    public int getLevel() {
        return this.data[0];
    }

    public int getScore() {
        return this.data[1];
    }

    public float getTime() {
        return (float) this.data[2];
    }

    public void setData(int[] data2) {
        this.data = data2;
        this.config.addScore(data2);
    }
}
