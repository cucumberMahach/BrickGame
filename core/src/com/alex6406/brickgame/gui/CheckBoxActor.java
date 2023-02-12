package com.alex6406.brickgame.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alex6406.brickgame.controller.CheckBoxToggle;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;

public class CheckBoxActor extends Actor {
    private boolean checked;
    private float locHeight;
    private float locPpux;
    private float locPpuy;
    private float locWidth;
    private float locX;
    private float locY;
    private final TextureRegion texChecked;
    private final TextureRegion texUnChecked;

    public CheckBoxActor(Loader loader, float x, float y) {
        this.texChecked = loader.getTileMenu("checkbox_on");
        this.texUnChecked = loader.getTileMenu("checkbox_off");
        setPosition(x, y);
        setSize((float) this.texChecked.getRegionWidth(), (float) this.texChecked.getRegionHeight());
        addListener(new CheckBoxToggle(this, loader));
    }

    public CheckBoxActor(Loader loader) {
        this(loader, 0.0f, 0.0f);
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.checked ? this.texChecked : this.texUnChecked, getX(), getY(), getWidth(), getHeight());
    }

    public void toggle() {
        this.checked = !this.checked;
    }

    public void setChecked(boolean value) {
        this.checked = value;
    }

    public boolean getChecked() {
        return this.checked;
    }

    public void act(float delta) {
        if (this.locPpux != ArkanoidGame.getInstance().getPpuX() || this.locPpuy != ArkanoidGame.getInstance().getPpuY()) {
            setSize(this.locWidth, this.locHeight);
            setPosition(this.locX, this.locY);
            this.locPpux = ArkanoidGame.getInstance().getPpuX();
            this.locPpuy = ArkanoidGame.getInstance().getPpuY();
        }
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
