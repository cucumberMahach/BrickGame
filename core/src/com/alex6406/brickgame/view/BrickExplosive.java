package com.alex6406.brickgame.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.model.World;

public class BrickExplosive extends Brick {
    private boolean exploded;
    private final Animation glowing;
    private float removingTime;

    public BrickExplosive(Loader loader, float x, float y, char type, int mapX, int mapY, World world) {
        super(loader, x, y, type, mapX, mapY, world);
        this.glowing = new Animation(loader.getAnimation("glowing"), 0.5f, true, false, 0.03f);
        this.glowing.play();
    }

    public void removing(boolean isExploded) {
        super.removing(true);
        this.loader.playSound("explode");
    }

    public void typeDraw(Batch batch, float parentAlpha) {
        batch.draw(this.glowing.getFrame(), ArkanoidGame.getInstance().getPpuX() * getX(), ArkanoidGame.getInstance().getPpuY() * getY(), ArkanoidGame.getInstance().getPpuX() * getWidth(), ArkanoidGame.getInstance().getPpuY() * getHeight());
    }

    public void act(float delta) {
        super.act(delta);
        if (getWorld().isUpdating()) {
            this.glowing.update(delta);
        }
        if (isRemoving()) {
            this.removingTime += delta;
        }
        if (this.removingTime >= 0.1f && !this.exploded) {
            getWorld().explode(getMapX(), getMapY());
            this.exploded = true;
        }
    }
}
