package io.github.eng12020team24.project1.gamestates;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import io.github.eng12020team24.project1.saving.LoadSystem;

import java.io.IOException;

public class MenuState implements Screen {
    private AuberGame game;
    private ActualGame actualGame = null;
    private TextureAtlas uiAtlas;
    private Button saveButton;
    private Button playButton;
    private Button exitButton;
    private Button resumeButton;
    private Button loadButton;
    private Boolean clicked;
    private Boolean isShowingDifficulty = false;
    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;
    private Button demoButton;

    /**
     * Initialises the Menu state
     * @param game the overall {@link AuberGame} that this MenuState is a part of
     */
    public MenuState(AuberGame game){
        this.game=game;
        uiAtlas = new TextureAtlas(Gdx.files.internal("UISpritesheet/uispritesheet.atlas"));
        playButton = new Button(0, 128, uiAtlas.findRegion("PLAY_BUTTON"));
        saveButton = new Button(0, 256, uiAtlas.findRegion("SAVE_BUTTON"));
        loadButton = new Button(0, 256, uiAtlas.findRegion("LOAD_BUTTON"));
        resumeButton = new Button(0, 128, uiAtlas.findRegion("RESUME_BUTTON"));
        exitButton = new Button(0, 0, uiAtlas.findRegion("EXIT_BUTTON"));
        demoButton = new Button(0, 384, uiAtlas.findRegion("RESUME_BUTTON"));

        // TODO: Replace with correct images
        hardButton = new Button(450, 0, uiAtlas.findRegion("PLAY_BUTTON"));
        mediumButton = new Button(450, 128, uiAtlas.findRegion("PLAY_BUTTON"));
        easyButton = new Button(450, 256, uiAtlas.findRegion("PLAY_BUTTON"));
    }

    @Override
	public void show() {
        clicked = false;
    }

    @Override
    public void pause(){
    }

    /**
     * Renders this menu screen - displays play/resume buttons depending on if the game has begun or not
     * @param delta - necessary for render method however is unused
     */
    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        

        game.batch.begin();
        exitButton.draw(game.batch);
        if (this.actualGame == null) {
            // Draw either the play or resume button depending on if game already exists
            if (isShowingDifficulty) {
                easyButton.draw(game.batch);
                mediumButton.draw(game.batch);
                hardButton.draw(game.batch);
            } else {
                playButton.draw(game.batch);
                loadButton.draw(game.batch);
                demoButton.draw(game.batch);
            }
        } else {
            resumeButton.draw(game.batch);
            saveButton.draw(game.batch);
        }
        game.batch.end();

        if (exitButton.isClicked()) {
            // Exit
            Gdx.app.exit();
        } else if (playButton.isClicked() && this.actualGame == null) {
            // Start new game
            isShowingDifficulty = true;
        } else if (easyButton.isClicked() && this.actualGame == null) {
            // Start easy game
            System.out.println("Easy");
            isShowingDifficulty = false;
            this.actualGame = new ActualGame(game, 0, this, null, false);
            game.setScreen(actualGame);
        } else if (mediumButton.isClicked() && this.actualGame == null) {
            // Start medium game
            System.out.println("Medium");
            isShowingDifficulty = false;
            this.actualGame = new ActualGame(game, 1, this, null, false);
            game.setScreen(actualGame);
        } else if (hardButton.isClicked() && this.actualGame == null) {
            // Start hard game
            System.out.println("Hard");
            isShowingDifficulty = false;
            this.actualGame = new ActualGame(game, 2, this, null, false);
            game.setScreen(actualGame);
        }
        else if (resumeButton.isClicked() && this.actualGame != null){
            // Resume old game
            game.setScreen(actualGame);
        } else if (saveButton.isClicked() && !clicked && this.actualGame != null) {
            // Save game
            clicked = true;
            try {
                actualGame.saveGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(loadButton.isClicked()  && this.actualGame == null) {
            // Load game save and start
            try {
                LoadSystem load = new LoadSystem("save.txt");
                this.actualGame = new ActualGame(game, load.getDifficulty(), this, load, false);
                game.setScreen(actualGame);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(demoButton.isClicked() && this.actualGame == null) {
            this.actualGame = new ActualGame(game, 0, this, null, true);
            game.setScreen(actualGame);
        }

        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			Gdx.app.exit(); // so you dont have to ALT+F4 the program
		}
    }

    @Override
    public void hide(){
    }

    @Override
    public void dispose(){
    }

    @Override
    public void resume(){
    }

    @Override
    public void resize(int a, int b){
    }
}