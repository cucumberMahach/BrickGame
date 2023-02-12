package com.alex6406.brickgame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.alex6406.brickgame.engine.ArkanoidGame;
import com.alex6406.brickgame.engine.Loader;

public class ListActor extends Actor {
    private final BitmapFont font;
    private String[] items;
    private final TextureRegion texBox;
    private final TextureRegion texItem;

    public ListActor(Loader loader, float x, float y, String[] items2) {
        this.texBox = loader.getTileMenu("box");
        this.texItem = loader.getTileMenu("list_item");
        this.items = items2;
        setPosition(x, y);
        setSize((float) this.texBox.getRegionWidth(), (float) this.texBox.getRegionHeight());
        this.font = new BitmapFont(Gdx.files.internal(Loader.FONT_72));
        this.font.setColor(0.5f, 0.0f, 0.0f, 1.0f);
    }

    public ListActor(Loader loader, String[] items2) {
        this(loader, 0.0f, 0.0f, items2);
    }

    public void draw(Batch batch, float parentAlpha) {
        float ppux = ArkanoidGame.getInstance().getPpuX();
        float ppuy = ArkanoidGame.getInstance().getPpuY();
        batch.draw(this.texBox, getX() * ppux, getY() * ppuy, getWidth() * ppux, getHeight() * ppuy);
        for (int a = 0; a < this.items.length; a++) {
            float itemX = getX() + 10.0f;
            float itemY = (((getHeight() + getY()) - ((float) this.texItem.getRegionHeight())) - 10.0f) - ((float) (this.texItem.getRegionHeight() * a));
            Batch batch2 = batch;
            batch2.draw(this.texItem, itemX * ppux, itemY * ppuy, ((float) (this.texItem.getRegionWidth() + 30)) * ppux, ((float) this.texItem.getRegionHeight()) * ppuy);
            this.font.draw(batch, String.valueOf(a + 1), (30.0f + itemX) * ppux, ((this.font.getCapHeight() / 2.0f) + itemY + ((float) (this.texItem.getRegionHeight() / 2))) * ppuy);
            if (this.items[a] != null) {
                this.font.draw(batch, this.items[a], (30.0f + itemX + 85.0f) * ppux, ((this.font.getCapHeight() / 2.0f) + itemY + ((float) (this.texItem.getRegionHeight() / 2))) * ppuy);
            }
        }
    }

    public void setItems(String[] items2) {
        this.items = items2;
    }

    public void setColor(float r, float g, float b, float a) {
        this.font.setColor(r, g, b, a);
        super.setColor(r, g, b, a);
    }

    public void setColor(Color color) {
        this.font.setColor(color);
        super.setColor(color);
    }
}
