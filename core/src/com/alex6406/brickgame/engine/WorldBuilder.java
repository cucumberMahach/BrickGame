package com.alex6406.brickgame.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import java.io.BufferedReader;
import java.io.IOException;
import com.alex6406.brickgame.model.World;
import com.alex6406.brickgame.view.Brick;
import com.alex6406.brickgame.view.BrickExplosive;
import com.alex6406.brickgame.view.BrickGlass;
import com.alex6406.brickgame.view.BrickMetal;
import com.alex6406.brickgame.view.BrickWood;

public class WorldBuilder {
    private float ballStandartSpeed;
    private int breakBricks;
    private String[] config;
    private int height;
    private final Loader loader;
    private char[] map;
    private Brick[] objects;
    private int width;
    private final World world;

    public WorldBuilder(Loader loader2, World world2) {
        this.loader = loader2;
        this.world = world2;
    }

    public void load(String filename) throws IOException {
        BufferedReader bf = Gdx.files.internal(filename).reader(1024);
        int y = -1;
        int[] size = getCount(filename);
        this.width = size[0];
        this.height = size[1];
        this.map = new char[(this.width * this.height)];
        while (bf.ready()) {
            String str = bf.readLine();
            if (y == -1) {
                this.config = str.split(";");
            } else {
                for (int x = 0; x < str.length(); x++) {
                    this.map[(this.width * y) + x] = str.charAt(x);
                }
            }
            y++;
        }
        bf.close();
    }

    public int[] getCount(String filename) throws IOException {
        BufferedReader bf = Gdx.files.internal(filename).reader(1024);
        int[] size = new int[2];
        while (bf.ready()) {
            int width2 = bf.readLine().length();
            if (size[1] == 0) {
                size[1] = size[1] + 1;
            } else {
                if (width2 > size[0]) {
                    size[0] = width2;
                }
                size[1] = size[1] + 1;
            }
        }
        bf.close();
        size[1] = size[1] - 1;
        return size;
    }

    public void build(float ofX, float ofY, boolean centered, boolean yTop) {
        this.breakBricks = 0;
        this.objects = new Brick[(this.width * this.height)];
        if (centered) {
            ofX = 640.0f - ((((float) this.width) * 60.0f) / 2.0f);
        }
        if (!yTop) {
            ofY = (720.0f - (((float) this.height) * 30.0f)) - ofY;
        }
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                char ch = this.map[(this.width * y) + x];
                if (ch != '*') {
                    if (ch == 'e') {
                        this.objects[(this.width * y) + x] = new BrickExplosive(this.loader, (((float) x) * 60.0f) + ofX, 720.0f - (((((float) y) * 30.0f) + 30.0f) + ofY), 'e', x, y, this.world);
                    } else if (ch == 'g') {
                        this.objects[(this.width * y) + x] = new BrickGlass(this.loader, (((float) x) * 60.0f) + ofX, 720.0f - (((((float) y) * 30.0f) + 30.0f) + ofY), 'g', x, y, this.world);
                    } else if (isMetalBrick(ch)) {
                        this.objects[(this.width * y) + x] = new BrickMetal(this.loader, (((float) x) * 60.0f) + ofX, 720.0f - (((((float) y) * 30.0f) + 30.0f) + ofY), ch, x, y, this.world);
                    } else if (isWoodBrick(ch)) {
                        this.objects[(this.width * y) + x] = new BrickWood(this.loader, (((float) x) * 60.0f) + ofX, 720.0f - (((((float) y) * 30.0f) + 30.0f) + ofY), ch, x, y, this.world);
                    } else if (ch == 'w') {
                        this.objects[(this.width * y) + x] = new BrickWood(this.loader, ofX + (((float) x) * 60.0f), 720.0f - (((((float) y) * 30.0f) + 30.0f) + ofY), 'P', x, y, this.world, true);
                        this.breakBricks--;
                    } else {
                        this.objects[(this.width * y) + x] = new Brick(this.loader, (((float) x) * 60.0f) + ofX, 720.0f - (((((float) y) * 30.0f) + 30.0f) + ofY), ch, x, y, this.world);
                    }
                    this.breakBricks++;
                }
            }
        }
    }

    public void build() {
        build(Float.valueOf(this.config[0]).floatValue(), Float.valueOf(this.config[1]).floatValue(), Boolean.valueOf(this.config[2]).booleanValue(), Boolean.valueOf(this.config[3]).booleanValue());
        if (this.config.length == 5) {
            this.ballStandartSpeed = Float.valueOf(this.config[4]).floatValue();
            this.world.setBallStandartSpeed(this.ballStandartSpeed);
        }
    }

    public void printMap() {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                System.out.print(this.map[(this.width * y) + x]);
            }
            System.out.println();
        }
    }

    public void printBuildedMap() {
        System.out.println("=============================");
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                System.out.print(this.objects[(this.width * y) + x] == null ? '-' : Character.forDigit(this.objects[(this.width * y) + x].getType(), 10));
            }
            System.out.println();
        }
        System.out.println("=============================");
    }

    public boolean isWoodBrick(char type) {
        return isInColorfulRange(type - 'H');
    }

    public boolean isMetalBrick(char type) {
        return isInColorfulRange(type - '@');
    }

    public boolean isInColorfulRange(int index) {
        return index > 0 && index <= 8;
    }

    public Brick[] getBuiltObjects() {
        return this.objects;
    }

    public int getBreakBricks() {
        return this.breakBricks;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public float getBallStandartSpeed() {
        return this.ballStandartSpeed;
    }
}
