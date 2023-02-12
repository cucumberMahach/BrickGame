package com.alex6406.brickgame.animateIcons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;

public class IconMode1 extends Actor {
    private final TextureRegion ballTex;
    private final TextureRegion boardTex;
    private final TextureRegion bricksTex;
    private float cof;
    private final float max = 12.566371f;

    public IconMode1(Loader loader) {
        this.bricksTex = loader.getTileMenu("object_bricks1");
        this.ballTex = loader.getTileMenu("object_ball");
        this.boardTex = loader.getTileMenu("object_board");
    }

    public void act(float delta) {
        this.cof += 0.1f * delta * 66.0f;
        if (this.cof > this.max) {
            this.cof = 0.0f;
        }
    }

    public void draw(Batch batch, float parentAlpha) {
        float ppux = ArkanoidGame.getInstance().getPpuX();
        float ppuy = ArkanoidGame.getInstance().getPpuY();
        batch.draw(this.bricksTex, (getX() + (((float) Math.sin(this.cof + 1.0f)) * 10.0f)) * ppux, (getY() + 130.0f + (((float) Math.cos(this.cof)) * 2.0f)) * ppuy, ((float) this.bricksTex.getRegionWidth()) * ppux, ((float) this.bricksTex.getRegionHeight()) * ppuy);
        batch.draw(this.ballTex, (getX() + 179.0f) * ppux, (getY() + 73.0f + (((float) Math.cos(this.cof)) * 20.0f)) * ppuy, ((float) this.ballTex.getRegionWidth()) * ppux, ((float) this.ballTex.getRegionHeight()) * ppuy);
        batch.draw(this.boardTex, (getX() + 50.0f + (((float) Math.sin(this.cof)) * 60.0f)) * ppux, getY() * ppuy, ((float) this.boardTex.getRegionWidth()) * ppux, ((float) this.boardTex.getRegionHeight()) * ppuy);
    }
}
