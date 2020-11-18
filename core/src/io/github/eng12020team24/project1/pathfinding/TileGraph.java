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

// This code is based on code here https://happycoding.io/tutorials/libgdx/pathfinding, which is released under the terms of the CC BY 4.0 license, available at https://creativecommons.org/licenses/by/4.0/.
public class TileGraph implements IndexedGraph<Tile> {

    TileHeuristic heuristic = new TileHeuristic();
    Array<Tile> tiles = new Array<>();
    Array<TileConnection> connections = new Array<>();
    private Tile[][] flatTileMap;
    private ArrayList<MapRegion> regions;

    ObjectMap<Tile, Array<Connection<Tile>>> connectionMap = new ObjectMap<>();

    private int lastNodeIndex = 0;

    /**
     * Adds a new Tile to this graph
     * @param tile The tile to add to the graph
     */
    public void addTile(Tile tile) {
        tile.setIndex(lastNodeIndex);
        lastNodeIndex++;
        tiles.add(tile);
    }

    /**
     * Creates a new connection between two Tiles
     * @param fromTile The origin of this connection
     * @param toTile The destination of this connection
     */
    public void connectTiles(Tile fromTile, Tile toTile) {
        TileConnection newConnection = new TileConnection(fromTile, toTile);
        if (!connectionMap.containsKey(fromTile)) {
            connectionMap.put(fromTile, new Array<Connection<Tile>>());
        }
        connectionMap.get(fromTile).add(newConnection);
        connections.add(newConnection);
    }

    /**
     * Finds a path between two Tiles
     * @param startTile The origin Tile of the path
     * @param goalTile The destination Tile of the path
     * @return a GraphPath<Tile> containing the path
     */
    public GraphPath<Tile> findPath(Tile startTile, Tile goalTile) {
        GraphPath<Tile> tilePath = new DefaultGraphPath<>();
        new IndexedAStarPathFinder<>(this).searchNodePath(startTile, goalTile, heuristic, tilePath);
        return tilePath;
    }

    /**
     * Finds a path between two Tiles
     * @param xPos the xPos in the game world of the origin
     * @param yPos the yPos in the game world of the origin
     * @param goalXPos the xPos in the game world of the destination
     * @param goalYPos the yPos in the game world of the desination
     * @return a GraphPath<Tile> containing the path
     */
    public GraphPath<Tile> findPath(int xPos, int yPos, int goalXPos, int goalYPos) {
        return this.findPath(this.getTileFromCoordinates(xPos, yPos), this.getTileFromCoordinates(goalXPos, goalYPos));
    }

    /**
     * Gets the specific tile under a given set of coordinates in the game world
     * @param xPos the xPos in the game world of the tile
     * @param yPos the yPos in the game world of the tile
     * @return a Tile indicated by the given coordinates in the game world
     */
    public Tile getTileFromCoordinates(int xPos, int yPos) {
        int tileSize = flatTileMap[0][0].getType().TILE_SIZE;
        return flatTileMap[Math.floorDiv(xPos, tileSize)][Math.floorDiv(yPos, tileSize)];
    }

    /**
     * Gets the index for a specific tile
     * @param tile the Tile to get the index for
     * @return the tile's index
     */
    @Override
    public int getIndex(Tile tile) {
        // TODO Auto-generated method stub
        return tile.getIndex();
    }

    /**
     * Gets the number of nodes in this graph
     * @return the number of nodes in this graph
     */
    @Override
    public int getNodeCount() {
        // TODO Auto-generated method stub
        return lastNodeIndex;
    }

    /**
     * Gets the connections originating at a given tile
     * @param fromNode the tile that is the origin of these connections
     * @return an Array that contains all the connections originating at a given tile
     */
    @Override
    public Array<Connection<Tile>> getConnections(Tile fromNode) {
        // TODO Auto-generated method stub
        if (connectionMap.containsKey(fromNode)) {
            return connectionMap.get(fromNode);
        }
        return new Array<>(0);
    }

    /**
     * Returns the region containing a specific tile
     * @param tile 
     * @return The map region containing a specific tile, or null if no region contains it (e.g. it is collidable)
     */
    public MapRegion getRegionForTile(Tile tile) {
        for (MapRegion r : regions) {
            if (r.contains(tile)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Creates a new TileGraph from the given map
     * @param map the TiledGameMap used to create the TileGraph
     */
    public TileGraph(TiledGameMap map) {
        boolean allowCornerCutting = false;
        // This is used to determine if the path should have to go around corners or directly across them
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
                                    if (!flatTileMap[x + xDiff][y].getType().isCollidable()
                                            && !flatTileMap[x][y + yDiff].getType().isCollidable()) {
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
                        // Makes sure there is a valid path in both directions to account for 1-way
                        // paths
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
        //TODO This isn't exactly efficient-a flood fill or similar would be more efficient.
    }

}
