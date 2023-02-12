package com.alex6406.brickgame.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.model.World;

public class Brick extends Actor {
    private Animation anim;
    private boolean isExploded;
    protected Loader loader;
    private final int mapX;
    private final int mapY;
    private final Rectangle rect;
    private boolean removing;
    private TextureRegion texture;
    private final char type;
    private final World world;

    public Brick(Loader loader2, float x, float y, char type2, int mapX2, int mapY2, World world2) {
        int ch = Character.getNumericValue(type2);
        this.texture = loader2.getBricks((ch <= 0 || ch > 8) ? 0 : ch);
        this.loader = loader2;
        this.type = type2;
        this.mapX = mapX2;
        this.mapY = mapY2;
        this.world = world2;
        setPosition(x, y);
        setSize(60.0f, 30.0f);
        this.rect = new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public void collided() {
        sound();
        removing(false);
    }

    public void explode() {
        if (!isRemoving()) {
            removing(true);
        }
    }

    /* access modifiers changed from: protected */
    public void removed() {
    }

    /* access modifiers changed from: protected */
    public void sound() {
        this.loader.playSound("brick");
    }

    /* access modifiers changed from: protected */
    public void removing(boolean isExploded2) {
        if (!this.removing) {
            this.removing = true;
            this.world.addScore(this.type);
            if (isExploded2) {
                this.anim = new Animation(this.loader.getAnimation("explode"), 0.1f, false, false);
            } else {
                this.anim = new Animation(this.loader.getAnimation("remove"), 0.2f, false, true);
            }
            this.isExploded = isExploded2;
            this.anim.play();
        }
    }

    private void free() {
        remove();
        removed();
    }

    public void draw(Batch batch, float parentAlpha) {
        if (!this.isExploded) {
            typeDraw(batch, parentAlpha);
        }
        if (this.removing) {
            batch.draw(this.anim.getFrame(), ArkanoidGame.getInstance().getPpuX() * getX(), ArkanoidGame.getInstance().getPpuY() * getY(), ArkanoidGame.getInstance().getPpuX() * getWidth(), ArkanoidGame.getInstance().getPpuY() * getHeight());
        }
    }

    /* access modifiers changed from: protected */
    public void typeDraw(Batch batch, float parentAlpha) {
        batch.draw(this.texture, ArkanoidGame.getInstance().getPpuX() * getX(), ArkanoidGame.getInstance().getPpuY() * getY(), ArkanoidGame.getInstance().getPpuX() * getWidth(), ArkanoidGame.getInstance().getPpuY() * getHeight());
    }

    public void act(float delta) {
        if (this.removing) {
            this.anim.update(delta);
            if (!this.anim.isPlaying()) {
                this.world.removeBrick(this.mapX, this.mapY);
                free();
            }
        }
    }

    public void setTexture(TextureRegion texture2) {
        this.texture = texture2;
    }

    /* access modifiers changed from: protected */
    public int getMapX() {
        return this.mapX;
    }

    /* access modifiers changed from: protected */
    public int getMapY() {
        return this.mapY;
    }

    /* access modifiers changed from: protected */
    public World getWorld() {
        return this.world;
    }

    public Rectangle getRectangle() {
        return this.rect;
    }

    public boolean isRemoving() {
        return this.removing;
    }

    public char getType() {
        return this.type;
    }
}
