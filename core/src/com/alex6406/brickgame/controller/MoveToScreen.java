package com.alex6406.brickgame.controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.ArkanoidScreen;

public class MoveToScreen extends ClickListener {
    private final ArkanoidScreen screen;

    public MoveToScreen(ArkanoidScreen screen2) {
        this.screen = screen2;
    }

    public void clicked(InputEvent event, float x, float y) {
        ArkanoidGame.getInstance().showScreen(this.screen);
    }
}
