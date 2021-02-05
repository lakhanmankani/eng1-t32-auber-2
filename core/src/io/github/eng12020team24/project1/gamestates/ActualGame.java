package io.github.eng12020team24.project1.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import io.github.eng12020team24.project1.characters.Auber;
import io.github.eng12020team24.project1.characters.Beam;
import io.github.eng12020team24.project1.characters.Infiltrator;
import io.github.eng12020team24.project1.characters.NeutralNPC;
import io.github.eng12020team24.project1.characters.infiltrators.DisguiseInfiltrator;
import io.github.eng12020team24.project1.characters.infiltrators.InvisibleInfiltrator;
import io.github.eng12020team24.project1.characters.infiltrators.SpeedInfiltrator;
import io.github.eng12020team24.project1.mapclasses.TileType;
import io.github.eng12020team24.project1.mapclasses.TiledGameMap;
import io.github.eng12020team24.project1.pathfinding.TileGraph;
import io.github.eng12020team24.project1.powerup.PowerUp;
import io.github.eng12020team24.project1.saving.LoadSystem;
import io.github.eng12020team24.project1.saving.SaveSystem;
import io.github.eng12020team24.project1.system.StationSystem;
import io.github.eng12020team24.project1.ui.EnemyBar;
import io.github.eng12020team24.project1.ui.HealthBar;
import io.github.eng12020team24.project1.ui.Minimap;
import io.github.eng12020team24.project1.ui.SystemBar;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ActualGame implements Screen {
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
    HealthBar healthbar;
    SystemBar systemBar;
    EnemyBar enemyBar;
    ArrayList<StationSystem> stationSystems;
    ArrayList<Infiltrator> infiltrators;
    ArrayList<Infiltrator> infiltratorsToAdd;
    ArrayList<Beam> beamgun;
    int difficulty;
    TextureAtlas powerUpAtlas;
    ArrayList<PowerUp> unusedPowerUps;
    public ArrayList<PowerUp> currentPowerUps;

    public ActualGame(AuberGame game, int difficulty, MenuState menu, LoadSystem load) {
        this.game = game;
        game.batch = new SpriteBatch();

        this.menu = menu;
        textureAtlas = new TextureAtlas(Gdx.files.internal("spritesheet/myspritesheet.atlas"));
        uiAtlas = new TextureAtlas(Gdx.files.internal("UISpritesheet/uispritesheet.atlas"));
        powerUpAtlas = new TextureAtlas(Gdx.files.internal("powerUpsSpriteSheet/PowerUpsSprites.atlas"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameMap = new TiledGameMap();
        auber = new Auber(textureAtlas, difficulty, gameMap);
        minimap = new Minimap(uiAtlas);
        graph = new TileGraph(gameMap);
        healthbar = new HealthBar(uiAtlas);
        systemBar = new SystemBar(textureAtlas, 1);
        enemyBar = new EnemyBar(textureAtlas, 2);
        beamgun = new ArrayList<Beam>();

        //Set Auber position and health if loading a save
        if(load != null) {
            auber.setX(load.getAuberDetails().getInt("x"));
            auber.setY(load.getAuberDetails().getInt("y"));
            auber.setAuberHealth(load.getAuberDetails().getInt("health"));
        }

        // Add systems
        stationSystems = new ArrayList<StationSystem>();
        if(load != null) {
            for(ArrayList system : load.generateSystemsList())
            {
                stationSystems.add(new StationSystem(textureAtlas, (int) system.get(0) / TileType.TILE_SIZE, (int) system.get(1) / TileType.TILE_SIZE, (boolean) system.get(2), (int) system.get(3)));
            }
        } else {
            stationSystems.add(new StationSystem(textureAtlas, 6, 26));// 1
            stationSystems.add(new StationSystem(textureAtlas, 6, 17));// 2
            stationSystems.add(new StationSystem(textureAtlas, 20, 24));// 3
            stationSystems.add(new StationSystem(textureAtlas, 7, 8));// 4
            stationSystems.add(new StationSystem(textureAtlas, 8, 37));// 5
            stationSystems.add(new StationSystem(textureAtlas, 16, 33));// 6
            stationSystems.add(new StationSystem(textureAtlas, 29, 25));// 7
            stationSystems.add(new StationSystem(textureAtlas, 20, 14));// 8
            stationSystems.add(new StationSystem(textureAtlas, 42, 10));// 9
            stationSystems.add(new StationSystem(textureAtlas, 37, 19));// 10
            stationSystems.add(new StationSystem(textureAtlas, 42, 19));// 11
            stationSystems.add(new StationSystem(textureAtlas, 33, 25));// 12
            stationSystems.add(new StationSystem(textureAtlas, 41, 38));// 13
            stationSystems.add(new StationSystem(textureAtlas, 44, 35));// 14
            stationSystems.add(new StationSystem(textureAtlas, 45, 46));// 15
            stationSystems.add(new StationSystem(textureAtlas, 40, 46));// 16
        }

        // Set game difficulty
        if(load != null) {
            this.difficulty = load.getDifficulty();
        } else {
            this.difficulty = difficulty;
        }

        // Add neutral NPCs
        neutralNpcs = new ArrayList<NeutralNPC>();
        if(load != null) {
            for (ArrayList npc : load.generateNpcList() ) {
                neutralNpcs.add(new NeutralNPC(graph, graph.getTileFromCoordinates((Integer) npc.get(0), (Integer) npc.get(1)), textureAtlas));
            }
        } else {
            neutralNpcs.add(new NeutralNPC(graph, graph.getTileFromCoordinates(208, 144), textureAtlas));
            neutralNpcs.add(new NeutralNPC(graph, graph.getTileFromCoordinates(1360, 1360), textureAtlas));
            neutralNpcs.add(new NeutralNPC(graph, graph.getTileFromCoordinates(720, 1296), textureAtlas));
            neutralNpcs.add(new NeutralNPC(graph, graph.getTileFromCoordinates(1264, 272), textureAtlas));

        }

        // Power ups
        currentPowerUps = new ArrayList<>();
        unusedPowerUps = new ArrayList<>();
        if(load != null){
            for(ArrayList powerup : load.generateCurrentPowerupsList()){
                BigDecimal decimal = (BigDecimal) powerup.get(3);
                float timer = decimal.floatValue();
                currentPowerUps.add(new PowerUp((String) powerup.get(0), (int) powerup.get(1) / TileType.TILE_SIZE, (int) powerup.get(2) / TileType.TILE_SIZE, timer));
            }

            for(ArrayList powerup : load.generateUnusedPowerupsList()){
                unusedPowerUps.add(new PowerUp((String) powerup.get(0), (int) powerup.get(1) / TileType.TILE_SIZE, (int) powerup.get(2) / TileType.TILE_SIZE));
            }

        }else {
            unusedPowerUps.add(new PowerUp("Shield", 44, 39));
            unusedPowerUps.add(new PowerUp("SpeedUp", 29, 21));
            unusedPowerUps.add(new PowerUp("MultiBeam", 22, 35));
            unusedPowerUps.add(new PowerUp("InfiltratorFreeze", 12, 7));
            unusedPowerUps.add(new PowerUp("All", 34, 25));
        }

        // Add hostile NPCs
        infiltrators = new ArrayList<Infiltrator>();
        infiltratorsToAdd = new ArrayList<Infiltrator>();

        if(load != null) {
            for (ArrayList infiltrator : load.generateInfiltratorToAddList() ) {
                switch((String) infiltrator.get(2)){
                    case "SpeedInfiltrator":
                        infiltratorsToAdd.add(new SpeedInfiltrator(difficulty, graph,
                                graph.getTileFromCoordinates((int) infiltrator.get(0), (int) infiltrator.get(1)), textureAtlas));
                        break;
                    case "InvisibleInfiltrator":
                        infiltratorsToAdd.add(new InvisibleInfiltrator(difficulty, graph,
                                graph.getTileFromCoordinates((int) infiltrator.get(0), (int) infiltrator.get(1)), textureAtlas));
                        break;
                    case "DisguiseInfiltrator":
                        infiltratorsToAdd.add(new DisguiseInfiltrator(difficulty, graph,
                                graph.getTileFromCoordinates((int) infiltrator.get(0), (int) infiltrator.get(1)), textureAtlas));
                        break;
                }
            }

            for (ArrayList infiltrator : load.generateInfiltratorList() ) {
                switch((String) infiltrator.get(2)){
                    case "SpeedInfiltrator":
                        infiltrators.add(new SpeedInfiltrator(difficulty, graph,
                                graph.getTileFromCoordinates((int) infiltrator.get(0), (int) infiltrator.get(1)), textureAtlas));
                        break;
                    case "InvisibleInfiltrator":
                        infiltrators.add(new InvisibleInfiltrator(difficulty, graph,
                                graph.getTileFromCoordinates((int) infiltrator.get(0), (int) infiltrator.get(1)), textureAtlas));
                        break;
                    case "DisguiseInfiltrator":
                        infiltrators.add(new DisguiseInfiltrator(difficulty, graph,
                                graph.getTileFromCoordinates((int) infiltrator.get(0), (int) infiltrator.get(1)), textureAtlas));
                        break;
                }
            }
        } else {
            infiltratorsToAdd.add(new SpeedInfiltrator(difficulty, graph,
                    graph.getTileFromCoordinates(43 * TileType.TILE_SIZE, 47 * TileType.TILE_SIZE), textureAtlas));
            infiltratorsToAdd.add(new SpeedInfiltrator(difficulty, graph,
                    graph.getTileFromCoordinates(9 * TileType.TILE_SIZE, 39 * TileType.TILE_SIZE), textureAtlas));
            infiltratorsToAdd.add(new InvisibleInfiltrator(difficulty, graph,
                    graph.getTileFromCoordinates(23 * TileType.TILE_SIZE, 47 * TileType.TILE_SIZE), textureAtlas));
            infiltratorsToAdd.add(new SpeedInfiltrator(difficulty, graph,
                    graph.getTileFromCoordinates(9 * TileType.TILE_SIZE, 25 * TileType.TILE_SIZE), textureAtlas));
            infiltratorsToAdd.add(new DisguiseInfiltrator(difficulty, graph,
                    graph.getTileFromCoordinates(43 * TileType.TILE_SIZE, 38 * TileType.TILE_SIZE), textureAtlas));
            infiltratorsToAdd.add(new SpeedInfiltrator(difficulty, graph,
                    graph.getTileFromCoordinates(34 * TileType.TILE_SIZE, 25 * TileType.TILE_SIZE), textureAtlas));
            infiltratorsToAdd.add(new InvisibleInfiltrator(difficulty, graph,
                    graph.getTileFromCoordinates(43 * TileType.TILE_SIZE, 47 * TileType.TILE_SIZE), textureAtlas));
            infiltratorsToAdd.add(new DisguiseInfiltrator(difficulty, graph,
                    graph.getTileFromCoordinates(9 * TileType.TILE_SIZE, 39 * TileType.TILE_SIZE), textureAtlas));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsedTime += Gdx.graphics.getDeltaTime();

        if (isCurrentlyUsingPowerUp("SpeedUp")) {
            auber.move(Gdx.graphics.getDeltaTime(), infiltrators, 2.5f);
        } else {
            auber.move(Gdx.graphics.getDeltaTime(), infiltrators);
        }
        if (isCurrentlyUsingPowerUp("Shield")) {
            if (auber.getHealth() != 10) {
                System.out.println("Man down!");
            }
            auber.fullHeal();
        }
        camera.position.set(auber.getPositionForCamera());
        camera.update();
        gameMap.render(camera);

        // Check if game is over
        if (stationSystems.size() <= 1) {
            game.setScreen(new GameOverState(game, false));
        } else if (infiltratorsToAdd.size() == 0 && infiltrators.size() == 0) {
            game.setScreen(new GameOverState(game, true));
        }

        game.batch.begin();

        // Display teleport menu
        if (auber.isAuberOnTeleporter()) {
            minimap.render(game.batch, auber.getXPos(), auber.getYPos());
            minimap.teleportTo(auber);
        }

        // Display status bars
        healthbar.render(game.batch, elapsedTime, auber);
        systemBar.render(game.batch, stationSystems.size());
        enemyBar.render(game.batch, infiltratorsToAdd.size() + infiltrators.size());

        // Remove destroyed systems
        ArrayList<StationSystem> systemsToRemove = new ArrayList<StationSystem>();
        for (StationSystem sys : stationSystems) {
            sys.render(game.batch, camera);
            if (sys.getHealth() <= 0) {
                systemsToRemove.add(sys);
            }
            sys.setFunctioning(true);
        }
        for (StationSystem sys : systemsToRemove) {
            stationSystems.remove(sys);
        }

        // Render power up tiles
        ArrayList<PowerUp> powerUpsToRemove = new ArrayList<>();
        for (PowerUp powerUp : unusedPowerUps) {
            powerUp.render(game.batch, camera, powerUpAtlas);
            if (powerUp.auberOnPowerUpTile(auber)) {
                // Move power up from unused to current
                powerUp.startUsing();
                currentPowerUps.add(powerUp);
                powerUpsToRemove.add(powerUp);
                System.out.println("Pickup "+powerUp.name);
            }
        }
        for (PowerUp powerUp : powerUpsToRemove) {
            unusedPowerUps.remove(powerUp);
        }
        powerUpsToRemove = new ArrayList<>();
        for (PowerUp powerUp : currentPowerUps) {
            powerUp.render(game.batch, camera, powerUpAtlas);
            if (powerUp.finishedUsing()) {
                System.out.println("Remove "+powerUp.name);
                powerUpsToRemove.add(powerUp);
            }
        }
        for (PowerUp powerUp : powerUpsToRemove) {
            currentPowerUps.remove(powerUp);
        }

        // Move neutral NPCs
        for (NeutralNPC npc : neutralNpcs) {
            npc.move();
            npc.render(game.batch, camera);
        }

        for (Infiltrator infiltrator : infiltrators) {
            if (!isCurrentlyUsingPowerUp("InfiltratorFreeze")) {
                infiltrator.runAI(auber, stationSystems);
            }
            infiltrator.runAI(auber, stationSystems, 0);

            infiltrator.render(game.batch, camera);
        }
        if (infiltrators.size() < 2 && infiltratorsToAdd.size() > 0) {
            infiltrators.add(infiltratorsToAdd.get(0));
            infiltratorsToAdd.remove(0);
        }
        auber.render(game.batch);

        // Display beams
        if (Gdx.input.isKeyPressed(Keys.SPACE) && beamgun.size() < 1) {
            beamgun.add(new Beam(auber, difficulty, textureAtlas, 0));
            if (isCurrentlyUsingPowerUp("MultiBeam")) {
                beamgun.add(new Beam(auber, difficulty, textureAtlas, 1));
                beamgun.add(new Beam(auber, difficulty, textureAtlas, -1));
            }
        }

        // Remove beam when it collides
        ArrayList<Beam> beamsToRemove = new ArrayList<Beam>();
        ArrayList<Infiltrator> infiltratorsToRemove = new ArrayList<Infiltrator>();
        for (Beam beam : beamgun) {
            beam.render(game.batch, camera);
            beam.move();

            if (gameMap.doesRectCollideWithMap(beam.getX() + 8, beam.getY() + 8, 16, 16)) {
                // +8 as the beam orb sprite is 16x16 surrounded by an 8-wide border
                beamsToRemove.add(beam);
            } else {
                for (Infiltrator infiltrator : infiltrators) {
                    if (infiltrator.doesRectCollideWithInfiltrator(beam.getX() + 8, beam.getY() + 8, 16, 16)) {
                        infiltratorsToRemove.add(infiltrator);
                    }
                }
            }
        }

        for (Beam b : beamsToRemove) {
            beamgun.remove(b);
        }

        for (Infiltrator i : infiltratorsToRemove) {
            infiltrators.remove(i);
        }

        game.batch.end();
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            game.setScreen(menu);
        }

    }

    public void saveGame() throws IOException {
        SaveSystem save = new SaveSystem(infiltrators, neutralNpcs, stationSystems, difficulty, auber, infiltratorsToAdd, currentPowerUps, unusedPowerUps);
        save.writeSaveToFile();
        return;
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

    private boolean isCurrentlyUsingPowerUp(String name) {
        for (PowerUp powerUp : currentPowerUps) {
            if (powerUp.name.equals(name) || powerUp.name.equals("All")) {
                return true;
            }
        }
        return false;
    }
}
