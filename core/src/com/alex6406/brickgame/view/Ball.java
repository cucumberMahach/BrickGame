package com.alex6406.brickgame.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.geometry.BallCollision;
import com.alex6406.brickgame.geometry.Location;
import com.alex6406.brickgame.geometry.Object;
import com.alex6406.brickgame.model.World;

public class Ball extends Actor {
    private final Vector2 direction;
    private final Rectangle rect = new Rectangle();
    private float speed;
    private float speedLen;
    private float stSpeed;
    private final TextureRegion tex;
    private final World world;

    public Ball(Loader loader, World world2) {
        this.world = world2;
        this.tex = loader.getTile("ball");
        setSize(((float) this.tex.getRegionWidth()) * loader.getBallScale(), ((float) this.tex.getRegionHeight()) * loader.getBallScale());
        this.speed = 9.0f;
        this.stSpeed = 10.0f;
        this.direction = new Vector2();
        toStart();
    }

    public void act(float delta) {
        if (this.world.isUpdating()) {
            BallCollision ballCol = this.world.physics().isAnyCollided(getRectangle(), this.direction.x * this.speed * delta * 66.0f, this.direction.y * this.speed * delta * 66.0f);
            if (ballCol.location != Location.NOT) {
                if (ballCol.type != Object.BOARD) {
                    if (ballCol.type == Object.WALL || (ballCol.type == Object.BRICK && this.world.isBallBouncing())) {
                        if (ballCol.location == Location.LEFT || ballCol.location == Location.RIGHT) {
                            this.direction.scl(-1.0f, 1.0f);
                        } else if (ballCol.location == Location.TOP || ballCol.location == Location.BOTTOM) {
                            this.direction.scl(1.0f, -1.0f);
                        }
                    }
                    if (ballCol.type == Object.WALL) {
                        this.world.walled(ballCol.location);
                        if (ballCol.location == Location.BOTTOM) {
                            this.world.ballDown(this);
                        }
                    }
                } else {
                    this.direction.scl(1.0f, -1.0f);
                    float boardWidth = this.world.getBoard().getWidth();
                    float boardPos = ballCol.colX - this.world.getBoard().getX();
                    this.direction.x = ((float) (boardPos > boardWidth / 2.0f ? 1 : -1)) * (1.0f / (boardWidth / 2.0f)) * ((boardPos > boardWidth / 2.0f ? boardPos - (boardWidth / 2.0f) : (boardWidth / 2.0f) - boardPos) + (0.125f * this.world.getBoard().getWidth()));
                    mathSpeed();
                }
            }
            if (ballCol.type != Object.BRICK) {
                moveBy(this.direction.x * this.speed * delta * 66.0f, this.direction.y * this.speed * delta * 66.0f);
            }
        }
    }

    private void mathSpeed() {
        float dxLen = Math.abs(this.direction.x);
        float dyLen = Math.abs(this.direction.y);
        this.speedLen = dyLen - dxLen;
        if (dyLen > dxLen) {
            this.speed = this.stSpeed + ((this.stSpeed / 0.5f) * this.speedLen);
        } else {
            this.speed = this.stSpeed;
        }
    }

    public void toStart(float offX) {
        this.speed = 9.0f;
        setPosition(this.world.getBoard().getX() + offX, this.world.getBoard().getY() + this.world.getBoard().getHeight());
        this.direction.set(0.5f, 0.5f);
        this.direction.nor();
    }

    public void toStart() {
        toStart((this.world.getBoard().getWidth() / 2.0f) - (getWidth() / 2.0f));
    }

    public void setStandartSpeed(float value) {
        this.stSpeed = value;
    }

    public void addSpeed(float value) {
        if (this.stSpeed + value > 3.0f) {
            this.stSpeed += value;
        } else {
            this.stSpeed = 3.0f;
        }
    }

    public void updateSpeed() {
        mathSpeed();
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.tex, ArkanoidGame.getInstance().getPpuX() * getX(), ArkanoidGame.getInstance().getPpuY() * getY(), ArkanoidGame.getInstance().getPpuX() * getWidth(), ArkanoidGame.getInstance().getPpuY() * getHeight());
    }

    public Rectangle getRectangle() {
        this.rect.set(getX(), getY(), getWidth(), getHeight());
        return this.rect;
    }
}
