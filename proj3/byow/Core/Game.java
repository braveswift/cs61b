package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.io.Serializable;

public class Game implements Serializable {
    private TETile[][] world;
    private Point player;

    public Game (long seed) {
        world = Draw.drawPrimitive(seed);
        player = Draw.drawPlayer(world);
    }

    public TETile[][] getWorld() {
        return world;
    }

    public Point getPlayer() {
        return player;
    }

    public void moveUp() {
        int y = player.y() + 1;
        int x = player.x();
        if (!world[x][y].equals(Tileset.WALL)) {
            world[player.x()][player.y()] = Tileset.FLOOR;
            world[x][y] = Tileset.AVATAR;
            player = new Point(x, y);
        }
    }

    public void moveDown() {
        int y = player.y() - 1;
        int x = player.x();
        if (!world[x][y].equals(Tileset.WALL)) {
            world[player.x()][player.y()] = Tileset.FLOOR;
            world[x][y] = Tileset.AVATAR;
            player = new Point(x, y);
        }
    }

    public void moveLeft() {
        int y = player.y();
        int x = player.x() - 1;
        if (!world[x][y].equals(Tileset.WALL)) {
            world[player.x()][player.y()] = Tileset.FLOOR;
            world[x][y] = Tileset.AVATAR;
            player = new Point(x, y);
        }
    }

    public void moveRight() {
        int y = player.y();
        int x = player.x() + 1;
        if (!world[x][y].equals(Tileset.WALL)) {
            world[player.x()][player.y()] = Tileset.FLOOR;
            world[x][y] = Tileset.AVATAR;
            player = new Point(x, y);
        }
    }


}
