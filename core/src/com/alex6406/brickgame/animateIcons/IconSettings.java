package com.alex6406.brickgame.animateIcons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alex6406.brickgame.engine.ArkanoidGame;

public class IconSettings extends Actor {
    private float angle1;
    private float angle2;
    private final TextureRegion cogTex;

    public IconSettings(TextureRegion cogTex2) {
        this.cogTex = cogTex2;
        setSize((float) cogTex2.getRegionWidth(), (float) cogTex2.getRegionHeight());
    }

    public void act(float delta) {
        this.angle1 += 1.0f * delta * 66.0f;
        if (this.angle1 > 180.0f) {
            this.angle1 = 0.0f;
        }
        this.angle2 -= (0.6f * delta) * 66.0f;
        if (this.angle2 < -180.0f) {
            this.angle2 = 0.0f;
        }
    }

    public void draw(Batch batch, float parentAlpha) {
        float ppux = ArkanoidGame.getInstance().getPpuX();
        float ppuy = ArkanoidGame.getInstance().getPpuY();
        batch.draw(this.cogTex, (getX() + 80.0f) * ppux, (getY() - 20.0f) * ppuy, (getWidth() / 2.0f) * ppux, (getHeight() / 2.0f) * ppuy, getWidth() * ppux, getHeight() * ppuy, 0.9f, 0.9f, this.angle1);
        batch.draw(this.cogTex, getX() * ppux, getY() * ppuy, (getWidth() / 2.0f) * ppux, (getHeight() / 2.0f) * ppuy, getWidth() * ppux, getHeight() * ppuy, 1.0f, 1.0f, this.angle2);
    }
}
