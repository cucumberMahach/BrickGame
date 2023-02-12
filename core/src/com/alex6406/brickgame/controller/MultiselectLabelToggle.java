package com.alex6406.brickgame.controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.gui.MultiselectLabel;

public class MultiselectLabelToggle extends InputListener {
    private final Loader loader;
    private final MultiselectLabel multiLabel;

    public MultiselectLabelToggle(MultiselectLabel multiLabel2, Loader loader2) {
        this.loader = loader2;
        this.multiLabel = multiLabel2;
    }

    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        this.multiLabel.change();
        this.loader.playSound("click");
        return true;
    }
}
