package com.alex6406.brickgame.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.model.World;

public class Board extends Actor {
    private final TextureRegion centerTex;

    /* renamed from: cw */
    private final float cw;
    private final TextureRegion leftTex;

    /* renamed from: lw */
    private final float lw;
    private final int prefSize;
    private final Rectangle rect;
    private final TextureRegion rightTex;

    /* renamed from: rw */
    private final float rw;
    private int size;
    private final World world;

    public Board(Loader loader, World world2, float x) {
        this.leftTex = loader.getTile("board_left");
        this.centerTex = loader.getTile("board_center");
        this.rightTex = loader.getTile("board_right");
        this.world = world2;
        this.prefSize = loader.getBoardSize();
        setPosition(x, 20.0f);
        this.lw = ((float) this.leftTex.getRegionWidth()) * loader.getBoardScale();
        this.cw = ((float) this.centerTex.getRegionWidth()) * loader.getBoardScale();
        this.rw = ((float) this.rightTex.getRegionWidth()) * loader.getBoardScale();
        setHeight(22.0f * loader.getBoardScale());
        rect = new Rectangle(getX(), getY(), getWidth() * 10.5f, getHeight());
    }

    public void draw(Batch batch, float parentAlpha) {
        float ppux = ArkanoidGame.getInstance().getPpuX();
        float ppuy = ArkanoidGame.getInstance().getPpuY();
        batch.draw(this.leftTex, getX() * ppux, getY() * ppuy, this.lw * ppux, getHeight() * ppuy);
        for (int i = 0; i < this.size; i++) {
            float cx = (this.lw + getX() + (this.cw * ((float) i))) * ppux;
            float cy = (getY() + ((getHeight() - getHeight()) / 2.0f)) * ppuy;
            batch.draw(this.centerTex, cx, cy, this.cw * ppux, getHeight() * ppuy);
        }
        batch.draw(this.rightTex, (this.rw + getX() + (((float) this.size) * this.cw)) * ppux, getY() * ppuy, this.rw * ppux, getHeight() * ppuy);
    }

    public void setX(float x) {
        if (!this.world.isUpdating()) {
            return;
        }
        if (x <= 0.0f) {
            setXPos(0.0f);
        } else if (getWidth() + x < 1280.0f) {
            setXPos(x);
        } else {
            setXPos(1280.0f - getWidth());
        }
    }

    private void setXPos(float x) {
        this.rect.x = x;
        super.setX(x);
    }

    public void toStart() {
        setSize(this.prefSize);
        setXPos(640.0f - (getWidth() / 2.0f));
    }

    private void setSize(int value) {
        if (value < 1) {
            this.size = 1;
        } else if (value > 30) {
            this.size = 30;
        } else {
            this.size = value;
        }
        setWidth(this.lw + (((float) this.size) * this.cw) + this.rw);
        if (this.rect != null) {
            this.rect.setWidth(getWidth());
        }
    }

    public void changeSize(int value) {
        setSize(this.size + value);
    }

    public Rectangle getRectangle() {
        return this.rect;
    }
}
