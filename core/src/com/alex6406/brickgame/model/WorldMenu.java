package com.alex6406.brickgame.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.gui.ImageActor;

public class WorldMenu extends Stage {
    private ImageActor[] bricks;
    private final Loader loader;

    public WorldMenu(Viewport viewport, Batch batch, Loader loader2) {
        super(viewport, batch);
        this.loader = loader2;
        buildBackground();
    }

    public void buildBackground() {
        int width = (int) Math.ceil(21.33333396911621d);
        int height = (int) Math.ceil(24.0d);
        float offX = ((21.333334f - ((float) width)) * 60.0f) / 2.0f;
        float offY = ((24.0f - ((float) height)) * 30.0f) / 2.0f;
        this.bricks = new ImageActor[(width * height)];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.bricks[(y * width) + x] = new ImageActor(this.loader.getBricks(2), (((float) x) * 60.0f) + offX, 720.0f - (((((float) y) * 30.0f) + 30.0f) + offY), 60.0f, 30.0f);
                addActor(this.bricks[(y * width) + x]);
            }
        }
    }
}
