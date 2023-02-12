package com.alex6406.brickgame.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.alex6406.brickgame.screens.AboutScreen;
import com.alex6406.brickgame.screens.GameScreen;
import com.alex6406.brickgame.screens.GamemodeScreen;
import com.alex6406.brickgame.screens.GameoverScreen;
import com.alex6406.brickgame.screens.MenuScreen;
import com.alex6406.brickgame.screens.RatesScreen;
import com.alex6406.brickgame.screens.SettingsScreen;
import com.alex6406.brickgame.screens.TransitionScreen;
import com.alex6406.brickgame.screens.WaitScreen;

public class ArkanoidGame extends Game {
    private static /* synthetic */ int[] $SWITCH_TABLE$engine$ArkanoidScreen = null;
    public static final int BACKGROUND_FPS = 60;
    public static final Color CLEAR_COLOR = new Color(0.0f, 0.0f, 0.0f, 1.0f);
    public static final int FPS = 0;
    public static final boolean FULLSCREEN = false;
    public static final int GAME_HEIGHT = 720;
    public static final int GAME_WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final boolean RESIZABLE = false;
    public static final int SAMPLES = 16;
    public static final String TITLE = "Arkanoid";
    public static final boolean VSYNC = true;
    public static final int WIDTH = 1280;
    private static final ArkanoidGame instance = new ArkanoidGame();
    private AboutScreen aboutScreen;
    private SpriteBatch batch;
    private Config config;
    private GameData gameData;
    private GameScreen gameScreen;
    private GamemodeScreen gamemodeScreen;
    private GameoverScreen gameoverScreen;
    private Screen lastScreen;
    private Loader loader;
    private MenuScreen menuScreen;
    private Screen newScreen;
    private float ppuX;
    private float ppuY;
    private RatesScreen ratesScreen;
    private SettingsScreen settingsScreen;
    private ShapeRenderer shaper;
    private TransitionScreen transitionScreen;
    private WaitScreen waitScreen;

    static /* synthetic */ int[] $SWITCH_TABLE$engine$ArkanoidScreen() {
        int[] iArr = $SWITCH_TABLE$engine$ArkanoidScreen;
        if (iArr == null) {
            iArr = new int[ArkanoidScreen.values().length];
            try {
                iArr[ArkanoidScreen.About.ordinal()] = 7;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ArkanoidScreen.Game.ordinal()] = 4;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ArkanoidScreen.Gamemode.ordinal()] = 5;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ArkanoidScreen.Gameover.ordinal()] = 6;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ArkanoidScreen.Menu.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[ArkanoidScreen.Rates.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[ArkanoidScreen.Settings.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            $SWITCH_TABLE$engine$ArkanoidScreen = iArr;
        }
        return iArr;
    }

    public void create() {
        this.batch = new SpriteBatch();
        this.shaper = new ShapeRenderer();
        Gdx.gl.glClearColor(CLEAR_COLOR.r, CLEAR_COLOR.g, CLEAR_COLOR.b, CLEAR_COLOR.a);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.config = new Config();
        this.config.apply();
        this.loader = new Loader(this.config);
        this.menuScreen = new MenuScreen(this.batch, this.loader);
        this.settingsScreen = new SettingsScreen(this.batch, this.loader, this.menuScreen.getWorldMenu(), this.config);
        this.ratesScreen = new RatesScreen(this.batch, this.loader, this.menuScreen.getWorldMenu(), this.config);
        this.gameScreen = new GameScreen(this.batch, this.loader);
        this.transitionScreen = new TransitionScreen(this.batch, this.loader);
        this.gamemodeScreen = new GamemodeScreen(this.batch, this.loader, this.menuScreen.getWorldMenu(), this.gameScreen);
        this.gameoverScreen = new GameoverScreen(this.batch, this.loader, this.menuScreen.getWorldMenu(), this.gameScreen);
        this.aboutScreen = new AboutScreen(this.batch, this.loader, this.menuScreen.getWorldMenu());
        this.gameData = new GameData(this.config);
        this.lastScreen = null;
        this.waitScreen = new WaitScreen(this.batch, this.loader);
        setScreen(this.waitScreen);
    }

    public GameData getGameData() {
        return this.gameData;
    }

    public void showScreen(ArkanoidScreen screen) {
        this.lastScreen = this.newScreen;
        switch ($SWITCH_TABLE$engine$ArkanoidScreen()[screen.ordinal()]) {
            case 1:
                this.newScreen = this.menuScreen;
                break;
            case 2:
                this.newScreen = this.settingsScreen;
                break;
            case 3:
                this.newScreen = this.ratesScreen;
                break;
            case 4:
                this.newScreen = this.gameScreen;
                break;
            case 5:
                this.newScreen = this.gamemodeScreen;
                break;
            case 6:
                this.newScreen = this.gameoverScreen;
                break;
            case 7:
                this.newScreen = this.aboutScreen;
                break;
        }
        setScreen(this.transitionScreen);
    }

    public void render() {
        super.render();
    }

    public static ArkanoidGame getInstance() {
        return instance;
    }

    public void resize(int width, int height) {
        this.ppuX = ((float) width) / 1280.0f;
        this.ppuY = ((float) height) / 720.0f;
    }

    public float getPpuX() {
        return this.ppuX;
    }

    public float getPpuY() {
        return this.ppuY;
    }

    public Screen getLastScreen() {
        return this.lastScreen;
    }

    public Screen getNewScreen() {
        return this.newScreen;
    }

    public ShapeRenderer getShaper() {
        return this.shaper;
    }
}
