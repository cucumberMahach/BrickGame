package com.alex6406.brickgame.screens;

import com.alex6406.brickgame.animateIcons.IconRates;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.alex6406.brickgame.controller.MoveToScreen;
import com.alex6406.brickgame.engine.ArkanoidScreen;
import com.alex6406.brickgame.engine.Config;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.gui.ButtonActor;
import com.alex6406.brickgame.gui.ImageActor;
import com.alex6406.brickgame.gui.ListActor;
import com.alex6406.brickgame.model.WorldMenu;

public class RatesScreen implements Screen {
    private final ButtonActor butBack;
    private final ButtonActor butReset;
    private final OrthographicCamera camera = new OrthographicCamera();
    private final Config config;
    private final IconRates icon;
    private final ListActor list;
    private final Stage stage;
    private final ImageActor title;
    private final WorldMenu worldMenu;

    private class ResetRates extends ClickListener {
        private final RatesScreen screen;

        public ResetRates(RatesScreen screen2) {
            this.screen = screen2;
        }

        public void clicked(InputEvent event, float x, float y) {
            this.screen.resetRates();
        }
    }

    public RatesScreen(SpriteBatch batch, Loader loader, WorldMenu worldMenu2, Config config2) {
        this.config = config2;
        this.worldMenu = worldMenu2;
        this.camera.setToOrtho(false, 1280.0f, 720.0f);
        this.stage = new Stage(new ScreenViewport(this.camera), batch);
        this.title = new ImageActor(loader.getTileMenu("title_rates"));
        this.title.setPosition(159.0f, 698.0f - this.title.getHeight());
        this.butBack = new ButtonActor(loader.getTileMenu("button_back"), loader);
        this.butBack.setPosition(29.0f, 169.0f - this.butBack.getLocHeight());
        this.butReset = new ButtonActor(loader.getTileMenu("button_reset"), loader);
        this.butReset.setPosition(29.0f, 348.0f - this.butReset.getLocHeight());
        this.butReset.addListener(new ResetRates(this));
        this.butBack.addListener(new MoveToScreen(ArkanoidScreen.Menu));
        this.list = new ListActor(loader, config2.scoreToString());
        this.list.setPosition(353.0f, 519.0f - this.list.getHeight());
        this.icon = new IconRates(loader.getTileMenu("object_progress"));
        this.icon.setPosition(104.0f, 365.0f);
        this.stage.addActor(this.title);
        this.stage.addActor(this.list);
        this.stage.addActor(this.butBack);
        this.stage.addActor(this.butReset);
        this.stage.addActor(this.icon);
    }

    public void resetRates() {
        this.config.resetScore();
        update();
    }

    public void update() {
        this.list.setItems(this.config.scoreToString());
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
        update();
    }

    public void hide() {
    }

    public void dispose() {
    }
}
