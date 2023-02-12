package com.alex6406.brickgame.geometry;

import com.badlogic.gdx.math.Rectangle;
import com.alex6406.brickgame.model.World;
import com.alex6406.brickgame.view.Brick;

public class Physics {
    private final Rectangle locRect = new Rectangle();
    private Location tempLoc;
    private final Rectangle tempRect = new Rectangle();
    private final World world;

    public Physics(World world2) {
        this.world = world2;
    }

    public Collision isHitted(Rectangle rect, float velX, float velY) {
        Location loc;
        int i;
        this.tempRect.set(rect);
        float far = (float) Math.sqrt((((0.0f) + velX) * ((0.0f) + velX)) + (((0.0f) + velY) * ((0.0f) + velY)));
        float velStepX = (1.0f / far) * velX;
        float velStepY = (1.0f / far) * velY;
        Collision collid = new Collision(Location.NOT, 0.0f, 0.0f);
        Brick[] bricks = this.world.getBricks();
        if (bricks != null) {
            float i2 = 0.0f;
            while (true) {
                if (i2 >= far) {
                    break;
                }
                this.tempRect.x += velStepX;
                this.tempRect.y += velStepY;
                int a = 0;
                while (a < bricks.length) {
                    if (bricks[a] == null || bricks[a].isRemoving() || (loc = getLocation(this.tempRect, bricks[a].getRectangle(), velStepX, velStepY)) == Location.NOT) {
                        a++;
                    } else {
                        collid.location = loc;
                        collid.colX = (((loc == Location.LEFT || loc == Location.RIGHT) ? this.tempRect.width : 0.0f) * ((float) (loc == Location.RIGHT ? 0 : 1))) + this.tempRect.x;
                        float f = this.tempRect.y;
                        float f2 = (loc == Location.TOP || loc == Location.BOTTOM) ? this.tempRect.height / 2.0f : 0.0f;
                        if (loc == Location.TOP) {
                            i = 0;
                        } else {
                            i = 1;
                        }
                        collid.colY = (f2 * ((float) i)) + f;
                        collid.brickX = bricks[a].getRectangle().x;
                        collid.brickY = bricks[a].getRectangle().y;
                        collid.recX = this.tempRect.x - velStepX;
                        collid.recY = this.tempRect.y - velStepY;
                        this.world.collided(bricks[a]);
                        return collid;
                    }
                }
                i2 += 1.0f;
            }
        }
        return collid;
    }

    public Location isCollided(Rectangle rect, Rectangle rect2, float velX, float velY) {
        this.tempRect.set(rect);
        float far = (float) Math.sqrt((((0.0f) + velX) * ((0.0f) + velX)) + (((0.0f) + velY) * ((0.0f) + velY)));
        float velStepX = (1.0f / far) * velX;
        float velStepY = (1.0f / far) * velY;
        for (float i = 0.0f; i < far; i += 1.0f) {
            this.tempRect.x += velStepX;
            this.tempRect.y += velStepY;
            if (this.tempRect.overlaps(rect2)) {
                if ((this.tempRect.x + this.tempRect.width > rect2.x || this.tempRect.x < rect2.x + rect2.width) && (rect.y > rect2.y + rect2.height || rect.y + rect.height < rect2.y)) {
                    if (this.tempRect.y > rect2.y) {
                        return Location.TOP;
                    }
                    if (this.tempRect.y < rect2.y) {
                        return Location.BOTTOM;
                    }
                }
                if (this.tempRect.x < rect2.x) {
                    return Location.LEFT;
                }
                if (this.tempRect.x > rect2.x) {
                    return Location.RIGHT;
                }
            }
        }
        return Location.NOT;
    }

    public Collision isCollided2(Rectangle rect, Rectangle rect2, float velX, float velY) {
        Collision collid = new Collision(Location.NOT, 0.0f, 0.0f);
        this.tempRect.set(rect);
        float far = (float) Math.sqrt((((0.0f) + velX) * ((0.0f) + velX)) + (((0.0f) + velY) * ((0.0f) + velY)));
        float velStepX = (1.0f / far) * velX;
        float velStepY = (1.0f / far) * velY;
        float i = 0.0f;
        while (true) {
            if (i >= far) {
                break;
            }
            this.tempRect.x += velStepX;
            this.tempRect.y += velStepY;
            if (this.tempRect.overlaps(rect2)) {
                collid.colX = this.tempRect.x;
                collid.colY = this.tempRect.y;
                if ((this.tempRect.x + this.tempRect.width > rect2.x || this.tempRect.x < rect2.x + rect2.width) && (rect.y > rect2.y + rect2.height || rect.y + rect.height < rect2.y)) {
                    if (this.tempRect.y > rect2.y) {
                        collid.location = Location.TOP;
                    } else if (this.tempRect.y < rect2.y) {
                        collid.location = Location.BOTTOM;
                    }
                }
                if (this.tempRect.x < rect2.x) {
                    collid.location = Location.LEFT;
                } else if (this.tempRect.x > rect2.x) {
                    collid.location = Location.RIGHT;
                }
            } else {
                i += 1.0f;
            }
        }
        return collid;
    }

    public Location getLocation(Rectangle rect, Rectangle rect2, float velX, float velY) {
        this.locRect.set(rect);
        this.locRect.x += velX;
        this.locRect.y += velY;
        if (this.locRect.overlaps(rect2)) {
            if ((this.locRect.x + this.locRect.width > rect2.x || this.locRect.x < rect2.x + rect2.width) && (rect.y > rect2.y + rect2.height || rect.y + rect.height < rect2.y)) {
                if (this.locRect.y > rect2.y) {
                    return Location.TOP;
                }
                if (this.locRect.y < rect2.y) {
                    return Location.BOTTOM;
                }
            }
            if (this.locRect.x < rect2.x) {
                return Location.LEFT;
            }
            if (this.locRect.x > rect2.x) {
                return Location.RIGHT;
            }
        }
        return Location.NOT;
    }

    public Location isWalled(Rectangle rect, float velX, float velY) {
        this.tempRect.set(rect);
        this.tempRect.x += velX;
        this.tempRect.y += velY;
        if (this.tempRect.x < 0.0f) {
            return Location.LEFT;
        }
        if (this.tempRect.x + this.tempRect.width > 1280.0f) {
            return Location.RIGHT;
        }
        if (this.tempRect.y < 0.0f) {
            return Location.BOTTOM;
        }
        if (this.tempRect.y + this.tempRect.height > 720.0f) {
            return Location.TOP;
        }
        return Location.NOT;
    }

    public Collision isBoarded2(Rectangle rect, float velX, float velY) {
        Collision col = isCollided2(rect, this.world.getBoard().getRectangle(), velX, velY);
        if (col.location != Location.NOT) {
            this.world.boarded();
        }
        return col;
    }

    public Location isBoarded(Rectangle rect, float velX, float velY) {
        this.tempLoc = isCollided(rect, this.world.getBoard().getRectangle(), velX, velY);
        if (this.tempLoc != Location.NOT) {
            this.world.boarded();
        }
        return this.tempLoc;
    }

    public BallCollision isAnyCollided(Rectangle rect, float velX, float velY) {
        BallCollision result = new BallCollision(null, 0.0f, 0.0f);
        result.location = isWalled(rect, velX, velY);
        if (result.location != Location.NOT) {
            result.type = Object.WALL;
        } else if (isBoarded(rect, velX, velY) == Location.NOT) {
            result.setCollision(isHitted(rect, velX, velY));
            if (result.location != Location.NOT) {
                result.type = Object.BRICK;
            }
        } else {
            result.setCollision(isBoarded2(rect, velX, velY));
            result.type = Object.BOARD;
        }
        return result;
    }
}
