package com.alex6406.brickgame.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.geometry.Location;
import com.alex6406.brickgame.model.World;

public class Drop extends Actor {
    private final Vector2 boost;
    private float cof;
    private final Vector2 force;
    private final Rectangle rect = new Rectangle(getX(), getY(), getWidth(), getHeight());
    private final TextureRegion tex;
    private final int type;
    private final World world;

    public Drop(Loader loader, World world2, float x, float y, int type2) {
        this.world = world2;
        this.type = type2;
        this.tex = loader.getTile("drop" + type2);
        setPosition(x, y);
        setSize(((float) this.tex.getRegionWidth()) * loader.getDropScale(), ((float) this.tex.getRegionHeight()) * loader.getDropScale());
        this.force = new Vector2(((float) (MathUtils.randomBoolean() ? -1 : 1)) * MathUtils.random(1.0f, 3.0f), MathUtils.random(4.0f, 6.0f));
        this.boost = new Vector2(0.0f, -0.1f);
    }

    public void act(float delta) {
        if (this.world.isUpdating()) {
            this.cof += 0.15f;
            if (((double) this.cof) > 12.566370614359172d) {
                this.cof = 0.0f;
            }
            sizeBy(((float) Math.sin(this.cof)) / 2.0f, ((float) Math.cos(this.cof)) / 2.0f);
            if (this.world.physics().isBoarded(getRectangle(), this.force.x, this.force.y) != Location.NOT) {
                this.world.dropCaught(this.type);
                remove();
            } else if (getY() + getHeight() < 0.0f) {
                remove();
            } else {
                if (getX() + getWidth() > 1280.0f || getX() < 0.0f) {
                    this.force.scl(-1.0f, 1.0f);
                }
                this.force.add(this.boost.x * delta * 66.0f, this.boost.y * delta * 66.0f);
                moveBy(this.force.x * delta * 66.0f, this.force.y * delta * 66.0f);
            }
        }
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.tex, ArkanoidGame.getInstance().getPpuX() * getX(), ArkanoidGame.getInstance().getPpuY() * getY(), ArkanoidGame.getInstance().getPpuX() * getWidth(), ArkanoidGame.getInstance().getPpuY() * getHeight());
    }

    public Rectangle getRectangle() {
        this.rect.set(getX(), getY(), getWidth(), getHeight());
        return this.rect;
    }
}
