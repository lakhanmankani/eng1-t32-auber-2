package io.github.eng12020team24.project1.gamestates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;

public class AuberGame extends Game {
	SpriteBatch batch;
	public MenuState menuState;

	/**
	 * Creates game and drawing batch and then calls {@link MenuState}
	 */
	@Override
	public void create() {
		batch = new SpriteBatch();
		this.menuState = new MenuState(this);
		this.setScreen(this.menuState);
	}

	@Override
	public void render() {
		super.render();
		this.menuState.CheckButtons();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
