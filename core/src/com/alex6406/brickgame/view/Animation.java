package com.alex6406.brickgame.view;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
    private int currentFrame;
    private float currentTime;
    private final float fps;
    private final TextureRegion[] frames;
    private final boolean invent;
    private boolean isPlaying;
    private final boolean looping;

    public Animation(TextureRegion[] frames2, float time, boolean looping2, boolean invent2, float fps2) {
        this.frames = frames2;
        this.fps = fps2 <= 0.0f ? time / ((float) frames2.length) : fps2;
        this.looping = looping2;
        this.invent = invent2;
        this.currentTime = 0.0f;
        this.currentFrame = 0;
    }

    public Animation(TextureRegion[] frames2, float time) {
        this(frames2, time, false);
    }

    public Animation(TextureRegion[] frames2, float time, boolean looping2) {
        this(frames2, time, looping2, false);
    }

    public Animation(TextureRegion[] frames2, float time, boolean looping2, boolean invent2) {
        this(frames2, time, looping2, invent2, 0.0f);
    }

    public TextureRegion getFrame() {
        return this.frames[this.invent ? (this.frames.length - this.currentFrame) - 1 : this.currentFrame];
    }

    public void update(float delta) {
        if (this.isPlaying) {
            this.currentTime += delta;
            while (this.currentTime >= this.fps) {
                nextFrame();
                this.currentTime -= this.fps;
            }
        }
    }

    private void nextFrame() {
        if (this.currentFrame == this.frames.length - 1) {
            this.currentFrame = 0;
            if (!this.looping) {
                this.isPlaying = false;
                return;
            }
            return;
        }
        this.currentFrame++;
    }

    public void setTimeOffset(float offset) {
        this.currentTime += offset;
    }

    public void play() {
        this.isPlaying = true;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }
}
