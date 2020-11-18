package io.github.eng12020team24.project1.gamestates;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import io.github.eng12020team24.project1.mapclasses.TiledGameMap;
import io.github.eng12020team24.project1.pathfinding.TileGraph;
import io.github.eng12020team24.project1.characters.Auber;
import io.github.eng12020team24.project1.characters.NeutralNPC;
import io.github.eng12020team24.project1.characters.character_utils;
import io.github.eng12020team24.project1.ui.Healthbar;
import io.github.eng12020team24.project1.ui.Minimap;

public class ActualGame implements Screen{
    final AuberGame game;
    private TextureAtlas textureAtlas;
	private float elapsedTime = 0f;
	OrthographicCamera camera;
	TiledGameMap gameMap;
	Auber auber;
    TextureAtlas uiAtlas;
    Minimap minimap;
    ArrayList<NeutralNPC> neutralNpcs;
    TileGraph graph;
    MenuState menu;
    Healthbar healthbar;
    
    public ActualGame(AuberGame game, MenuState menu){
        this.game = game;
        game.batch = new SpriteBatch();

        this.menu = menu;
        textureAtlas = new TextureAtlas(Gdx.files.internal("spritesheet/myspritesheet.atlas"));
        uiAtlas = new TextureAtlas(Gdx.files.internal("UISpritesheet/uispritesheet.atlas"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameMap = new TiledGameMap();
        auber = new Auber(textureAtlas, gameMap);
        minimap = new Minimap(uiAtlas);
        graph = new TileGraph(gameMap);
        healthbar = new Healthbar(uiAtlas);
        neutralNpcs = new ArrayList<NeutralNPC>();
        neutralNpcs.add(new NeutralNPC(graph, graph.getTileFromCoordinates(208, 144), textureAtlas));
        neutralNpcs.add(new NeutralNPC(graph, graph.getTileFromCoordinates(1360, 1360), textureAtlas));
        neutralNpcs.add(new NeutralNPC(graph, graph.getTileFromCoordinates(720, 1296), textureAtlas));
        neutralNpcs.add(new NeutralNPC(graph, graph.getTileFromCoordinates(1264, 272), textureAtlas));
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


		if (auber.isAuberOnTeleporter()){
		    minimap.render(game.batch, auber.getXPos(), auber.getYPos());
		    minimap.teleportTo(auber);
		}
        auber.render(game.batch);
        healthbar.render(game.batch,elapsedTime,auber);
        for (NeutralNPC npc : neutralNpcs) {
            npc.move();
            npc.render(game.batch, camera);
        }
        game.batch.end();
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.setScreen(menu); // so you dont have to ALT+F4 the program
		}

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
