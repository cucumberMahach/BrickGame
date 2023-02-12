package com.alex6406.brickgame.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alex6406.brickgame.controller.ButtonListener;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;

public class ButtonActor extends Actor {
    private boolean down;
    private boolean hover;
    private Actor icon;
    private float locHeight;
    private float locPpux;
    private float locPpuy;
    private float locWidth;
    private float locX;
    private float locY;
    private final TextureRegion tex;

    public ButtonActor(TextureRegion tex2, Loader loader, float x, float y) {
        this.tex = tex2;
        setPosition(x, y);
        setSize((float) tex2.getRegionWidth(), (float) tex2.getRegionHeight());
        addListener(new ButtonListener(this, loader));
    }

    public ButtonActor(TextureRegion tex2, Loader loader) {
        this(tex2, loader, 0.0f, 0.0f);
    }

    public void act(float delta) {
        if (this.icon != null) {
            this.icon.act(delta);
        }
        if (this.locPpux != ArkanoidGame.getInstance().getPpuX() || this.locPpuy != ArkanoidGame.getInstance().getPpuY()) {
            setSize(this.locWidth, this.locHeight);
            setPosition(this.locX, this.locY);
            this.locPpux = ArkanoidGame.getInstance().getPpuX();
            this.locPpuy = ArkanoidGame.getInstance().getPpuY();
        }
    }

    public void draw(Batch batch, float parentAlpha) {
        if (this.hover) {
            batch.setColor(1.0f, 0.9f, 0.0f, 1.0f);
        }
        if (this.down) {
            batch.setColor(0.6f, 0.6f, 0.6f, 1.0f);
        }
        batch.draw(this.tex, getX(), getY(), getWidth(), getHeight());
        if (this.icon != null) {
            this.icon.draw(batch, parentAlpha);
        }
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void setIcon(Actor icon2, float offX, float offY) {
        this.icon = icon2;
        icon2.setPosition(this.locX + offX, this.locY + offY);
    }

    public void removeIcon() {
        this.icon = null;
    }

    public void setSize(float width, float height) {
        this.locWidth = width;
        this.locHeight = height;
        super.setSize(ArkanoidGame.getInstance().getPpuX() * width, ArkanoidGame.getInstance().getPpuY() * height);
    }

    public void setX(float x) {
        this.locX = x;
        super.setX(ArkanoidGame.getInstance().getPpuX() * x);
    }

    public void setY(float y) {
        this.locY = y;
        super.setX(ArkanoidGame.getInstance().getPpuY() * y);
    }

    public void setPosition(float x, float y) {
        this.locX = x;
        this.locY = y;
        super.setPosition(ArkanoidGame.getInstance().getPpuX() * x, ArkanoidGame.getInstance().getPpuY() * y);
    }

    public void setFocus(boolean value) {
        this.hover = value;
    }

    public void setDown(boolean value) {
        this.down = value;
    }

    public float getLocX() {
        return this.locX;
    }

    public float getLocY() {
        return this.locY;
    }

    public float getLocWidth() {
        return this.locWidth;
    }

    public float getLocHeight() {
        return this.locHeight;
    }
}
