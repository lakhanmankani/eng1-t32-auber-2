package io.github.eng12020team24.project1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import io.github.eng12020team24.mapclasses.GameMap;
import io.github.eng12020team24.mapclasses.Minimap;
import io.github.eng12020team24.mapclasses.TiledGameMap;
import io.github.eng12020team24.project1.characters.Auber;;

public class ActualGame implements Screen{
    final AuberGame game;
    private TextureAtlas textureAtlas;
	private float elapsedTime = 0f;
	OrthographicCamera camera;
	GameMap gameMap;
	Auber auber;
    TextureAtlas uiAtlas;
    Minimap minimap;
    
    public ActualGame(AuberGame game){
        this.game = game;
        game.batch = new SpriteBatch();

        textureAtlas = new TextureAtlas(Gdx.files.internal("spritesheet/myspritesheet.atlas"));
        uiAtlas = new TextureAtlas(Gdx.files.internal("UISpritesheet/uispritesheet.atlas"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameMap = new TiledGameMap();
        auber = new Auber(textureAtlas, gameMap);
        minimap = new Minimap(uiAtlas);
    }

    @Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		elapsedTime += Gdx.graphics.getDeltaTime();

        auber.move(Gdx.graphics.getDeltaTime());
        camera.position.set(auber.getPositionForCamera());
		camera.update();
		gameMap.render(camera);
		game.batch.begin();
		if (auber.isAuberOnTeleporter()){
		    minimap.render(game.batch, auber.getXPos(), auber.getYPos());
		    minimap.teleportTo(auber);
		}
		auber.render(game.batch, Gdx.graphics.getDeltaTime());
        game.batch.end();
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
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
    public void dispose() {
    }
    public static void main(){
    }
}
