package com.alex6406.brickgame.screens;

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
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.ArkanoidScreen;
import com.alex6406.brickgame.engine.Config;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.gui.ButtonActor;
import com.alex6406.brickgame.gui.CheckBoxActor;
import com.alex6406.brickgame.gui.ImageActor;
import com.alex6406.brickgame.gui.ScrollBarActor;
import com.alex6406.brickgame.gui.ScrollBarListener;
import com.alex6406.brickgame.model.WorldMenu;

public class SettingsScreen implements Screen {
    private final ImageActor box;
    private final ButtonActor butBack;
    private final ButtonActor butOk;
    private final OrthographicCamera camera = new OrthographicCamera();
    private final CheckBoxActor chkFullScreen;
    private final CheckBoxActor chkVSync;
    private final Config config;
    private ImageActor curtain;
    private final IconSettings icon;
    private final ImageActor labels;
    private final ScrollBarActor scrMusic;
    private final ScrollBarActor scrSound;
    private final Stage stage;
    private final ImageActor title;
    private final WorldMenu worldMenu;

    private class SaveSettings extends ClickListener {
        private final SettingsScreen screen;

        public SaveSettings(SettingsScreen screen2) {
            this.screen = screen2;
        }

        public void clicked(InputEvent event, float x, float y) {
            this.screen.saveSettings();
        }
    }

    private class ScrollMusicChanger implements ScrollBarListener {
        private final Config config;
        private final Loader loader;

        public ScrollMusicChanger(Config config2, Loader loader2) {
            this.config = config2;
            this.loader = loader2;
        }

        public void dragged(float value) {
            this.config.setMusicVolume(value);
            this.loader.setMusicVolume(value);
        }
    }

    private class ScrollSoundChanger implements ScrollBarListener {
        private final Config config;

        public ScrollSoundChanger(Config config2) {
            this.config = config2;
        }

        public void dragged(float value) {
            this.config.setSoundVolume(value);
        }
    }

    public SettingsScreen(SpriteBatch batch, Loader loader, WorldMenu worldMenu2, Config config2) {
        this.worldMenu = worldMenu2;
        this.config = config2;
        this.camera.setToOrtho(false, 1280.0f, 720.0f);
        this.stage = new Stage(new ScreenViewport(this.camera), batch);
        this.title = new ImageActor(loader.getTileMenu("title_settings"));
        this.title.setPosition(273.0f, 698.0f - this.title.getHeight());
        this.butBack = new ButtonActor(loader.getTileMenu("button_back"), loader);
        this.butBack.setPosition(29.0f, 169.0f - this.butBack.getLocHeight());
        this.butOk = new ButtonActor(loader.getTileMenu("button_ok"), loader);
        this.butOk.setPosition(29.0f, 348.0f - this.butOk.getLocHeight());
        this.butOk.addListener(new SaveSettings(this));
        this.butBack.addListener(new MoveToScreen(ArkanoidScreen.Menu));
        this.box = new ImageActor(loader.getTileMenu("box"));
        this.box.setPosition(353.0f, 519.0f - this.box.getHeight());
        this.labels = new ImageActor(loader.getTileMenu("label_settings"));
        this.labels.setPosition(374.0f, 498.0f - this.labels.getHeight());
        this.scrMusic = new ScrollBarActor(loader);
        this.scrMusic.setPosition(669.0f, 483.0f - this.scrMusic.getHeight());
        this.scrSound = new ScrollBarActor(loader);
        this.scrSound.setPosition(669.0f, 397.0f - this.scrMusic.getHeight());
        this.chkFullScreen = new CheckBoxActor(loader);
        this.chkFullScreen.setPosition(922.0f, 348.0f - this.chkFullScreen.getLocHeight());
        this.chkVSync = new CheckBoxActor(loader);
        this.chkVSync.setPosition(710.0f, 263.0f - this.chkVSync.getLocHeight());
        this.icon = new IconSettings(loader.getTileMenu("object_cog"));
        this.icon.setPosition(38.0f, 384.0f);
        this.scrMusic.setListener(new ScrollMusicChanger(config2, loader));
        this.scrSound.setListener(new ScrollSoundChanger(config2));
        this.stage.addActor(this.title);
        this.stage.addActor(this.box);
        this.stage.addActor(this.labels);
        this.stage.addActor(this.butBack);
        this.stage.addActor(this.butOk);
        this.stage.addActor(this.scrMusic);
        this.stage.addActor(this.scrSound);
        if (!loader.isAndroid()) {
            this.stage.addActor(this.chkFullScreen);
            this.stage.addActor(this.chkVSync);
        } else {
            this.curtain = new ImageActor(loader.getTileMenu("yellow"));
            this.curtain.setPosition(372.0f, 184.0f);
            this.curtain.setSize(512.0f, 168.0f);
            this.stage.addActor(this.curtain);
        }
        this.stage.addActor(this.icon);
    }

    public void saveSettings() {
        this.config.setSettings(this.scrMusic.getValue(), this.scrSound.getValue(), this.chkFullScreen.getChecked(), this.chkVSync.getChecked());
        this.config.apply();
        ArkanoidGame.getInstance().showScreen(ArkanoidScreen.Menu);
    }

    public void update() {
        this.scrMusic.setValue(this.config.getMusicVolume());
        this.scrSound.setValue(this.config.getSoundVolume());
        this.chkFullScreen.setChecked(this.config.getFullscreen());
        this.chkVSync.setChecked(this.config.getvSync());
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
