package com.alex6406.brickgame.screens;

import com.alex6406.brickgame.animateIcons.IconAbout;
import com.alex6406.brickgame.animateIcons.IconExit;
import com.alex6406.brickgame.animateIcons.IconPlay;
import com.alex6406.brickgame.animateIcons.IconRates;
import com.alex6406.brickgame.animateIcons.IconSettings;
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
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.gui.ButtonActor;
import com.alex6406.brickgame.gui.ImageActor;
import com.alex6406.brickgame.model.WorldMenu;

public class MenuScreen implements Screen {
    private final ButtonActor butAbout;
    private final ButtonActor butExit;
    private final ButtonActor butPlay;
    private final ButtonActor butRates;
    private final ButtonActor butSettings;
    private final OrthographicCamera camera = new OrthographicCamera();
    private final Loader loader;
    private final Stage stage;
    private final ImageActor title;
    private final WorldMenu worldMenu;

    private class ExitButton extends ClickListener {
        private ExitButton() {
        }

        /* synthetic */ ExitButton(MenuScreen menuScreen, ExitButton exitButton) {
            this();
        }

        public void clicked(InputEvent event, float x, float y) {
            Gdx.app.exit();
        }
    }

    public MenuScreen(SpriteBatch batch, Loader loader2) {
        this.loader = loader2;
        this.camera.setToOrtho(false, 1280.0f, 720.0f);
        this.worldMenu = new WorldMenu(new ScreenViewport(this.camera), batch, loader2);
        this.stage = new Stage(new ScreenViewport(this.camera), batch);
        this.title = new ImageActor(loader2.getTileMenu("title_arkanoid"));
        this.title.setPosition(273.0f, 698.0f - this.title.getHeight());
        this.butPlay = new ButtonActor(loader2.getTileMenu("button_play"), loader2);
        this.butPlay.setPosition(21.0f, 538.0f - this.butPlay.getLocHeight());
        this.butSettings = new ButtonActor(loader2.getTileMenu("button_settings"), loader2);
        this.butSettings.setPosition(440.0f, 538.0f - this.butSettings.getLocHeight());
        this.butRates = new ButtonActor(loader2.getTileMenu("button_rates"), loader2);
        this.butRates.setPosition(860.0f, 538.0f - this.butRates.getLocHeight());
        this.butPlay.addListener(new MoveToScreen(ArkanoidScreen.Gamemode));
        this.butSettings.addListener(new MoveToScreen(ArkanoidScreen.Settings));
        this.butRates.addListener(new MoveToScreen(ArkanoidScreen.Rates));
        this.butExit = new ButtonActor(loader2.getTileMenu("button_exit"), loader2);
        this.butExit.setPosition(860.0f, 270.0f - this.butExit.getLocHeight());
        this.butExit.addListener(new ExitButton(this, null));
        this.butAbout = new ButtonActor(loader2.getTileMenu("button_about"), loader2);
        this.butAbout.setPosition(21.0f, 270.0f - this.butAbout.getLocHeight());
        this.butAbout.addListener(new MoveToScreen(ArkanoidScreen.About));
        this.butPlay.setIcon(new IconPlay(loader2), 50.0f, 117.0f);
        this.butSettings.setIcon(new IconSettings(loader2.getTileMenu("object_cogSmall")), 95.0f, 107.0f);
        this.butRates.setIcon(new IconRates(loader2.getTileMenu("object_progressSmall")), 139.0f, 101.0f);
        this.butExit.setIcon(new IconExit(loader2), 135.0f, 98.0f);
        this.butAbout.setIcon(new IconAbout(loader2), 166.0f, 95.0f);
        this.stage.addActor(this.title);
        this.stage.addActor(this.butPlay);
        this.stage.addActor(this.butSettings);
        this.stage.addActor(this.butRates);
        this.stage.addActor(this.butExit);
        this.stage.addActor(this.butAbout);
    }

    public void show() {
        Gdx.input.setInputProcessor(this.stage);
        this.loader.playMusic("menu", true);
    }

    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.worldMenu.act(delta);
        this.stage.act(delta);
        this.worldMenu.draw();
        this.stage.draw();
    }

    public WorldMenu getWorldMenu() {
        return this.worldMenu;
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
