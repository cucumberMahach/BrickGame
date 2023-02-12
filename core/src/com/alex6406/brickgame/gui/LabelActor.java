package com.alex6406.brickgame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;

public class LabelActor extends Actor {
    private String caption;
    private final BitmapFont font;
    private float offset;

    public LabelActor(float x, float y, String font2, boolean centered) {
        setPosition(x, y);
        this.font = new BitmapFont(Gdx.files.internal(font2));
        setCaption(Loader.PREFIX);
        if (centered) {
            this.offset = this.font.getCapHeight() / 2.0f;
        }
    }

    public void draw(Batch batch, float parentAlpha) {
        this.font.draw(batch, this.caption, getX() * ArkanoidGame.getInstance().getPpuX(), (getY() + this.offset) * ArkanoidGame.getInstance().getPpuY());
    }

    public void setColor(float r, float g, float b, float a) {
        this.font.setColor(r, g, b, a);
        super.setColor(r, g, b, a);
    }

    public void setColor(Color color) {
        this.font.setColor(color);
        super.setColor(color);
    }

    public void setCaption(String caption2) {
        this.caption = caption2;
    }
}
