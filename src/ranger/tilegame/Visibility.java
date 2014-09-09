package ranger.tilegame;

import jmotion.tilegame.model.Direction;
import jmotion.tilegame.model.TileCoord;

import java.util.HashSet;

public class Visibility {

    public void setLocation(TiledLocation location) {
        this.location = location;
    }

    public HashSet<TileCoord> getVisibleCoords(PhysicalEntity entity, int fromRow, int fromCol) {
        HashSet<TileCoord> coords = new HashSet<>();

        int distance = 7;

        TileCoord center = new TileCoord(fromRow, fromCol);
        coords.add(center);
        seeCoords(coords, entity, center.getNorth(), distance, Direction.NORTH);
        seeCoords(coords, entity, center.getSouth(), distance, Direction.SOUTH);
        seeCoords(coords, entity, center.getEast(), distance, Direction.EAST);
        seeCoords(coords, entity, center.getWest(), distance, Direction.WEST);

        seeCoords(coords, entity, center.getNorth().getEast(), distance, Direction.NORTH, Direction.EAST);
        seeCoords(coords, entity, center.getNorth().getWest(), distance, Direction.NORTH, Direction.WEST);
        seeCoords(coords, entity, center.getSouth().getEast(), distance, Direction.SOUTH, Direction.EAST);
        seeCoords(coords, entity, center.getSouth().getWest(), distance, Direction.SOUTH, Direction.WEST);

        return coords;
    }

    public void seeCoords(HashSet<TileCoord> coords, PhysicalEntity entity, TileCoord from, int distance, Direction... directions) {
        // Can't see off of the grid
        if (!location.contains(from))
            return;
        coords.add(from);

        // Can't see past visible range
        if (distance <= 0)
            return;

        for (Direction dir : directions) {
            TileCoord newCoord = from.getNeighbor(dir);

            // Reduce visible distance based on terrain
            int newDistance = distance - location.get(from).terrain.difficulty - 1;

            // See more tiles past this location
            seeCoords(coords, entity, newCoord, newDistance, directions);
        }
    }

    public Visibility(TiledLocation location) {
        this.location = location;
    }

    private TiledLocation location;
}
