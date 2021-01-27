package io.github.eng12020team24.project1.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameOverState implements Screen {
    private AuberGame game;
    private TextureAtlas uiAtlas;
    // private Button playAgainButton;
    private TextureRegion outcome;
    private int outcomeX;
    private Button exitButton;
    private ActualGame newGame;
    private MenuState menu;
    boolean win;

    /**
     * Initialises the Game Over state
     * @param game the overall {@link AuberGame} that the game over is a part of
     * @param win true if the user has won. False otherwise
     */
    public GameOverState(AuberGame game, boolean win) {
        this.game = game;
        uiAtlas = new TextureAtlas(Gdx.files.internal("UISpritesheet/uispritesheet.atlas"));
        // playAgainButton = new Button(Gdx.graphics.getWidth()/2 - 440, 128, uiAtlas.findRegion("PLAY_AGAIN_BUTTON"));
        exitButton = new Button(Gdx.graphics.getWidth()/2 - 200, 0, uiAtlas.findRegion("EXIT_BUTTON"));

        this.win = win;
        // 490 is half of the game over image width
        if (win) {
            outcome = new TextureRegion(uiAtlas.findRegion("YOU_WIN_IMAGE"));
        } else {
            outcome = new TextureRegion(uiAtlas.findRegion("GAME_OVER_IMAGE"));
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

        if (exitButton.isClicked() || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){ //exits
            Gdx.app.exit();
        }
        /*
        else if (playAgainButton.isClicked()){ // starts new game
            menu = new MenuState(game);
            this.newGame = new ActualGame(game, menu);
            game.setScreen(newGame);
        }
        */
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