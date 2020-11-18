package io.github.eng12020team24.project1.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import io.github.eng12020team24.project1.characters.Auber;
import io.github.eng12020team24.project1.mapclasses.TileType;

public class HealthBar {
    private Animation<TextureRegion> hearts;
    private Animation<TextureRegion> healingParticles;

    public HealthBar(TextureAtlas uiAtlas){
        hearts = new Animation<TextureRegion>(1f/2f,uiAtlas.findRegions("Z_LIFE_HEART"));
        healingParticles = new Animation<TextureRegion>(1f/5f,uiAtlas.findRegions("Z_HEALING PARTICLE"));
    }

    public void render(SpriteBatch batch, float elapsedtime, Auber auber){
        if(auber.getHealth() > 0) {
            for (int heartcount = 1; heartcount <= auber.getHealth(); heartcount++) {
                batch.draw(hearts.getKeyFrame(elapsedtime, true), heartcount * 16, 0);
            }
        } else {
            auber.move(25*TileType.TILE_SIZE-16,6*TileType.TILE_SIZE-16);
        }
        if(auber.isAuberOnHealer()){
            batch.draw(healingParticles.getKeyFrame(elapsedtime,true), Gdx.graphics.getWidth()/2 - 32,Gdx.graphics.getHeight()/2 - 32,64,64);
            auber.auberHeal();
        }
    }
}
