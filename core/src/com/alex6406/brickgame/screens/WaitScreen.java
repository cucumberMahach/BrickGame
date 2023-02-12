package com.alex6406.brickgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.ArkanoidScreen;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.gui.ImageActor;

public class WaitScreen implements Screen {
    private final SpriteBatch batch;
    private final OrthographicCamera camera = new OrthographicCamera();
    private final ImageActor logo;
    private final Stage stage;
    private float time;

    public WaitScreen(SpriteBatch batch2, Loader loader) {
        this.batch = batch2;
        this.camera.setToOrtho(false, 1280.0f, 720.0f);
        this.stage = new Stage(new ScreenViewport(this.camera), batch2);
        this.logo = new ImageActor(loader.getTileMenu("title_arkanoid"));
        this.logo.setPosition(640.0f - (this.logo.getWidth() / 2.0f), 360.0f - (this.logo.getHeight() / 2.0f));
        this.stage.addActor(this.logo);
    }

    public void show() {
        Gdx.input.setInputProcessor(this.stage);
    }

    public void render(float delta) {
        this.batch.setColor(1.0f, 1.0f, 1.0f, 0.25f * this.time);
        this.time += delta;
        if (this.time > 4.0f) {
            this.batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            ArkanoidGame.getInstance().showScreen(ArkanoidScreen.Menu);
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.stage.act(delta);
        this.stage.draw();
    }

    public void resize(int width, int height) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void hide() {
    }

    public void dispose() {
    }
}
