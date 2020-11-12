package io.github.eng12020team24.project1;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MenuState implements Screen{
    //private Stage menu;
    AuberGame game;
    Texture playButton;
    Texture exitButton;

    public MenuState(AuberGame game){
        //menu = new Stage(new ScreenViewport());
        this.game=game;
        playButton=new Texture("PLAY_BUTTON.png");
        exitButton=new Texture("EXIT_BUTTON.png");
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
        game.batch.begin();
        game.batch.draw(exitButton,0,0);
        game.batch.draw(playButton,0,128);
		game.batch.end();
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