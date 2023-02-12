package com.alex6406.brickgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.alex6406.brickgame.controller.MoveToScreen;
import com.alex6406.brickgame.controller.SelectGamemode;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.ArkanoidScreen;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.gui.ButtonActor;
import com.alex6406.brickgame.gui.ImageActor;
import com.alex6406.brickgame.gui.LabelActor;
import com.alex6406.brickgame.model.WorldMenu;
import com.alex6406.brickgame.view.ScoreLabel;

public class GameoverScreen implements Screen {
    private final ImageActor box;
    private final ButtonActor butMenu;
    private final ButtonActor butRestart;
    private final OrthographicCamera camera = new OrthographicCamera();
    private final LabelActor labLevel;
    private final LabelActor labScore;
    private final LabelActor labTime;
    private final Loader loader;
    private final Stage stage;
    private final ImageActor title;
    private final WorldMenu worldMenu;

    public GameoverScreen(SpriteBatch batch, Loader loader2, WorldMenu worldMenu2, GameScreen screen) {
        this.loader = loader2;
        this.worldMenu = worldMenu2;
        this.camera.setToOrtho(false, 1280.0f, 720.0f);
        this.stage = new Stage(new ScreenViewport(this.camera), batch);
        this.title = new ImageActor(loader2.getTileMenu("title_gameover"));
        this.title.setPosition(205.0f, 698.0f - this.title.getHeight());
        this.box = new ImageActor(loader2.getTileMenu("box_gameover"));
        this.box.setPosition(29.0f, 547.0f - this.box.getHeight());
        this.butMenu = new ButtonActor(loader2.getTileMenu("button_menu"), loader2);
        this.butMenu.setPosition(29.0f, 170.0f - this.butMenu.getLocHeight());
        this.butRestart = new ButtonActor(loader2.getTileMenu("button_restart"), loader2);
        this.butRestart.setPosition(360.0f, 170.0f - this.butRestart.getLocHeight());
        this.butMenu.addListener(new MoveToScreen(ArkanoidScreen.Menu));
        this.butRestart.addListener(new SelectGamemode(screen, -1));
        this.stage.addActor(this.title);
        this.stage.addActor(this.box);
        this.stage.addActor(this.butMenu);
        this.stage.addActor(this.butRestart);
        this.labLevel = new LabelActor(389.0f, 477.0f, Loader.FONT_92, true);
        this.labLevel.setColor(0.1f, 0.5f, 0.0f, 1.0f);
        this.labScore = new LabelActor(389.0f, 374.0f, Loader.FONT_92, true);
        this.labScore.setColor(0.1f, 0.5f, 0.0f, 1.0f);
        this.labTime = new LabelActor(389.0f, 270.0f, Loader.FONT_92, true);
        this.labTime.setColor(0.1f, 0.5f, 0.0f, 1.0f);
        this.stage.addActor(this.labLevel);
        this.stage.addActor(this.labScore);
        this.stage.addActor(this.labTime);
    }

    public void update() {
        this.labLevel.setCaption(String.valueOf(ArkanoidGame.getInstance().getGameData().getLevel()));
        this.labScore.setCaption(ScoreLabel.format(ArkanoidGame.getInstance().getGameData().getScore()));
        this.labTime.setCaption(String.format("%d sec", Integer.valueOf((int) ArkanoidGame.getInstance().getGameData().getTime())));
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
        update();
    }

    public void hide() {
    }

    public void dispose() {
    }
}
