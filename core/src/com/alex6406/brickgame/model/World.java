package com.alex6406.brickgame.model;

import com.alex6406.brickgame.engine.*;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.alex6406.brickgame.geometry.Location;
import com.alex6406.brickgame.geometry.Physics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import com.alex6406.brickgame.view.Ball;
import com.alex6406.brickgame.view.Board;
import com.alex6406.brickgame.view.Brick;
import com.alex6406.brickgame.view.BrickWood;
import com.alex6406.brickgame.view.Drop;
import com.alex6406.brickgame.view.FillActor;
import com.alex6406.brickgame.view.LivesLabel;
import com.alex6406.brickgame.view.ScoreLabel;

public class World extends Stage {
    private WorldConfig worldConfig;
    private boolean ballBounce;
    private int ballCount;
    private int ballGameCount;
    private final ArrayList<Ball> balls;
    private final Board board;
    private Brick[] bricks;
    private int brokenBricks;
    private final WorldBuilder builder;
    private final ArrayList<Drop> drops;
    private final FillActor fillActor;
    private int lastDropTime;
    private int level;
    private int lives;
    private final LivesLabel livesLabel;
    private final Loader loader;
    private final Physics physics = new Physics(this);
    private int score;
    private final ScoreLabel scoreLabel;
    private float time;
    private int type;
    private boolean update;

    public World(Viewport viewport, Batch batch, Loader loader2) {
        super(viewport, batch);
        worldConfig = new WorldConfig();
        worldConfig.load();
        this.loader = loader2;
        this.builder = new WorldBuilder(loader2, this);
        this.board = new Board(loader2, this, 0.0f);
        this.scoreLabel = new ScoreLabel(loader2, 10.0f, 710.0f);
        this.scoreLabel.setColor(0.0f, 0.5f, 1.0f, 1.0f);
        this.livesLabel = new LivesLabel(loader2);
        this.livesLabel.setPosition(1260.0f, (720.0f - this.livesLabel.getHeight()) - 20.0f);
        this.fillActor = new FillActor(loader2, this, 0.5f, 0.9f);
        this.drops = new ArrayList<>();
        this.balls = new ArrayList<>();
        addActor(this.board);
        addActor(this.livesLabel);
        addActor(this.fillActor);
    }

    public void act(float delta) {
        this.time += delta;
        super.act(delta);
    }

    public void startGame() {
        this.update = true;
    }

    public void stopGame() {
        this.update = false;
    }

    public void newGame(int type2) {
        if (type2 != -1) {
            this.type = type2;
        }
        this.level = worldConfig.getStartLevel();
        this.score = 0;
        this.time = 0.0f;
        switch (this.type) {
            case 0:
                this.lives = 2;
                this.ballGameCount = 1;
                break;
            case 1:
                this.lives = 0;
                this.ballGameCount = 5;
                break;
        }
        this.scoreLabel.clear();
        this.livesLabel.setValue(this.lives);
        prepareGame(this.level);
        this.loader.playSound("start");
    }

    public void nextLevel() {
        if (level == worldConfig.getEndLevel()){
            stopGame();
            ArkanoidGame.getInstance().getGameData().setData(new int[]{this.level, this.score, (int) this.time});
            ArkanoidGame.getInstance().showScreen(ArkanoidScreen.Gameover);
        }else {
            this.level++;
            prepareGame(this.level);
        }
    }

    public void prepareGame(int level2) {
        if (this.bricks != null) {
            clearGame();
        }
        this.ballBounce = true;
        this.brokenBricks = 0;
        this.lastDropTime = 0;
        this.board.toStart();
        prepareBalls();
        this.fillActor.toStart();
        loadLevel(level2);
    }

    private void prepareBalls() {
        removeBalls();
        for (int i = 0; i < this.ballGameCount; i++) {
            Ball b = new Ball(this.loader, this);
            b.setStandartSpeed(this.builder.getBallStandartSpeed());
            if (this.ballGameCount > 1) {
                b.toStart((this.board.getWidth() / ((float) this.ballGameCount)) * ((float) i));
            } else {
                b.toStart();
            }
            addActor(b);
            this.balls.add(b);
        }
        this.ballCount = this.ballGameCount;
    }

    private void addBall() {
        Ball b = new Ball(this.loader, this);
        b.setStandartSpeed(this.builder.getBallStandartSpeed());
        this.ballCount++;
        addActor(b);
        this.balls.add(b);
    }

    private void removeBalls() {
        Iterator<Ball> i = this.balls.iterator();
        while (i.hasNext()) {
            i.next().remove();
            i.remove();
        }
    }

    public void ballDown(Ball ball) {
        this.ballCount--;
        ball.remove();
        if (this.ballCount == 0) {
            lose(0);
        }
        this.loader.playSound("lose");
    }

    private void clearGame() {
        for (int i = 0; i < this.bricks.length; i++) {
            if (this.bricks[i] != null) {
                this.bricks[i].remove();
                this.bricks[i] = null;
            }
        }
        this.scoreLabel.remove();
        dropsFree();
    }

    private void loadLevel(int level2) {
        try {
            this.builder.load("levels/" + level2 + ".txt");
            this.builder.build();
            this.bricks = this.builder.getBuiltObjects();
            for (int i = 0; i < this.bricks.length; i++) {
                if (this.bricks[i] != null) {
                    addActor(this.bricks[i]);
                }
            }
            addActor(this.scoreLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bricksChange() {
        if (isLevelComleted()) {
            this.fillActor.fully();
            stopGame();
        }
    }

    public void addScore(char type2) {
        if (type2 == 'e') {
            this.score += Input.Keys.FORWARD_DEL;
        } else if (type2 == 'g') {
            this.score += 97;
        } else if (this.builder.isMetalBrick(type2)) {
            this.score += 117;
        } else if (this.builder.isWoodBrick(type2)) {
            this.score += 157;
        } else {
            this.score += 80;
        }
        this.scoreLabel.setValue(this.score);
    }

    public void dropMath(float x, float y) {
        this.lastDropTime++;
        if (this.lastDropTime > 4 && MathUtils.randomBoolean(0.2f)) {
            this.lastDropTime = 0;
            drop(x, y);
        }
    }

    public void drop(float x, float y) {
        int type2;
        if (MathUtils.random() < 0.6f) {
            type2 = MathUtils.random(0, 2);
        } else {
            type2 = MathUtils.random(3, 8);
        }
        Drop drop = new Drop(this.loader, this, x, y, type2);
        this.drops.add(drop);
        addActor(drop);
        this.loader.playSound("dropped");
    }

    public void dropCaught(int type2) {
        this.loader.playSound("pickup");
        switch (type2) {
            case 0:
                this.board.changeSize(3);
                return;
            case 1:
                this.board.changeSize(-3);
                return;
            case 2:
                lose(0);
                return;
            case 3:
                addBall();
                return;
            case 4:
                this.lives++;
                this.livesLabel.setValue(this.lives);
                return;
            case 5:
                explodeAll();
                return;
            case 6:
                this.ballBounce = false;
                return;
            case 7:
                addBallSpeed(-2.0f);
                ballUpdateSpeed();
                return;
            case 8:
                addBallSpeed(2.0f);
                ballUpdateSpeed();
                return;
            default:
        }
    }

    private void dropsFree() {
        Iterator<Drop> i = this.drops.iterator();
        while (i.hasNext()) {
            i.next().remove();
            i.remove();
        }
    }

    public void explode(int x, int y) {
        if (getBrick(x, y - 2) != null) {
            getBrick(x, y - 2).explode();
        }
        if (getBrick(x, y + 2) != null) {
            getBrick(x, y + 2).explode();
        }
        if (getBrick(x - 2, y) != null) {
            getBrick(x - 2, y).explode();
        }
        if (getBrick(x + 2, y) != null) {
            getBrick(x + 2, y).explode();
        }
        for (int j = -1; j < 2; j++) {
            for (int k = -1; k < 2; k++) {
                if (getBrick(x + k, y + j) != null) {
                    getBrick(x + k, y + j).explode();
                }
            }
        }
    }

    private void explodeAll() {
        for (int y = 0; y < this.builder.getHeight(); y++) {
            for (int x = 0; x < this.builder.getWidth(); x++) {
                Brick brick = getBrick(x, y);
                if (brick != null && brick.getType() == 'e') {
                    getBrick(x, y).explode();
                }
            }
        }
    }

    private void toStart() {
        this.board.toStart();
        this.ballBounce = true;
        prepareBalls();
        toStartBalls();
        this.livesLabel.setValue(this.lives);
        dropsFree();
    }

    private void toStartBalls() {
        int a = 0;
        Iterator<Ball> i = this.balls.iterator();
        while (i.hasNext()) {
            Ball b = i.next();
            if (this.ballCount > 1) {
                b.toStart((this.board.getWidth() / ((float) this.ballCount)) * ((float) a));
            } else {
                b.toStart();
            }
            a++;
        }
    }

    public void lose(int step) {
        switch (step) {
            case 0:
                this.lives--;
                this.update = false;
                this.loader.playSound("lose");
                if (this.lives == -1) {
                    this.fillActor.onlyOut();
                    return;
                } else {
                    this.fillActor.fully();
                    return;
                }
            case 1:
                if (isLevelComleted()) {
                    nextLevel();
                    return;
                } else if (this.lives == -1) {
                    stopGame();
                    ArkanoidGame.getInstance().getGameData().setData(new int[]{this.level, this.score, (int) this.time});
                    ArkanoidGame.getInstance().showScreen(ArkanoidScreen.Gameover);
                    return;
                } else {
                    toStart();
                    return;
                }
            case 2:
                startGame();
                return;
            default:
        }
    }

    public void collided(Brick brick) {
        boolean isClearWood = this.builder.isWoodBrick(brick.getType()) && !((BrickWood) brick).isWooded();
        brick.collided();
        if (isClearWood) {
            this.brokenBricks++;
            bricksChange();
        }
        if (!this.builder.isWoodBrick(brick.getType()) || isClearWood) {
            dropMath(brick.getX(), brick.getY());
        }
    }

    public void walled(Location loc) {
        if (loc != Location.BOTTOM) {
            this.loader.playSound("wall");
        }
    }

    public void boarded() {
        this.loader.playSound("board");
    }

    public void removeBrick(int x, int y) {
        if (y < this.builder.getHeight() && x < this.builder.getWidth() && x >= 0 && y >= 0) {
            if (!this.builder.isWoodBrick(this.bricks[(this.builder.getWidth() * y) + x].getType())) {
                this.bricks[(this.builder.getWidth() * y) + x] = null;
                this.brokenBricks++;
                bricksChange();
            } else if (!((BrickWood) this.bricks[(this.builder.getWidth() * y) + x]).isWooded()) {
                this.bricks[(this.builder.getWidth() * y) + x] = null;
                this.brokenBricks++;
                bricksChange();
            } else if (!this.ballBounce) {
                this.bricks[(this.builder.getWidth() * y) + x] = null;
            }
        }
    }

    public Brick getBrick(int x, int y) {
        if (y >= this.builder.getHeight() || x >= this.builder.getWidth() || x < 0 || y < 0) {
            return null;
        }
        return this.bricks[(this.builder.getWidth() * y) + x];
    }

    private boolean isLevelComleted() {
        return this.brokenBricks == this.builder.getBreakBricks();
    }

    public boolean isUpdating() {
        return this.update;
    }

    public Brick[] getBricks() {
        return this.bricks;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setBallStandartSpeed(float value) {
        Iterator<Ball> iter = this.balls.iterator();
        while (iter.hasNext()) {
            iter.next().setStandartSpeed(value);
        }
    }

    public void addBallSpeed(float value) {
        Iterator<Ball> iter = this.balls.iterator();
        while (iter.hasNext()) {
            iter.next().addSpeed(value);
        }
    }

    public void ballUpdateSpeed() {
        Iterator<Ball> iter = this.balls.iterator();
        while (iter.hasNext()) {
            iter.next().updateSpeed();
        }
    }

    public Physics physics() {
        return this.physics;
    }

    public int getGameType() {
        return this.type;
    }

    public boolean isBallBouncing() {
        return this.ballBounce;
    }
}
