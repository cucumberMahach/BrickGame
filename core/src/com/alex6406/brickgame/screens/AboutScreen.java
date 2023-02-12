package com.alex6406.brickgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.alex6406.brickgame.controller.MoveToScreen;
import com.alex6406.brickgame.engine.ArkanoidScreen;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.gui.ButtonActor;
import com.alex6406.brickgame.gui.ImageActor;
import com.alex6406.brickgame.model.WorldMenu;

public class AboutScreen implements Screen {
    private final ImageActor box;
    private final ButtonActor butBack;
    private final OrthographicCamera camera = new OrthographicCamera();
    private final ImageActor labelAbout;
    private final Stage stage;
    private final ImageActor title;
    private final WorldMenu worldMenu;

    public AboutScreen(SpriteBatch batch, Loader loader, WorldMenu worldMenu2) {
        this.worldMenu = worldMenu2;
        this.camera.setToOrtho(false, 1280.0f, 720.0f);
        this.stage = new Stage(new ScreenViewport(this.camera), batch);
        this.title = new ImageActor(loader.getTileMenu("title_about"));
        this.title.setPosition(392.0f, 698.0f - this.title.getHeight());
        this.box = new ImageActor(loader.getTileMenu("box"));
        this.box.setPosition(190.0f, 557.0f - this.box.getHeight());
        this.labelAbout = new ImageActor(loader.getTileMenu("label_about"));
        this.labelAbout.setPosition(223.0f, 508.0f - this.labelAbout.getHeight());
        this.butBack = new ButtonActor(loader.getTileMenu("button_back"), loader);
        this.butBack.setPosition(29.0f, 169.0f - this.butBack.getLocHeight());
        this.butBack.addListener(new MoveToScreen(ArkanoidScreen.Menu));
        this.stage.addActor(this.title);
        this.stage.addActor(this.box);
        this.stage.addActor(this.labelAbout);
        this.stage.addActor(this.butBack);
    }

    public void show() {
        Gdx.input.setInputProcessor(this.stage);
    }

    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.worldMenu.act(delta);
        this.stage.act(delta);
        this.worldMenu.draw();
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
