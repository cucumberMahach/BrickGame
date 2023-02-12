package com.alex6406.brickgame.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.gui.ButtonActor;

public class ButtonListener extends InputListener {
    private final ButtonActor button;
    private final Loader loader;

    public ButtonListener(ButtonActor button2, Loader loader2) {
        this.loader = loader2;
        this.button = button2;
    }

    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button2) {
        this.button.setDown(true);
        return true;
    }

    public void touchUp(InputEvent event, float x, float y, int pointer, int button2) {
        this.button.setDown(false);
        if (button2 == 0) {
            this.loader.playSound("click");
        }
    }

    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        this.button.setFocus(true);
    }

    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        this.button.setFocus(false);
    }
}
