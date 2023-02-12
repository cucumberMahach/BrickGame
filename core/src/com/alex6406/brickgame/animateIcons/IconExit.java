package com.alex6406.brickgame.animateIcons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;

public class IconExit extends Actor {
    private float cof;
    private final TextureRegion doorTex;
    private final TextureRegion knobTex;
    private final float max = 12.566371f;
    private float prgs1;
    private float prgs2;

    public IconExit(Loader loader) {
        this.doorTex = loader.getTileMenu("object_door");
        this.knobTex = loader.getTileMenu("object_knob");
        setSize((float) this.doorTex.getRegionWidth(), (float) this.doorTex.getRegionHeight());
    }

    public void act(float delta) {
        this.cof += 0.1f * delta * 66.0f;
        if (this.cof > this.max) {
            this.cof = 0.0f;
        }
        this.prgs1 = (float) Math.sin(this.cof / 2.0f);
        this.prgs2 = ((float) Math.sin(this.cof + 1.0f)) * 5.0f;
        moveBy(this.prgs1, 0.0f);
    }

    public void draw(Batch batch, float parentAlpha) {
        float ppux = ArkanoidGame.getInstance().getPpuX();
        float ppuy = ArkanoidGame.getInstance().getPpuY();
        batch.draw(this.doorTex, getX() * ppux, getY() * ppuy, getWidth() * ppux, getHeight() * ppuy);
        batch.draw(this.knobTex, (getX() + 60.0f) * ppux, (getY() + 51.0f + this.prgs2) * ppuy, ((float) this.knobTex.getRegionWidth()) * ppux, ((float) this.knobTex.getRegionHeight()) * ppuy);
    }
}
