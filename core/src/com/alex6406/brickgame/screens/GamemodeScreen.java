package com.alex6406.brickgame.screens;

import com.alex6406.brickgame.animateIcons.IconMode1;
import com.alex6406.brickgame.animateIcons.IconMode2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.alex6406.brickgame.controller.SelectGamemode;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.gui.ButtonActor;
import com.alex6406.brickgame.gui.ImageActor;
import com.alex6406.brickgame.model.WorldMenu;

public class GamemodeScreen implements Screen {
    private final ButtonActor butMode1;
    private final ButtonActor butMode2;
    private final OrthographicCamera camera = new OrthographicCamera();
    private final Stage stage;
    private final ImageActor title;
    private final WorldMenu worldMenu;

    public GamemodeScreen(SpriteBatch batch, Loader loader, WorldMenu worldMenu2, GameScreen gameScreen) {
        this.worldMenu = worldMenu2;
        this.camera.setToOrtho(false, 1280.0f, 720.0f);
        this.stage = new Stage(new ScreenViewport(this.camera), batch);
        this.title = new ImageActor(loader.getTileMenu("title_gamemode"));
        this.title.setPosition(213.0f, 698.0f - this.title.getHeight());
        this.butMode1 = new ButtonActor(loader.getTileMenu("button_mode1"), loader);
        this.butMode1.setPosition(29.0f, 530.0f - this.butMode1.getLocHeight());
        this.butMode2 = new ButtonActor(loader.getTileMenu("button_mode2"), loader);
        this.butMode2.setPosition(651.0f, 530.0f - this.butMode1.getLocHeight());
        this.butMode1.setIcon(new IconMode1(loader), 134.0f, 215.0f);
        this.butMode2.setIcon(new IconMode2(loader), 175.0f, 218.0f);
        this.stage.addActor(this.title);
        this.stage.addActor(this.butMode1);
        this.stage.addActor(this.butMode2);
        this.butMode1.addListener(new SelectGamemode(gameScreen, 0));
        this.butMode2.addListener(new SelectGamemode(gameScreen, 1));
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
