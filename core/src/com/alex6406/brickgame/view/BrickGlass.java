package com.alex6406.brickgame.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.model.World;

public class BrickGlass extends Brick {
    private final TextureRegion glassTexture;
    private boolean glassed = false;

    public BrickGlass(Loader loader, float x, float y, char type, int mapX, int mapY, World world) {
        super(loader, x, y, type, mapX, mapY, world);
        this.glassTexture = loader.getTile("glass");
    }

    public void collided() {
        if (this.glassed) {
            removing(false);
            sound();
            return;
        }
        this.glassed = true;
        this.loader.playSound("glass");
    }

    /* access modifiers changed from: protected */
    public void typeDraw(Batch batch, float parentAlpha) {
        if (this.glassed) {
            batch.draw(this.glassTexture, ArkanoidGame.getInstance().getPpuX() * getX(), ArkanoidGame.getInstance().getPpuY() * getY(), ArkanoidGame.getInstance().getPpuX() * getWidth(), ArkanoidGame.getInstance().getPpuY() * getHeight());
        }
    }
}
