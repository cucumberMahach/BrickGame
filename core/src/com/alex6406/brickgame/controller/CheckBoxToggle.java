package com.alex6406.brickgame.controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.gui.CheckBoxActor;

public class CheckBoxToggle extends InputListener {
    private final CheckBoxActor checkBox;
    private final Loader loader;

    public CheckBoxToggle(CheckBoxActor checkBox2, Loader loader2) {
        this.loader = loader2;
        this.checkBox = checkBox2;
    }

    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        this.loader.playSound("click");
        this.checkBox.toggle();
        return true;
    }
}
