package com.alex6406.brickgame.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.gui.ScrollBarActor;

public class ScrollBtnMove extends InputListener {
    private final ScrollBarActor scrollBar;

    public ScrollBtnMove(ScrollBarActor scrollBar2) {
        this.scrollBar = scrollBar2;
    }

    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return true;
    }

    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        this.scrollBar.btnDragged(((float) Gdx.input.getX()) / ArkanoidGame.getInstance().getPpuX());
        super.touchDragged(event, x, y, pointer);
    }
}
