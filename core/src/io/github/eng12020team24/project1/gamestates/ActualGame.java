package io.github.eng12020team24.project1.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.eng12020team24.project1.mapclasses.GameMap;
import io.github.eng12020team24.project1.mapclasses.TiledGameMap;
import io.github.eng12020team24.project1.pathfinding.TileGraph;
import io.github.eng12020team24.project1.characters.Auber;;

public class ActualGame implements Screen {
    final AuberGame game;
    private TextureAtlas textureAtlas;
	public Animation<TextureRegion> auber_walk;
	public Animation<TextureRegion> star_twinkle;
	private float elapsedTime = 0f;
	OrthographicCamera camera;
	TiledGameMap gameMap;
    Auber auber;
    TileGraph graph;
    
    public ActualGame(AuberGame game){
        this.game = game;
        game.batch = new SpriteBatch();

        textureAtlas = new TextureAtlas(Gdx.files.internal("spritesheet/myspritesheet.atlas"));
        auber_walk = new Animation<TextureRegion>(1f / 4f, textureAtlas.findRegions("AUBER_WALK"));
        star_twinkle = new Animation<TextureRegion>(1f / 8f, textureAtlas.findRegions("SPACE_BG"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameMap = new TiledGameMap();
        auber = new Auber(textureAtlas, gameMap);
        graph = new TileGraph(gameMap);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsedTime += Gdx.graphics.getDeltaTime();

        auber.move(Gdx.graphics.getDeltaTime());

        camera.position.set(auber.getPositionForCamera());
		camera.update();
		gameMap.render(camera);
		game.batch.begin();
		auber.render(game.batch);
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

    public static void main() {
    }
}
