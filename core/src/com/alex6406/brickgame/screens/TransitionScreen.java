package com.alex6406.brickgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.model.WorldTransition;

public class TransitionScreen implements Screen {
    private final OrthographicCamera camera = new OrthographicCamera();
    private Screen lastScreen;
    private Screen newScreen;
    private final WorldTransition world;

    public TransitionScreen(SpriteBatch batch, Loader loader) {
        this.camera.setToOrtho(false, 1280.0f, 720.0f);
        this.world = new WorldTransition(new ScreenViewport(this.camera), batch, loader, this);
    }

    public void show() {
        Gdx.input.setInputProcessor(null);
        this.lastScreen = ArkanoidGame.getInstance().getLastScreen();
        this.newScreen = ArkanoidGame.getInstance().getNewScreen();
        this.newScreen.resume();
        this.world.show();
    }

    public void render(float delta) {
        if (this.lastScreen != null) {
            this.lastScreen.render(delta);
        } else {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }
        if (this.world.isHiding()) {
            this.newScreen.render(delta);
        }
        this.world.act(delta);
        this.world.draw();
    }

    public void end() {
        ArkanoidGame.getInstance().setScreen(this.newScreen);
    }

    public void resize(int width, int height) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    public void dispose() {
    }
}
