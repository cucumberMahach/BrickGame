package com.alex6406.brickgame.animateIcons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;

public class IconPlay extends Actor {
    private final TextureRegion arrowTex;
    private final float[] arrows = new float[3];
    private final TextureRegion hideTex;
    private final float rightBound = 230.0f;
    private final float step = 2.0f;

    public IconPlay(Loader loader) {
        this.arrowTex = loader.getTileMenu("object_arrowSmall");
        this.hideTex = loader.getTileMenu("object_arrowYellow");
        setSize((float) this.arrowTex.getRegionWidth(), (float) this.arrowTex.getRegionHeight());
        for (int a = 0; a < this.arrows.length; a++) {
            this.arrows[a] = (this.rightBound / ((float) this.arrows.length)) * ((float) a);
        }
    }

    public void act(float delta) {
        for (int a = 0; a < this.arrows.length; a++) {
            float[] fArr = this.arrows;
            fArr[a] = fArr[a] + (this.step * 0.015f * 66.0f);
            if (this.arrows[a] > this.rightBound) {
                this.arrows[a] = 0.0f;
            }
        }
    }

    public void draw(Batch batch, float parentAlpha) {
        float ppux = ArkanoidGame.getInstance().getPpuX();
        float ppuy = ArkanoidGame.getInstance().getPpuY();
        for (float x : this.arrows) {
            batch.draw(this.arrowTex, (getX() + x) * ppux, getY() * ppuy, getWidth() * ppux, getHeight() * ppuy);
        }
        batch.draw(this.hideTex, (getX() + this.rightBound) * ppux, getY() * ppuy, ((float) this.arrowTex.getRegionWidth()) * ppux, ((float) this.arrowTex.getRegionHeight()) * ppuy);
        batch.draw(this.hideTex, getX() * ppux, getY() * ppuy, ((float) this.arrowTex.getRegionWidth()) * ppux, ((float) this.arrowTex.getRegionHeight()) * ppuy);
    }
}
