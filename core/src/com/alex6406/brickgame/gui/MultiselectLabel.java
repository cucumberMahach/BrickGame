package com.alex6406.brickgame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alex6406.brickgame.controller.MultiselectLabelToggle;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;

public class MultiselectLabel extends Actor {
    private final BitmapFont font;
    private int index;
    private final String[] list;
    private float locHeight;
    private float locPpux;
    private float locPpuy;
    private float locWidth;
    private float locX;
    private float locY;

    public MultiselectLabel(Loader loader, float x, float y, float width, String font2, String[] list2) {
        this.list = list2;
        this.font = new BitmapFont(Gdx.files.internal(font2));
        setSize(width, this.font.getCapHeight());
        addListener(new MultiselectLabelToggle(this, loader));
    }

    public MultiselectLabel(Loader loader, float width, String font2, String[] list2) {
        this(loader, 0.0f, 0.0f, width, font2, list2);
    }

    public void change() {
        if (this.index == this.list.length - 1) {
            this.index = 0;
        } else {
            this.index++;
        }
    }

    public void act(float delta) {
        if (this.locPpux != ArkanoidGame.getInstance().getPpuX() || this.locPpuy != ArkanoidGame.getInstance().getPpuY()) {
            setSize(this.locWidth, this.locHeight);
            setPosition(this.locX, this.locY);
            this.locPpux = ArkanoidGame.getInstance().getPpuX();
            this.locPpuy = ArkanoidGame.getInstance().getPpuY();
        }
    }

    public void draw(Batch batch, float parentAlpha) {
        this.font.draw(batch, this.list[this.index], getX(), getY() + (this.font.getCapHeight() * ArkanoidGame.getInstance().getPpuY()));
    }

    public void setIndex(int value) {
        this.index = value;
    }

    public int getIndex() {
        return this.index;
    }

    public void setColor(float r, float g, float b, float a) {
        this.font.setColor(r, g, b, a);
        super.setColor(r, g, b, a);
    }

    public void setColor(Color color) {
        this.font.setColor(color);
        super.setColor(color);
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
