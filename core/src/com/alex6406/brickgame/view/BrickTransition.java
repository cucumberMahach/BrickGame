package com.alex6406.brickgame.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.model.WorldTransition;

public class BrickTransition extends Actor {
    private boolean alive = false;
    private Animation anim;
    private final Animation animExp;
    private final Animation animRes;
    private boolean exploded;
    private final float limitTime = MathUtils.random(0.1f, 0.02f);
    private final Loader loader;
    private final int mapX;
    private final int mapY;
    private boolean removing;
    private float removingTime;
    private final boolean soundOn;
    private final TextureRegion texture;
    private final WorldTransition world;

    public BrickTransition(Loader loader2, float x, float y, int type, int mapX2, int mapY2, WorldTransition world2, boolean soundOn2) {
        this.texture = loader2.getBricks(type);
        this.loader = loader2;
        this.world = world2;
        this.mapX = mapX2;
        this.mapY = mapY2;
        this.animExp = new Animation(loader2.getAnimation("explode"), 0.4f, false, false);
        this.animRes = new Animation(loader2.getAnimation("multi"), 0.4f, false, false);
        this.soundOn = soundOn2;
        setPosition(x, y);
        setSize(60.0f, 30.0f);
    }

    public void explode() {
        if (this.world.isHiding() == this.alive && !this.removing) {
            removing();
        }
    }

    public void draw(Batch batch, float parentAlpha) {
        if (this.alive) {
            batch.draw(this.removing ? this.anim.getFrame() : this.texture, ArkanoidGame.getInstance().getPpuX() * getX(), ArkanoidGame.getInstance().getPpuY() * getY(), ArkanoidGame.getInstance().getPpuX() * getWidth(), ArkanoidGame.getInstance().getPpuY() * getHeight());
        } else if (this.removing) {
            batch.draw(this.texture, ArkanoidGame.getInstance().getPpuX() * getX(), ArkanoidGame.getInstance().getPpuY() * getY(), ArkanoidGame.getInstance().getPpuX() * getWidth(), ArkanoidGame.getInstance().getPpuY() * getHeight());
            batch.draw(this.anim.getFrame(), ArkanoidGame.getInstance().getPpuX() * getX(), ArkanoidGame.getInstance().getPpuY() * getY(), ArkanoidGame.getInstance().getPpuX() * getWidth(), ArkanoidGame.getInstance().getPpuY() * getHeight());
        }
    }

    public void act(float delta) {
        if (this.removing) {
            this.anim.update(delta);
            this.removingTime += delta;
            if (this.removingTime >= this.limitTime && !this.exploded) {
                this.world.explode(this.mapX, this.mapY);
                this.exploded = true;
            }
            if (!this.anim.isPlaying()) {
                this.world.done();
                reset();
            }
        }
    }

    private void removing() {
        if (!this.removing) {
            this.removing = true;
            this.anim = this.alive ? this.animExp : this.animRes;
            this.anim.play();
            if (this.soundOn) {
                this.loader.playSound("hit");
            }
        }
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void reset() {
        this.alive = !this.alive;
        this.removing = false;
        this.exploded = false;
        this.removingTime = 0.0f;
    }
}
