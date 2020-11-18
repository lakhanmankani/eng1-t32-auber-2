package io.github.eng12020team24.project1.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import io.github.eng12020team24.project1.mapclasses.TiledGameMap;
import io.github.eng12020team24.project1.pathfinding.TileGraph;
import io.github.eng12020team24.project1.characters.Auber;
import io.github.eng12020team24.project1.characters.NeutralNPC;
import io.github.eng12020team24.project1.characters.character_utils;
import io.github.eng12020team24.project1.system.StationSystem;
import io.github.eng12020team24.project1.ui.HealthBar;
import io.github.eng12020team24.project1.ui.Minimap;

import java.util.ArrayList;

public class ActualGame implements Screen{
    final AuberGame game;
    private TextureAtlas textureAtlas;
	private float elapsedTime = 0f;
	OrthographicCamera camera;
	TiledGameMap gameMap;
	Auber auber;
    TextureAtlas uiAtlas;
    Minimap minimap;
    NeutralNPC npc;
    TileGraph graph;
    ShapeRenderer sr;
    MenuState menu;
    HealthBar healthbar;
    ArrayList<StationSystem> stationSystems;
    
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
        npc = new NeutralNPC(graph, 200, 150, textureAtlas);
        sr = new ShapeRenderer();
        healthbar = new HealthBar(uiAtlas);

        stationSystems = new ArrayList<StationSystem>();
        stationSystems.add(new StationSystem(textureAtlas,6,26));//1
        stationSystems.add(new StationSystem(textureAtlas,6,17));//2
        stationSystems.add(new StationSystem(textureAtlas,20,24));//3
        stationSystems.add(new StationSystem(textureAtlas,7,8));//4
        stationSystems.add(new StationSystem(textureAtlas,8,37));//5
        stationSystems.add(new StationSystem(textureAtlas,16,33));//6
        stationSystems.add(new StationSystem(textureAtlas,29,25));//7
        stationSystems.add(new StationSystem(textureAtlas,20,14));//8
        stationSystems.add(new StationSystem(textureAtlas,42,10));//9
        stationSystems.add(new StationSystem(textureAtlas,37,19));//10
        stationSystems.add(new StationSystem(textureAtlas,42,19));//11
        stationSystems.add(new StationSystem(textureAtlas,33,25));//12
        stationSystems.add(new StationSystem(textureAtlas,41,38));//13
        stationSystems.add(new StationSystem(textureAtlas,44,35));//14
        stationSystems.add(new StationSystem(textureAtlas,45,46));//15
        stationSystems.add(new StationSystem(textureAtlas,40,46));//16
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
        sr.begin(ShapeType.Filled);
        npc.render(game.batch, camera, sr, true);
        sr.end();

        healthbar.render(game.batch,elapsedTime,auber);

        for(StationSystem sys : stationSystems){
            sys.render(game.batch, camera);
            if (sys.getHealth() <= 0){
                stationSystems.remove(sys);
            }
        }
        auber.render(game.batch);
        game.batch.end();
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.setScreen(menu); // so you dont have to ALT+F4 the program
		} else if (Gdx.input.isKeyJustPressed(Keys.L)) {
            Vector2 worldClickedPos = character_utils.cameraPositionToWorldPosition(camera, new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()));
            npc.findPath((int) worldClickedPos.x, (int) worldClickedPos.y);
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
