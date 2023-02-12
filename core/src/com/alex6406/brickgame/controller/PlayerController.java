package com.alex6406.brickgame.controller;

import com.badlogic.gdx.InputAdapter;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.view.Board;

public class PlayerController extends InputAdapter {
    private final Board board;

    public PlayerController(Board board2) {
        this.board = board2;
    }

    public boolean mouseMoved(int screenX, int screenY) {
        touchDragged(screenX, screenY, -1);
        return true;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        this.board.setX(((float) ((int) (((float) screenX) / ArkanoidGame.getInstance().getPpuX()))) - (this.board.getWidth() / 2.0f));
        return true;
    }
}
