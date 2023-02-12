package com.alex6406.brickgame.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.model.World;

public class BrickMetal extends Brick {
    private final TextureRegion metalTexture;
    private boolean metalled;

    public BrickMetal(Loader loader, float x, float y, char type, int mapX, int mapY, World world) {
        super(loader, x, y, type, mapX, mapY, world);
        setTexture(loader.getBricks(type - '@'));
        this.metalTexture = loader.getTile("metal");
    }

    public void collided() {
        if (this.metalled) {
            removing(false);
            sound();
            return;
        }
        this.metalled = true;
        this.loader.playSound("metal");
    }

    /* access modifiers changed from: protected */
    public void typeDraw(Batch batch, float parentAlpha) {
        super.typeDraw(batch, parentAlpha);
        if (this.metalled) {
            batch.draw(this.metalTexture, ArkanoidGame.getInstance().getPpuX() * getX(), ArkanoidGame.getInstance().getPpuY() * getY(), ArkanoidGame.getInstance().getPpuX() * getWidth(), ArkanoidGame.getInstance().getPpuY() * getHeight());
        }
    }
}
