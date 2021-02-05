package de.tomgrill.gdxtesting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import io.github.eng12020team24.project1.characters.Auber;
import io.github.eng12020team24.project1.mapclasses.TileType;
import io.github.eng12020team24.project1.mapclasses.TiledGameMap;
import io.github.eng12020team24.project1.ui.Minimap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class AuberTeleportTest {

    private Auber setupAuber() {
        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.local("../core/assets/spritesheet/myspritesheet.atlas"));
        return new Auber(textureAtlas, 0, null);
    }

    @Test
    public void testAuberOnTeleporter() {
        Auber auber = new Auber();
        Minimap minimap = new Minimap();

        // Test Auber not on teleporter
        auber.setX(100 * TileType.TILE_SIZE);
        auber.setY(100 * TileType.TILE_SIZE);
        Assert.assertFalse(minimap.isAuberOnTeleporter(auber));

        // Test Auber on teleporter
        auber.setX(26 * TileType.TILE_SIZE);
        auber.setY(47 * TileType.TILE_SIZE);
        Assert.assertTrue(minimap.isAuberOnTeleporter(auber));
    }

    @Test
    public void testAuberTeleports() {
        Auber auber = setupAuber();

        Minimap minimap = new Minimap();

        minimap.teleport(auber, 0);

        Assert.assertEquals(auber.getXPos(), 28*TileType.TILE_SIZE - 16);
        Assert.assertEquals(auber.getYPos(), 6*TileType.TILE_SIZE - 16);
    }


}
