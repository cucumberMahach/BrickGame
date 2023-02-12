package com.alex6406.brickgame.controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.ArkanoidScreen;
import com.alex6406.brickgame.screens.GameScreen;

public class SelectGamemode extends ClickListener {
    private final GameScreen screen;
    private final int type;

    public SelectGamemode(GameScreen screen2, int type2) {
        this.screen = screen2;
        this.type = type2;
    }

    public void clicked(InputEvent event, float x, float y) {
        this.screen.prepare(this.type);
        ArkanoidGame.getInstance().showScreen(ArkanoidScreen.Game);
    }
}
