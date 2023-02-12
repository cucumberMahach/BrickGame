package com.alex6406.brickgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.alex6406.brickgame.controller.PlayerController;
import com.alex6406.brickgame.engine.Loader;
import com.alex6406.brickgame.model.World;

public class GameScreen implements Screen {
    private final OrthographicCamera camera = new OrthographicCamera();

    /* renamed from: controller  reason: collision with root package name */
    private final PlayerController f225controller;
    private final Loader loader;
    private final InputMultiplexer multiplexer;
    private final World world;

    public GameScreen(SpriteBatch batch, Loader loader2) {
        this.loader = loader2;
        this.camera.setToOrtho(false, 1280.0f, 720.0f);
        this.world = new World(new ScreenViewport(this.camera), batch, loader2);
        this.multiplexer = new InputMultiplexer();
        this.f225controller = new PlayerController(this.world.getBoard());
        this.multiplexer.addProcessor(this.world);
        this.multiplexer.addProcessor(this.f225controller);
    }

    public void prepare(int type) {
        this.world.newGame(type);
        this.loader.stopAllMusic();
    }

    public void show() {
        Gdx.input.setInputProcessor(this.multiplexer);
        this.world.startGame();
    }

    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.world.act(delta);
        this.world.draw();
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
