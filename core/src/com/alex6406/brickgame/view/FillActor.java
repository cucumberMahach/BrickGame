package com.alex6406.brickgame.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.model.World;

public class FillActor extends Actor {
    private float alpha;
    private final TextureRegion blackTex;
    private float currentTime;
    private boolean fully;
    private int operation;
    private final float step;
    private boolean wait;
    private final float waitTime;
    private boolean working;
    private final World world;

    public FillActor(Loader loader, World world2, float waitTime2, float time) {
        this.blackTex = loader.getTile("black");
        this.waitTime = waitTime2;
        this.world = world2;
        setPosition(0.0f, 0.0f);
        setSize(1280.0f, 720.0f);
        this.step = 1.0f / time;
        toStart();
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(1.0f, 1.0f, 1.0f, this.alpha);
        batch.draw(this.blackTex, ArkanoidGame.getInstance().getPpuX() * getX(), ArkanoidGame.getInstance().getPpuY() * getY(), ArkanoidGame.getInstance().getPpuX() * getWidth(), ArkanoidGame.getInstance().getPpuY() * getHeight());
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void act(float delta) {
        if (!this.working) {
            return;
        }
        if (!this.wait || this.currentTime >= this.waitTime) {
            this.currentTime = 0.0f;
            this.wait = false;
            change(delta);
            return;
        }
        this.currentTime += delta;
    }

    private void change(float delta) {
        if (this.operation == 0) {
            this.alpha += this.step * delta;
            if (this.alpha > 1.0f) {
                this.alpha = 1.0f;
                this.world.lose(1);
                if (this.fully) {
                    fadeIn();
                } else {
                    stop();
                }
            }
        } else {
            this.alpha -= this.step * delta;
            if (this.alpha < 0.0f) {
                stop();
                this.alpha = 0.0f;
                this.world.lose(2);
            }
        }
    }

    public void start() {
        Stage s = getStage();
        remove();
        s.addActor(this);
        this.working = true;
    }

    public void stop() {
        this.working = false;
    }

    public void toStart() {
        stop();
        this.alpha = 0.0f;
    }

    public void fully() {
        this.fully = true;
        fadeOut();
    }

    public void onlyOut() {
        this.fully = false;
        fadeOut();
    }

    public void onlyIn() {
        this.fully = false;
        fadeIn();
    }

    private void fadeOut() {
        this.alpha = 0.0f;
        this.wait = true;
        this.operation = 0;
        start();
    }

    private void fadeIn() {
        this.alpha = 1.0f;
        this.wait = true;
        this.operation = 1;
        start();
    }

    public boolean isWorking() {
        return this.working;
    }
}
