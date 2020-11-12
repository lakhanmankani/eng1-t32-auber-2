package io.github.eng12020team24.project1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;

public class AuberGame extends Game{
	SpriteBatch batch;
	Texture img;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MenuState(this));
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
    
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		elapsedTime += Gdx.graphics.getDeltaTime();

		//camera.update();
		//tiledMapRenderer.setView(camera);
		//tiledMapRenderer.render();
		gameMap.render(camera);//All you need to render the map. Use methods in TileGameMap to interact with map.

		//batch.begin();
		//batch.draw(star_twinkle.getKeyFrame(elapsedTime,true),100,100);
		//batch.draw(auber_walk.getKeyFrame(elapsedTime,true), 132,100);
		//auber.render(batch, Gdx.graphics.getDeltaTime());
		//batch.end();
    
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			System.exit(0); // so you dont have to ALT+F4 the program
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
