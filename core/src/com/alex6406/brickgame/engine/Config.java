package com.alex6406.brickgame.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import java.lang.reflect.Array;
import com.alex6406.brickgame.view.ScoreLabel;

public class Config {
    private boolean fullscreen;
    private float musicVolume;
    private final Preferences pref = Gdx.app.getPreferences("ArkanoidConfig");
    private int[][] score;
    private float soundVolume;
    private boolean vSync;

    public Config() {
        load();
    }

    public void apply() {
        Gdx.graphics.setVSync(this.vSync);
        //TODO
        //Gdx.graphics.setDisplayMode(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), this.fullscreen);
    }

    private void load() {
        this.musicVolume = this.pref.getFloat("musicVolume", 0.5f);
        this.soundVolume = this.pref.getFloat("soundVolume", 0.8f);
        this.fullscreen = this.pref.getBoolean("fullscreen", false);
        this.vSync = this.pref.getBoolean("vSync", true);
        this.score = (int[][]) Array.newInstance(Integer.TYPE, new int[]{8, 3});
        for (int i = 0; i < this.score.length - 1; i++) {
            for (int j = 0; j < this.score[0].length; j++) {
                this.score[i][j] = this.pref.getInteger(String.format("score_%d-%d", Integer.valueOf(i), Integer.valueOf(j)));
            }
        }
    }

    private void save() {
        this.pref.putFloat("musicVolume", this.musicVolume);
        this.pref.putFloat("soundVolume", this.soundVolume);
        this.pref.putBoolean("fullscreen", this.fullscreen);
        this.pref.putBoolean("vSync", this.vSync);
        this.pref.flush();
    }

    public void setSettings(float musicVolume2, float soundVolume2, boolean fullscreen2, boolean vSync2) {
        this.musicVolume = musicVolume2;
        this.soundVolume = soundVolume2;
        this.fullscreen = fullscreen2;
        this.vSync = vSync2;
        save();
    }

    private void saveScore(int[][] score2) {
        for (int i = 0; i < score2.length - 1; i++) {
            for (int j = 0; j < score2[0].length; j++) {
                this.pref.putInteger(String.format("score_%d-%d", Integer.valueOf(i), Integer.valueOf(j)), score2[i][j]);
            }
        }
        this.pref.flush();
    }

    public void addScore(int[] data) {
        this.score[this.score.length - 1] = data;
        sortScore(this.score);
        saveScore(this.score);
    }

    private void sortScore(int[][] score2) {
        for (int i = 0; i < score2.length; i++) {
            for (int j = 0; j < (score2.length - i) - 1; j++) {
                if (score2[j][1] < score2[j + 1][1]) {
                    int[] item = score2[j];
                    score2[j] = score2[j + 1];
                    score2[j + 1] = item;
                }
            }
        }
        score2[score2.length - 1] = null;
    }

    public void resetScore() {
        for (int i = 0; i < this.score.length - 1; i++) {
            for (int j = 0; j < this.score[0].length; j++) {
                this.score[i][j] = 0;
            }
        }
        saveScore(this.score);
    }

    public String[] scoreToString() {
        String format;
        String[] str = new String[(this.score.length - 1)];
        for (int i = 0; i < str.length; i++) {
            if (this.score[i][0] == 0) {
                format = null;
            } else {
                format = String.format("[%d]: %s / %d sec", Integer.valueOf(this.score[i][0]), ScoreLabel.format(this.score[i][1]), Integer.valueOf(this.score[i][2]));
            }
            str[i] = format;
        }
        return str;
    }

    public float getMusicVolume() {
        return this.musicVolume;
    }

    public float getSoundVolume() {
        return this.soundVolume;
    }

    public boolean getFullscreen() {
        return this.fullscreen;
    }

    public boolean getvSync() {
        return this.vSync;
    }

    public void setMusicVolume(float musicVolume2) {
        this.musicVolume = musicVolume2;
    }

    public void setSoundVolume(float soundVolume2) {
        this.soundVolume = soundVolume2;
    }

    public void setFullscreen(boolean fullscreen2) {
        this.fullscreen = fullscreen2;
    }

    public void setvSync(boolean vSync2) {
        this.vSync = vSync2;
    }
}
