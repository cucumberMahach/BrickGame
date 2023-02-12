package com.alex6406.brickgame.animateIcons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alex6406.brickgame.engine.ArkanoidGame;

public class IconRates extends Actor {
    private float cof;
    private final float max = 12.566371f;
    private float prgs1;
    private float prgs2;
    private float prgs3;
    private final TextureRegion progressTex;

    public IconRates(TextureRegion progressTex2) {
        this.progressTex = progressTex2;
        setSize((float) progressTex2.getRegionWidth(), (float) progressTex2.getRegionHeight());
    }

    public void act(float delta) {
        this.cof += 0.1f * delta * 66.0f;
        if (this.cof > this.max) {
            this.cof = 0.0f;
        }
        this.prgs1 = (((float) Math.sin(this.cof)) * 30.0f) + (getHeight() * 0.7f);
        this.prgs2 = (((float) Math.sin(this.cof + 1.0f)) * 10.0f) + (getHeight() * 0.2f);
        this.prgs3 = (((float) Math.sin(this.cof + 2.0f)) * 40.0f) + (getHeight() * 0.5f);
    }

    public void draw(Batch batch, float parentAlpha) {
        float ppux = ArkanoidGame.getInstance().getPpuX();
        float ppuy = ArkanoidGame.getInstance().getPpuY();
        batch.draw(this.progressTex, getX() * ppux, getY() * ppuy, getWidth() * ppux, this.prgs1 * ppuy);
        batch.draw(this.progressTex, (getX() + 10.0f + getWidth()) * ppux, getY() * ppuy, getWidth() * ppux, this.prgs2 * ppuy);
        batch.draw(this.progressTex, (getX() + 20.0f + (getWidth() * 2.0f)) * ppux, getY() * ppuy, getWidth() * ppux, this.prgs3 * ppuy);
    }
}
