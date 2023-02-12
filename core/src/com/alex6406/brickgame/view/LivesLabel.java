package com.alex6406.brickgame.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;

public class LivesLabel extends Actor {
    private final TextureRegion liveTex;
    private int lives;

    public LivesLabel(Loader loader, float x, float y) {
        this.liveTex = loader.getTile("live");
        setPosition(x, y);
        setSize((float) this.liveTex.getRegionWidth(), (float) this.liveTex.getRegionHeight());
    }

    public LivesLabel(Loader loader) {
        this(loader, 0.0f, 0.0f);
    }

    public void setValue(int value) {
        this.lives = value;
    }

    public void draw(Batch batch, float parentAlpha) {
        for (int a = 0; a < this.lives; a++) {
            batch.draw(this.liveTex, ArkanoidGame.getInstance().getPpuX() * ((getX() - (((float) a) * (15.0f + getWidth()))) - getWidth()), ArkanoidGame.getInstance().getPpuY() * getY(), ArkanoidGame.getInstance().getPpuX() * getWidth(), ArkanoidGame.getInstance().getPpuY() * getHeight());
        }
    }
}
