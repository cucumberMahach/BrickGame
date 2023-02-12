package com.alex6406.brickgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;

public class ScoreLabel extends Actor {

    /* renamed from: sb */
    private static final StringBuffer f215sb = new StringBuffer();
    private int cof = 2;
    private final BitmapFont font;
    private int oldValue;
    private int part = 0;
    private String str;
    private float time;
    private boolean updated;
    private int value;
    private int valueTmp;

    public ScoreLabel(Loader loader, float x, float y) {
        this.font = new BitmapFont(Gdx.files.internal(loader.getScoreFont()));
        setPosition(x, y);
    }

    private String getString() {
        if (this.oldValue == this.value && this.str != null) {
            return this.str;
        }
        this.oldValue = this.value;
        this.str = format(this.value);
        return this.str;
    }

    public static String format(int value2) {
        f215sb.delete(0, f215sb.length());
        f215sb.append(value2);
        for (int a = f215sb.length() - 3; a > 0; a -= 3) {
            f215sb.insert(a, ',');
        }
        return f215sb.toString();
    }

    public void draw(Batch batch, float parentAlpha) {
        this.font.draw(batch, getString(), getX() * ArkanoidGame.getInstance().getPpuX(), getY() * ArkanoidGame.getInstance().getPpuY());
    }

    public void act(float delta) {
        this.time += delta;
        if (this.time >= 0.015f) {
            this.time = 0.0f;
            if (this.valueTmp > 0) {
                if (this.updated) {
                    this.cof = 2;
                    this.updated = false;
                }
                this.part = this.valueTmp / this.cof;
                if (this.part == 0) {
                    this.part = 1;
                }
                this.cof++;
                this.value += this.part;
                this.valueTmp -= this.part;
            }
        }
    }

    public void setColor(float r, float g, float b, float a) {
        this.font.setColor(r, g, b, a);
    }

    public void clear() {
        this.value = 0;
        this.valueTmp = 0;
        this.oldValue = 0;
        this.str = null;
    }

    public void setValue(int value2) {
        this.valueTmp = value2 - this.value;
        this.updated = true;
    }
}
