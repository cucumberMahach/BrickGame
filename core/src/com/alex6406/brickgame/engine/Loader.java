package com.alex6406.brickgame.engine;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.net.HttpStatus;
import java.util.HashMap;

public class Loader {
    public static final float BALL_SCALE_ANDROID = 1.2f;
    public static final float BALL_SCALE_DESKTOP = 1.0f;
    public static final float BOARD_SCALE_ANDROID = 1.2f;
    public static final float BOARD_SCALE_DESKTOP = 1.0f;
    public static final int BOARD_SIZE_ANDROID = 5;
    public static final int BOARD_SIZE_DESKTOP = 3;
    public static final float BRICK_GAME_HEIGHT = 30.0f;
    public static final float BRICK_GAME_WIDTH = 60.0f;
    public static final int BRICK_HEIGHT = 30;
    public static final float BRICK_VISUAL_HEIGHT = 30.0f;
    public static final float BRICK_VISUAL_WIDTH = 60.0f;
    public static final int BRICK_WIDTH = 60;
    public static final float DROP_SCALE_ANDROID = 1.5f;
    public static final float DROP_SCALE_DESKTOP = 1.0f;
    public static final String FONT_128 = "fonts/font128.fnt";
    public static final String FONT_150 = "fonts/font150.fnt";
    public static final String FONT_72 = "fonts/font72.fnt";
    public static final String FONT_92 = "fonts/font92.fnt";
    public static final String PREFIX = "";
    private final HashMap<String, TextureRegion[]> animations;
    private final TextureRegion[] bricks;
    private final Config config;
    private final boolean isAndroid;
    private final HashMap<String, Music> music;
    private final HashMap<String, Sound> sounds;
    private final HashMap<String, TextureRegion> tiles;
    private final HashMap<String, TextureRegion> tilesMenu;

    public Loader(Config config2) {
        this.config = config2;
        this.isAndroid = Gdx.app.getType() == Application.ApplicationType.Android;
        Texture atlas = new Texture("images/tilesMenu.png");
        this.tilesMenu = new HashMap<>();
        this.tilesMenu.put("title_arkanoid", new TextureRegion(atlas, 0, 0, 749, 129));
        this.tilesMenu.put("title_settings", new TextureRegion(atlas, 0, 129, 736, 129));
        this.tilesMenu.put("title_rates", new TextureRegion(atlas, 0, 258, 960, 129));
        this.tilesMenu.put("title_gamemode", new TextureRegion(atlas, 0, 387, 854, 129));
        this.tilesMenu.put("title_gameover", new TextureRegion(atlas, 0, 1874, 870, 129));
        this.tilesMenu.put("title_about", new TextureRegion(atlas, 0, 2003, 496, 129));
        this.tilesMenu.put("button_play", new TextureRegion(atlas, 0, 774, 400, 250));
        this.tilesMenu.put("button_settings", new TextureRegion(atlas, 400, 774, 400, 250));
        this.tilesMenu.put("button_rates", new TextureRegion(atlas, 1000, 624, 400, 250));
        this.tilesMenu.put("button_ok", new TextureRegion(atlas, 1200, 1024, 300, 150));
        this.tilesMenu.put("button_back", new TextureRegion(atlas, 800, 874, 300, 150));
        this.tilesMenu.put("button_reset", new TextureRegion(atlas, 1100, 874, 300, 150));
        this.tilesMenu.put("button_mode1", new TextureRegion(atlas, 0, 1024, 600, 500));
        this.tilesMenu.put("button_mode2", new TextureRegion(atlas, 600, 1024, 600, 500));
        this.tilesMenu.put("button_menu", new TextureRegion(atlas, 1500, 1024, 300, 150));
        this.tilesMenu.put("button_restart", new TextureRegion(atlas, 1200, 1174, 878, 150));
        this.tilesMenu.put("button_exit", new TextureRegion(atlas, 1209, 1324, 400, 250));
        this.tilesMenu.put("button_about", new TextureRegion(atlas, 1609, 1324, 400, 250));
        this.tilesMenu.put("label_settings", new TextureRegion(atlas, 1400, 600, 508, 304));
        this.tilesMenu.put("label_about", new TextureRegion(atlas, 1209, 1574, 834, 425));
        this.tilesMenu.put("object_cog", new TextureRegion(atlas, 0, 574, 200, 200));
        this.tilesMenu.put("object_progress", new TextureRegion(atlas, 200, 604, 42, 170));
        this.tilesMenu.put("object_arrowSmall", new TextureRegion(atlas, 242, 672, 103, 102));
        this.tilesMenu.put("object_cogSmall", new TextureRegion(atlas, 345, 644, 130, 130));
        this.tilesMenu.put("object_progressSmall", new TextureRegion(atlas, 475, 637, 34, 137));
        this.tilesMenu.put("object_board", new TextureRegion(atlas, 0, 540, 237, 34));
        this.tilesMenu.put("object_ball", new TextureRegion(atlas, 237, 539, 31, 31));
        this.tilesMenu.put("object_bricks1", new TextureRegion(atlas, 345, 520, 327, 117));
        this.tilesMenu.put("object_bricks2", new TextureRegion(atlas, 509, 657, 246, 117));
        this.tilesMenu.put("object_door", new TextureRegion(atlas, 672, 516, 89, 133));
        this.tilesMenu.put("object_knob", new TextureRegion(atlas, 268, 547, 23, 23));
        this.tilesMenu.put("object_i", new TextureRegion(atlas, 761, 643, 45, 131));
        this.tilesMenu.put("box", new TextureRegion(atlas, 1020, 0, 900, 500));
        this.tilesMenu.put("scroll_bar", new TextureRegion(atlas, 1370, 500, 550, 20));
        this.tilesMenu.put("scroll_button", new TextureRegion(atlas, 1820, 520, 100, 70));
        this.tilesMenu.put("checkbox_on", new TextureRegion(atlas, 1740, 520, 80, 80));
        this.tilesMenu.put("checkbox_off", new TextureRegion(atlas, 1660, 520, 80, 80));
        this.tilesMenu.put("list_item", new TextureRegion(atlas, 810, 520, 850, 70));
        this.tilesMenu.put("list_scroll_bar", new TextureRegion(atlas, 960, 0, 60, 500));
        this.tilesMenu.put("list_scroll_button", new TextureRegion(atlas, 900, 0, 60, 150));
        this.tilesMenu.put("box_gameover", new TextureRegion(atlas, 0, 1524, 1209, 350));
        this.tilesMenu.put("yellow", new TextureRegion(atlas, 80, 836, 100, 100));
        this.tilesMenu.put("object_arrowYellow", new TextureRegion(atlas, 242, 570, 103, 102));
        this.sounds = new HashMap<>();
        this.sounds.put("wall", Gdx.audio.newSound(Gdx.files.internal("sounds/wall.wav")));
        this.sounds.put("explode", Gdx.audio.newSound(Gdx.files.internal("sounds/explode.wav")));
        this.sounds.put("hit", Gdx.audio.newSound(Gdx.files.internal("sounds/hit.wav")));
        this.sounds.put("board", Gdx.audio.newSound(Gdx.files.internal("sounds/board.wav")));
        this.sounds.put("brick", Gdx.audio.newSound(Gdx.files.internal("sounds/brick.wav")));
        this.sounds.put("lose", Gdx.audio.newSound(Gdx.files.internal("sounds/lose.wav")));
        this.sounds.put("pickup", Gdx.audio.newSound(Gdx.files.internal("sounds/pickup.wav")));
        this.sounds.put("dropped", Gdx.audio.newSound(Gdx.files.internal("sounds/dropped.wav")));
        this.sounds.put("glass", Gdx.audio.newSound(Gdx.files.internal("sounds/glass.wav")));
        this.sounds.put("metal", Gdx.audio.newSound(Gdx.files.internal("sounds/metal.wav")));
        this.sounds.put("wood", Gdx.audio.newSound(Gdx.files.internal("sounds/wood.wav")));
        this.sounds.put("start", Gdx.audio.newSound(Gdx.files.internal("sounds/start.wav")));
        this.sounds.put("click", Gdx.audio.newSound(Gdx.files.internal("sounds/click.wav")));
        this.music = new HashMap<>();
        this.music.put("menu", Gdx.audio.newMusic(Gdx.files.internal("music/menu.mp3")));
        Texture atlas2 = new Texture("images/tiles.png");
        this.bricks = new TextureRegion[9];
        for (int i = 0; i < this.bricks.length; i++) {
            this.bricks[i] = new TextureRegion(atlas2, i * 60, 0, 60, 30);
        }
        this.tiles = new HashMap<>();
        this.tiles.put("board_left", new TextureRegion(atlas2, 0, 468, 20, 24));
        this.tiles.put("board_center", new TextureRegion(atlas2, 0, 424, 24, 20));
        this.tiles.put("board_right", new TextureRegion(atlas2, 0, 444, 20, 24));
        this.tiles.put("ball", new TextureRegion(atlas2, 225, 494, 18, 18));
        this.tiles.put("glass", new TextureRegion(atlas2, 0, 120, 60, 30));
        this.tiles.put("metal", new TextureRegion(atlas2, 60, 120, 60, 30));
        this.tiles.put("wood", new TextureRegion(atlas2, 0, 150, 60, 30));
        this.tiles.put("live", new TextureRegion(atlas2, 988, 501, 36, 11));
        this.tiles.put("black", new TextureRegion(atlas2, 974, 0, 50, 50));
        for (int i2 = 0; i2 < 9; i2++) {
            this.tiles.put("drop" + i2, new TextureRegion(atlas2, (i2 * 45) + 243, 467, 45, 45));
        }
        this.animations = new HashMap<>();
        TextureRegion[] remove = new TextureRegion[8];
        for (int i3 = 0; i3 < remove.length; i3++) {
            remove[i3] = new TextureRegion(atlas2, i3 * 60, 30, 60, 30);
        }
        this.animations.put("remove", remove);
        TextureRegion[] glowing = new TextureRegion[8];
        for (int i4 = 0; i4 < glowing.length; i4++) {
            glowing[i4] = new TextureRegion(atlas2, i4 * 60, 60, 60, 30);
        }
        this.animations.put("glowing", glowing);
        TextureRegion[] glowing2 = new TextureRegion[2];
        for (int i5 = 0; i5 < glowing2.length; i5++) {
            glowing2[i5] = new TextureRegion(atlas2, i5 * 60, 90, 60, 30);
        }
        this.animations.put("glowing2", glowing2);
        TextureRegion[] explode = new TextureRegion[8];
        for (int i6 = 0; i6 < explode.length; i6++) {
            explode[i6] = new TextureRegion(atlas2, i6 * 60, 180, 60, 30);
        }
        this.animations.put("explode", explode);
        TextureRegion[] multi = new TextureRegion[8];
        for (int i7 = 0; i7 < multi.length; i7++) {
            multi[i7] = new TextureRegion(atlas2, (i7 * 60) + 60, 0, 60, 30);
        }
        this.animations.put("multi", multi);
    }

    public TextureRegion[] getAnimation(String name) {
        return this.animations.get(name);
    }

    public void playSound(String name) {
        this.sounds.get(name).setVolume(this.sounds.get(name).play(), this.config.getSoundVolume());
    }

    public void playMusic(String name, boolean looping) {
        this.music.get(name).setVolume(this.config.getMusicVolume());
        this.music.get(name).play();
        this.music.get(name).setLooping(looping);
    }

    public void stopMusic(String name) {
        this.music.get(name).stop();
    }

    public boolean isMusicPlaying(String name) {
        return this.music.get(name).isPlaying();
    }

    public void setMusicVolume(float value) {
        for (Music volume : this.music.values()) {
            volume.setVolume(this.config.getMusicVolume());
        }
    }

    public void stopAllMusic() {
        for (Music stop : this.music.values()) {
            stop.stop();
        }
    }

    public void nextMusic(String name, boolean looping) {
        stopAllMusic();
        playMusic(name, looping);
    }

    public void setMusicLooping(String name, boolean value) {
        this.music.get(name).setLooping(value);
    }

    public TextureRegion getTile(String name) {
        return this.tiles.get(name);
    }

    public TextureRegion getTileMenu(String name) {
        return this.tilesMenu.get(name);
    }

    public TextureRegion getBricks(int index) {
        return this.bricks[index];
    }

    public float getBoardScale() {
        return this.isAndroid ? 1.2f : 1.0f;
    }

    public float getBallScale() {
        return this.isAndroid ? 1.2f : 1.0f;
    }

    public float getDropScale() {
        return this.isAndroid ? 1.5f : 1.0f;
    }

    public String getScoreFont() {
        return this.isAndroid ? FONT_92 : FONT_72;
    }

    public int getBoardSize() {
        return this.isAndroid ? 5 : 3;
    }

    public boolean isAndroid() {
        return this.isAndroid;
    }
}
