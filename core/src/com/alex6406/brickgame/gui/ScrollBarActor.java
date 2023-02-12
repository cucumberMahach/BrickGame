package com.alex6406.brickgame.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.alex6406.brickgame.controller.ScrollBtnMove;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;

public class ScrollBarActor extends Actor {
    private float btnMax;
    private float btnMin;
    private final ButtonActor button;
    private int lastPlayed;
    private ScrollBarListener listener;
    private final Loader loader;
    private final TextureRegion texScroll;

    public ScrollBarActor(Loader loader2, float x, float y) {
        this.loader = loader2;
        this.texScroll = loader2.getTileMenu("scroll_bar");
        setPosition(x, y);
        setSize((float) this.texScroll.getRegionWidth(), (float) this.texScroll.getRegionHeight());
        this.button = new ButtonActor(loader2.getTileMenu("scroll_button"), loader2);
        this.button.addListener(new ScrollBtnMove(this));
    }

    public void btnDragged(float x) {
        int i;
        this.lastPlayed++;
        float x2 = x - (this.button.getLocWidth() / 2.0f);
        if (x2 >= this.btnMax || x2 <= this.btnMin) {
            this.button.setX(x2 >= this.btnMax ? this.btnMax : this.btnMin);
            ScrollBarListener scrollBarListener = this.listener;
            if (x2 >= this.btnMax) {
                i = 1;
            } else {
                i = 0;
            }
            scrollBarListener.dragged((float) i);
            return;
        }
        this.button.setX(x2);
        if (this.listener != null) {
            this.listener.dragged(getValue());
        }
        if (this.lastPlayed > 3) {
            this.loader.playSound("wall");
            this.lastPlayed = 0;
        }
    }

    public float getValue() {
        return (this.button.getLocX() - this.btnMin) / (this.btnMax - this.btnMin);
    }

    public void setValue(float value) {
        this.button.setX(((this.btnMax - this.btnMin) * value) + this.btnMin);
    }

    public ScrollBarActor(Loader loader2) {
        this(loader2, 0.0f, 0.0f);
    }

    /* access modifiers changed from: protected */
    public void setStage(Stage stage) {
        super.setStage(stage);
        stage.addActor(this.button);
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.texScroll, ArkanoidGame.getInstance().getPpuX() * getX(), ArkanoidGame.getInstance().getPpuY() * getY(), ArkanoidGame.getInstance().getPpuX() * getWidth(), ArkanoidGame.getInstance().getPpuY() * getHeight());
    }

    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        if (this.button != null) {
            this.button.setPosition(getX(), (getY() - (this.button.getLocHeight() / 2.0f)) + (getHeight() / 2.0f));
            this.btnMin = getX();
            this.btnMax = (getX() + getWidth()) - this.button.getLocWidth();
        }
    }

    public void setListener(ScrollBarListener listener2) {
        this.listener = listener2;
    }

    public void clearListener() {
        this.listener = null;
    }

    public boolean remove() {
        this.button.remove();
        return super.remove();
    }
}
