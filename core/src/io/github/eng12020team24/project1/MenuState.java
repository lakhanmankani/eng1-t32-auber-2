package io.github.eng12020team24.project1;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;


public class MenuState implements Screen{
    //private Stage menu;
    AuberGame game;
    Texture playButton;
    Texture exitButton;
    Texture resumeButton;
    ActualGame actualGame = null;

    public MenuState(AuberGame game){
        //menu = new Stage(new ScreenViewport());
        this.game=game;
        playButton=new Texture("PLAY_BUTTON.png");
        exitButton=new Texture("EXIT_BUTTON.png");
        resumeButton = new Texture("RESUME_BUTTON.png");
    }
    @Override
	public void show() {
        //Table table = new Table();
        //menu.addActor(table);
    }
    //@Override
    public static void main(){
    }
    @Override
    public void pause(){
    }
    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.getY()>Gdx.graphics.getHeight()-128 && Gdx.input.getX()< 450){ //exit
            if (Gdx.input.isTouched()){
                System.exit(0);
            }
        } else if (Gdx.input.getY() < Gdx.graphics.getHeight()-128 &&
        Gdx.input.getY() > Gdx.graphics.getHeight() -256 &&
        Gdx.input.getX() < 450){
            if (Gdx.input.isTouched()){
                if (this.actualGame == null) {
                    this.actualGame = new ActualGame(game, this);
                }
                game.setScreen(actualGame);
            }
        }
        game.batch.begin();
        game.batch.draw(exitButton,0,0);
        if (this.actualGame == null) {
            game.batch.draw(playButton,0,128);
        } else {
            game.batch.draw(resumeButton,0,128);
        }
        game.batch.end();
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			System.exit(0); // so you dont have to ALT+F4 the program
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
        //aaaa
    }
}