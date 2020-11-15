package io.github.eng12020team24.project1.gamestates;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import io.github.eng12020team24.project1.gamestates.ActualGame;
import io.github.eng12020team24.project1.gamestates.AuberGame;

public class MenuState implements Screen {
    // private Stage menu;
    AuberGame game;
    TextureRegion playButton;
    TextureRegion exitButton;
    TextureAtlas uiAtlas;
    SpriteBatch batch;

    public MenuState(AuberGame game) {
        this.game = game;
        uiAtlas = new TextureAtlas(Gdx.files.internal("UISpritesheet/uispritesheet.atlas"));
        playButton = new TextureRegion(uiAtlas.findRegion("PLAY_BUTTON"));
        exitButton = new TextureRegion(uiAtlas.findRegion("EXIT_BUTTON"));
        batch = new SpriteBatch();
    }

    @Override
    public void show() {
        // Table table = new Table();
        // menu.addActor(table);
    }

    // @Override
    public static void main() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.getY() > Gdx.graphics.getHeight() - 128 && Gdx.input.getX() < 450) { // exit
            if (Gdx.input.isTouched()) {
                System.exit(0);
            }
        } else if (Gdx.input.getY() < Gdx.graphics.getHeight() - 128
                && Gdx.input.getY() > Gdx.graphics.getHeight() - 256 && Gdx.input.getX() < 450) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new ActualGame(game));
            }
        }
        batch.begin();
        batch.draw(exitButton, 0, 0);
        batch.draw(playButton, 0, 128);
        batch.end();
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void resize(int a, int b) {
        // aaaa
    }
}