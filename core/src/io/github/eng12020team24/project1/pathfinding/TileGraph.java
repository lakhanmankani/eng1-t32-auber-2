package io.github.eng12020team24.project1.pathfinding;

import java.util.ArrayList;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

import io.github.eng12020team24.project1.mapclasses.TileType;
import io.github.eng12020team24.project1.mapclasses.TiledGameMap;

// This code, and all code in this package, is based on code here https://happycoding.io/tutorials/libgdx/pathfinding, which is released under the terms of the CC BY 4.0 license.
public class TileGraph implements IndexedGraph<Tile> {

    TileHeuristic heuristic = new TileHeuristic();
    Array<Tile> tiles = new Array<>();
    Array<TileConnection> connections = new Array<>();
    private Tile[][] flatTileMap;
    private ArrayList<MapRegion> regions;


    ObjectMap<Tile, Array<Connection<Tile>>> connectionMap = new ObjectMap<>();

    private int lastNodeIndex = 0;

    public void addTile(Tile tile) {
        tile.setIndex(lastNodeIndex);
        lastNodeIndex++;

        tiles.add(tile);
    }

    public void connectTiles(Tile fromTile, Tile toTile) {
        TileConnection newConnection = new TileConnection(fromTile, toTile);
        if (!connectionMap.containsKey(fromTile)) {
            connectionMap.put(fromTile, new Array<Connection<Tile>>());
        }
        connectionMap.get(fromTile).add(newConnection);
        connections.add(newConnection);
    }

    public GraphPath<Tile> findPath(Tile startTile, Tile goalTile) {
        GraphPath<Tile> tilePath = new DefaultGraphPath<>();
        new IndexedAStarPathFinder<>(this).searchNodePath(startTile, goalTile, heuristic, tilePath);
        return tilePath;
    }

    public GraphPath<Tile> findPath(int xPos, int yPos, int goalXPos, int goalYPos) {
        GraphPath<Tile> tilePath = new DefaultGraphPath<>();
        new IndexedAStarPathFinder<>(this).searchNodePath(this.getTileFromCoordinates(xPos, yPos), this.getTileFromCoordinates(goalXPos, goalYPos), heuristic, tilePath);
        return tilePath;
    }

    public Tile getTileFromCoordinates(int xPos, int yPos) {
        int tileSize = flatTileMap[0][0].getType().TILE_SIZE;
        return flatTileMap[Math.floorDiv(xPos, tileSize)][Math.floorDiv(yPos, tileSize)];
    }


    @Override
    public int getIndex(Tile node) {
        // TODO Auto-generated method stub
        return node.getIndex();
    }

    @Override
    public int getNodeCount() {
        // TODO Auto-generated method stub
        return lastNodeIndex;
    }

    @Override
    public Array<Connection<Tile>> getConnections(Tile fromNode) {
        // TODO Auto-generated method stub
        if (connectionMap.containsKey(fromNode)) {
            return connectionMap.get(fromNode);
        }

        return new Array<>(0);
    }

    public MapRegion getRegionForTile(Tile tile) {
        for (MapRegion r : regions) {
            if (r.contains(tile)) {
                return r;
            }
        }
        return null;
    }

    public TileGraph(TiledGameMap map) {
        boolean allowCornerCutting = false;
        flatTileMap = new Tile[map.getWidth()][map.getHeight()];

        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                // We need to know the TileType of the most visible tile, i.e. the highest one.
                // We do this by iterating down through the layers and stopping when we
                // encounter a non-null tile type.
                int currentLayer = map.getLayers() - 1;
                TileType currentTileType = null;
                while (currentTileType == null && currentLayer >= 0) {
                    // break if we found the non-null tile type or if we've gone to a negative
                    // layer, which indicates that there is no tile here.
                    currentTileType = map.getTileTypeByCoordinate(currentLayer, x, y);
                    currentLayer--;
                }
                Tile newTile = new Tile(x, y, currentTileType);
                flatTileMap[x][y] = newTile;
                this.addTile(newTile);
            }
        }
        // This creates all the tiles we need.

        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                Tile currentTile = flatTileMap[x][y];
                for (int xDiff = -1; xDiff <= 1; xDiff++) {
                    for (int yDiff = -1; yDiff <= 1; yDiff++) {
                        if (x + xDiff < 0 || x + xDiff >= map.getWidth()) {
                            continue;
                        }
                        if (y + yDiff < 0 || y + yDiff >= map.getHeight()) {
                            continue;
                        }
                        // This ensures we do not try and access an invalid array index
                        if (xDiff == 0 && yDiff == 0) {
                            continue;
                        }
                        // if both xDiff and yDiff are zero then we are referring to this node.
                        if ((xDiff != 0 && yDiff == 0) || (xDiff == 0 && yDiff != 0)) {
                            // if only one of them is zero, i.e. it is orthogonal to the current tile
                            Tile toTile = flatTileMap[x + xDiff][y + yDiff];
                            if (toTile.getType().isCollidable()) {
                                continue;
                            } else {
                                this.connectTiles(currentTile, toTile);
                            }
                        } else if (xDiff != 0 && yDiff != 0) {
                            // if both are not zero, i.e. it is diagonal to the current tile
                            Tile toTile = flatTileMap[x + xDiff][y + yDiff];
                            if (!toTile.getType().isCollidable()) {
                                if (allowCornerCutting) {
                                    this.connectTiles(currentTile, toTile);
                                } else {
                                    if (!flatTileMap[x + xDiff][y].getType().isCollidable() && !flatTileMap[x][y + yDiff].getType().isCollidable()) {
                                        this.connectTiles(currentTile, toTile);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        this.regions = new ArrayList<MapRegion>();
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                Tile currentTile = flatTileMap[x][y];
                if (currentTile.getType().isCollidable()) {
                    continue;
                    // A collidable tile is a wall and shouldn't be in a region
                }
                boolean addedToRegion = false;
                for (MapRegion r : regions) {
                    Tile regionTile = r.getRandomTile();
                    if (findPath(currentTile, regionTile) != null && findPath(regionTile, currentTile) != null) {
                        // Makes sure there is a valid path in both directions to account for 1-way paths
                        // If the path is invalid in the first direction, it short-circuits
                        r.add(currentTile);
                        addedToRegion = true;
                        break;
                    }
                }
                if (!addedToRegion) {
                    MapRegion new_r = new MapRegion();
                    new_r.add(currentTile);
                    regions.add(new_r);
                }
            }
        }
    }

}
