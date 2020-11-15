package io.github.eng12020team24.project1;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class MenuState implements Screen{
    //private Stage menu;
    AuberGame game;
    TextureRegion playButton;
    TextureRegion exitButton;
    TextureAtlas uiAtlas;

    public MenuState(AuberGame game){
        //menu = new Stage(new ScreenViewport());
        this.game=game;
        uiAtlas = new TextureAtlas(Gdx.files.internal("UISpritesheet/uispritesheet.atlas"));
        playButton=new TextureRegion(uiAtlas.findRegion("PLAY_BUTTON"));
        exitButton=new TextureRegion(uiAtlas.findRegion("EXIT_BUTTON"));
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
                this.dispose();
                game.setScreen(new ActualGame(game));
            }
        }
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