package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class Move {
    public static Point moveUp(TETile[][] world, Point player) {
        int y = player.y() + 1;
        int x = player.x();
        if (world[x][y] != Tileset.WALL) {
            world[player.x()][player.y()] = Tileset.FLOOR;
            world[x][y] = Tileset.AVATAR;
            return new Point(x, y);
        }
        return player;
    }

    public static Point moveDown(TETile[][] world, Point player) {
        int y = player.y() - 1;
        int x = player.x();
        if (world[x][y] != Tileset.WALL) {
            world[player.x()][player.y()] = Tileset.FLOOR;
            world[x][y] = Tileset.AVATAR;
            return new Point(x, y);
        }
        return player;
    }

    public static Point moveLeft(TETile[][] world, Point player) {
        int y = player.y();
        int x = player.x() - 1;
        if (world[x][y] != Tileset.WALL) {
            world[player.x()][player.y()] = Tileset.FLOOR;
            world[x][y] = Tileset.AVATAR;
            return new Point(x, y);
        }
        return player;
    }

    public static Point moveRight(TETile[][] world, Point player) {
        int y = player.y();
        int x = player.x() + 1;
        if (world[x][y] != Tileset.WALL) {
            world[player.x()][player.y()] = Tileset.FLOOR;
            world[x][y] = Tileset.AVATAR;
            return new Point(x, y);
        }
        return player;
    }
}
