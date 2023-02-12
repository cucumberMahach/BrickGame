package com.alex6406.brickgame.animateIcons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;

public class IconAbout extends Actor {
    private float cof;
    private final float max = 12.566371f;
    private float prgs;
    private final TextureRegion texture;

    public IconAbout(Loader loader) {
        this.texture = loader.getTileMenu("object_i");
        setSize((float) this.texture.getRegionWidth(), (float) this.texture.getRegionHeight());
    }

    public void act(float delta) {
        this.cof += 0.1f * delta * 66.0f;
        if (this.cof > this.max) {
            this.cof = 0.0f;
        }
        this.prgs = ((float) Math.sin(this.cof)) * 10.0f;
    }

    public void draw(Batch batch, float parentAlpha) {
        float ppux = ArkanoidGame.getInstance().getPpuX();
        float ppuy = ArkanoidGame.getInstance().getPpuY();
        batch.draw(this.texture, getX() * ppux, getY() * ppuy, (getWidth() / 2.0f) * ppux, (getHeight() / 2.0f) * ppuy, getWidth() * ppux, getHeight() * ppuy, 1.0f, 1.0f, this.prgs);
    }
}
