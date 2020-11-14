package io.github.eng12020team24.project1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;

public class AuberGame extends Game {
	SpriteBatch batch;
	Texture img;

	@Override
	public void create() {
		batch = new SpriteBatch();
		this.setScreen(new MenuState(this));
	}

	@Override
	public void render() {
		super.render();


		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			System.exit(0); // so you dont have to ALT+F4 the program
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
