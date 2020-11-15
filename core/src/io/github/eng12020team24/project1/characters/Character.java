package io.github.eng12020team24.project1.characters;

import com.badlogic.gdx.math.Vector3;
import io.github.eng12020team24.mapclasses.GameMap;

public class Character {
    protected int xPos;
    protected int yPos;
    public GameMap map;

    public int getXPos(){return xPos;}
    public int getYPos(){return yPos;}

    public void setX(int newX) {
        xPos = newX;
    }

    public void setY(int newY) {
        yPos = newY;
    }

    public void move(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public Vector3 getPositionForCamera() {
        return new Vector3(xPos, yPos, 0);
    }
}
