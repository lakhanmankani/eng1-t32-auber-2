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
        if (this.actualGame == null) { // draws either the play or resume button depending on if game already exists
            playButton.draw(game.batch);
            loadButton.draw(game.batch);
        } else {
            resumeButton.draw(game.batch);
            saveButton.draw(game.batch);
        }
        game.batch.end();

        if (exitButton.isClicked()) {
            //exits
            Gdx.app.exit();
        } else if (playButton.isClicked() && this.actualGame == null){ // starts new game
            this.actualGame = new ActualGame(game, 0, this);
            game.setScreen(actualGame);
        } else if (resumeButton.isClicked() && this.actualGame != null){ // resumes old game
            game.setScreen(actualGame);
        } else if (saveButton.isClicked() && clicked == false && this.actualGame != null) {
            clicked = true;
            try {
                actualGame.saveGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(loadButton.isClicked()  && this.actualGame == null)
        {
            try {
                LoadSystem load = new LoadSystem("save.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Load");
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