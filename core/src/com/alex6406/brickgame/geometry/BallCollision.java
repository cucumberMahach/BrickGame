package com.alex6406.brickgame.geometry;

public class BallCollision extends Collision {
    public Object type;

    public BallCollision(Location location, float recX, float recY) {
        super(location, recX, recY);
    }

    public void setCollision(Collision col) {
        this.colX = col.colX;
        this.colY = col.colY;
        this.location = col.location;
        this.recX = col.recX;
        this.recY = col.recY;
        this.brickX = col.brickX;
        this.brickY = col.brickY;
        this.type = null;
    }
}
