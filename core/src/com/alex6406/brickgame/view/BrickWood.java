package com.alex6406.brickgame.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.model.World;

public class BrickWood extends Brick {
    private boolean isWooded;
    private final TextureRegion woodTexture;

    public BrickWood(Loader loader, float x, float y, char type, int mapX, int mapY, World world, boolean isWooded2) {
        super(loader, x, y, type, mapX, mapY, world);
        setTexture(loader.getBricks(isWooded2 ? 0 : type - 'H'));
        this.woodTexture = loader.getTile("wood");
        this.isWooded = isWooded2;
    }

    public BrickWood(Loader loader, float x, float y, char type, int mapX, int mapY, World world) {
        this(loader, x, y, type, mapX, mapY, world, false);
    }

    public void collided() {
        if (this.isWooded) {
            if (!getWorld().isBallBouncing()) {
                removing(false);
            }
            this.loader.playSound("wood");
            return;
        }
        this.isWooded = true;
        sound();
    }

    /* access modifiers changed from: protected */
    public void typeDraw(Batch batch, float parentAlpha) {
        if (this.isWooded) {
            batch.draw(this.woodTexture, ArkanoidGame.getInstance().getPpuX() * getX(), ArkanoidGame.getInstance().getPpuY() * getY(), ArkanoidGame.getInstance().getPpuX() * getWidth(), ArkanoidGame.getInstance().getPpuY() * getHeight());
            return;
        }
        super.typeDraw(batch, parentAlpha);
    }

    public boolean isWooded() {
        return this.isWooded;
    }
}
