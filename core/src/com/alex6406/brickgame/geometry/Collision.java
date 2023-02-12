package com.alex6406.brickgame.geometry;

public class Collision {
    public float brickX;
    public float brickY;
    public float colX;
    public float colY;
    public Location location;
    public float recX;
    public float recY;

    public Collision(Location location2, float recX2, float recY2) {
        this.location = location2;
        this.recX = recX2;
        this.recY = recY2;
    }
}
