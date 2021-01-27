package io.github.eng12020team24.project1.gamestates;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Input.Keys;

public class LoseState implements Screen{
    private AuberGame game;
    private TextureAtlas uiAtlas;
    //private Button playAgainButton;
    private TextureRegion gameOver;
    private int gameOverX;
    private Button exitButton;
    private ActualGame newGame;
    private MenuState menu;

    /**
     * Initialises the Lose State
     * @param game the overall {@link AuberGame} that this LoseState is a part of
     */
    public LoseState(AuberGame game){
        this.game=game;
        // all buttons and images are centered.
        uiAtlas = new TextureAtlas(Gdx.files.internal("UISpritesheet/uispritesheet.atlas"));
        // playAgainButton = new Button(Gdx.graphics.getWidth()/2 - 440, 128, uiAtlas.findRegion("PLAY_AGAIN_BUTTON")); //440 is half of the play again button width
        exitButton = new Button(Gdx.graphics.getWidth()/2 - 200, 0, uiAtlas.findRegion("EXIT_BUTTON")); // 200 is half of the visible width of the exit button
        gameOver = new TextureRegion(uiAtlas.findRegion("GAME_OVER_IMAGE")); 
        gameOverX = Gdx.graphics.getWidth()/2 - 490; // 490 is half of the game over image width
    }

    /**
     * Renders screen for this lose state. Displays a loss message, a button for playing again and a button for exiting
     * @param delta Does not need to be called by the user; is required as screen is implemented and this method override's Screen's render(float delta)
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(gameOver, gameOverX, 25);
        // playAgainButton.draw(game.batch);
        exitButton.draw(game.batch);
        game.batch.end();
        
        if (exitButton.isClicked() || Gdx.input.isKeyJustPressed(Keys.ESCAPE)) { //exits
            Gdx.app.exit();
        }
        /*
        else if (playAgainButton.isClicked()) { // starts new game
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
