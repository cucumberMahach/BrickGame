package com.alex6406.brickgame.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.screens.TransitionScreen;
import com.alex6406.brickgame.view.BrickTransition;

public class WorldTransition extends Stage {
    private BrickTransition[] bricks;
    private int done;
    private int height;
    private boolean hiding;
    private final Loader loader;
    private final TransitionScreen screen;
    private int width;

    public WorldTransition(Viewport viewport, Batch batch, Loader loader2, TransitionScreen screen2) {
        super(viewport, batch);
        this.loader = loader2;
        this.screen = screen2;
        build();
    }

    public void build() {
        this.width = (int) Math.ceil(21.33333396911621d);
        this.height = (int) Math.ceil(24.0d);
        float offX = (60.0f * (21.333334f - ((float) this.width))) / 2.0f;
        float offY = (30.0f * (24.0f - ((float) this.height))) / 2.0f;
        int q = 0;
        this.bricks = new BrickTransition[(this.width * this.height)];
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                q++;
                this.bricks[(this.width * y) + x] = new BrickTransition(this.loader, (((float) x) * 60.0f) + offX, 720.0f - (((((float) y) * 30.0f) + 30.0f) + offY), MathUtils.random(1, 2), x, y, this, q % 20 == 0);
                addActor(this.bricks[(this.width * y) + x]);
            }
        }
    }

    public void act(float delta) {
        if (this.done == this.width * this.height) {
            if (!getBrick(this.width - 1, this.height - 1).isAlive() && this.hiding) {
                end();
            }
            if (getBrick(this.width - 1, this.height - 1).isAlive() && !this.hiding) {
                hide();
            }
            this.done = 0;
        }
        super.act(delta);
    }

    public void explode(int x, int y) {
        if (getBrick(x, y - 2) != null) {
            getBrick(x, y - 2).explode();
        }
        if (getBrick(x, y + 2) != null) {
            getBrick(x, y + 2).explode();
        }
        if (getBrick(x - 2, y) != null) {
            getBrick(x - 2, y).explode();
        }
        if (getBrick(x + 2, y) != null) {
            getBrick(x + 2, y).explode();
        }
        for (int j = -1; j < 2; j++) {
            for (int k = -1; k < 2; k++) {
                if (getBrick(x + k, y + j) != null) {
                    getBrick(x + k, y + j).explode();
                }
            }
        }
    }

    public void done() {
        this.done++;
    }

    public BrickTransition getBrick(int x, int y) {
        if (y >= this.height || x >= this.width || x < 0 || y < 0) {
            return null;
        }
        return this.bricks[(this.width * y) + x];
    }

    public void show() {
        this.hiding = false;
        getBrick(MathUtils.random(0, this.width - 1), MathUtils.random(0, this.height - 1)).explode();
    }

    public void hide() {
        this.hiding = true;
        getBrick(MathUtils.random(0, this.width - 1), MathUtils.random(0, this.height - 1)).explode();
    }

    public void end() {
        this.screen.end();
        this.hiding = false;
    }

    public boolean isHiding() {
        return this.hiding;
    }
}
