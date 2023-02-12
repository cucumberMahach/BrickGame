package com.alex6406.brickgame.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alex6406.brickgame.engine.ArkanoidGame;

public class ImageActor extends Actor {
    private final TextureRegion texture;

    public ImageActor(TextureRegion texture2, float x, float y, float width, float height) {
        this.texture = texture2;
        setPosition(x, y);
        setSize(width, height);
    }

    public ImageActor(TextureRegion texture2) {
        this(texture2, 0.0f, 0.0f, (float) texture2.getRegionWidth(), (float) texture2.getRegionHeight());
    }

    public void draw(Batch batch, float parentAlpha) {
        Batch batch2 = batch;
        batch2.draw(this.texture, ArkanoidGame.getInstance().getPpuX() * getX(), ArkanoidGame.getInstance().getPpuY() * getY(), ArkanoidGame.getInstance().getPpuX() * (getWidth() / 2.0f), ArkanoidGame.getInstance().getPpuY() * (getHeight() / 2.0f), ArkanoidGame.getInstance().getPpuX() * getWidth(), ArkanoidGame.getInstance().getPpuY() * getHeight(), 1.0f, 1.0f, getRotation());
    }
}
