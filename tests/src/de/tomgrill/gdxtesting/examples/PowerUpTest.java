
package de.tomgrill.gdxtesting.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import de.tomgrill.gdxtesting.GdxTestRunner;
import io.github.eng12020team24.project1.characters.Auber;
import io.github.eng12020team24.project1.mapclasses.TileType;
import io.github.eng12020team24.project1.powerup.PowerUp;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class PowerUpTest {

    TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("../core/assets/spritesheet/myspritesheet.atlas"));
    //TiledGameMap gameMap = new TiledGameMap();
    //TextureAtlas powerUpAtlas = new TextureAtlas(Gdx.files.internal("../core/assets/powerUpsSpriteSheet/PowerUpsSprites.atlas"));
    Auber auber = new Auber(textureAtlas, 0, null);
//    AuberGame game;
//    MenuState menu;
//    LoadSystem load;
//    ActualGame testActualGame = new ActualGame(game, 0, menu, load);

    @Test
    @DisplayName("Auber should be able to pick up power ups")
    public void testValidPickUp() {

        auber.setX(4*TileType.TILE_SIZE);
        auber.setY(3*TileType.TILE_SIZE);

        PowerUp powerUp = new PowerUp("MultiBeam", 4, 4);
        assertFalse(powerUp.auberOnPowerUpTile(auber));
    }

    @Test
    @DisplayName("Auber should be able to use power ups")
    public void testUsePowerUp() {
    }

}
