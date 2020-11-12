package io.github.eng12020team24.project1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class AuberGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	private TextureAtlas textureAtlas;
	public Animation<TextureRegion> auber_walk;
	public Animation<TextureRegion> star_twinkle;
	private float elapsedTime = 0f;
	OrthographicCamera camera;
	TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		textureAtlas = new TextureAtlas(Gdx.files.internal("spritesheet/myspritesheet.atlas"));
		auber_walk = new Animation<TextureRegion>(1f/4f, textureAtlas.findRegions("AUBER_WALK"));
		star_twinkle = new Animation<TextureRegion>(1f/8f, textureAtlas.findRegions("SPACE_BG"));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		tiledMap = new TmxMapLoader().load("maps/Space_Station.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		elapsedTime += Gdx.graphics.getDeltaTime();

		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		/*batch.begin();
		batch.draw(star_twinkle.getKeyFrame(elapsedTime,true),100,100);
		batch.draw(auber_walk.getKeyFrame(elapsedTime,true), 132,100);
		batch.end();*/
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
