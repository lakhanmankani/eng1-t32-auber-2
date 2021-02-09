package io.github.eng12020team24.project1.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameOverState implements Screen {
    private final AuberGame game;
    private final TextureRegion outcome;
    private final int outcomeX;
    private final Button exitButton;
    boolean win;

    /**
     * Initialises the Game Over state
     * @param game the overall {@link AuberGame} that the game over is a part of
     * @param win true if the user has won. False otherwise
     */
    public GameOverState(AuberGame game, boolean win) {
        this.game = game;
        TextureAtlas uiAtlas = new TextureAtlas(Gdx.files.internal("../core/assets/UISpritesheet/uispritesheet.atlas"));
        TextureAtlas winAtlas = new TextureAtlas(Gdx.files.internal("../core/assets/UISpritesheet/uispritesheet2.atlas"));
        TextureAtlas loseAtlas = new TextureAtlas(Gdx.files.internal("../core/assets/UISpritesheet/uispritesheet3.atlas"));
        exitButton = new Button(Gdx.graphics.getWidth()/2 - 200, 0, uiAtlas.findRegion("EXIT_BUTTON"));

        this.win = win;
        // 490 is half of the game over image width
        if (win) {
            outcome = new TextureRegion(winAtlas.findRegion("YOU_WIN_IMAGE"));
        } else {
            outcome = new TextureRegion(loseAtlas.findRegion("GAME_OVER_IMAGE"));
        }
        outcomeX = Gdx.graphics.getWidth() / 2 - 490;
    }
    /**
     * Renders screen for this win state. Displays a congratulatory message, renders a button for playing again and a button for exiting
     * @param delta Does not need to be called by the user; is required as screen is implemented and this method override's Screen's render(float delta)
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.6f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(outcome, outcomeX, 25);
        // playAgainButton.draw(game.batch);
        exitButton.draw(game.batch);
        game.batch.end();


    }

    public void checkExitButton(){
        if (exitButton.isClicked() || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){ //exits
            Gdx.app.exit();
        }
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
    }
}
