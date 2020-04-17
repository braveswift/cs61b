package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

public class Room {
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public Room(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public Room (Random random) {
        this.x1 = RandomUtils.uniform(random, 1, Engine.WIDTH - 1);
        this.x2 = RandomUtils.uniform(random, x1, Math.min(Engine.WIDTH - 1, x1 + 8));
        this.y2 = RandomUtils.uniform(random, 1, Engine.HEIGHT - 1);
        this.y1 = RandomUtils.uniform(random, y2, Math.min((Engine.HEIGHT - 1), y2 + 8));
    }

    public int x1() {
        return x1;
    }

    public int x2() {
        return x2;
    }

    public int y1() {
        return y1;
    }

    public int y2() {
        return y2;
    }

    public boolean isValidRoom(TETile[][] world) {
        for (int j = y2; j <= y1; j++) {
            for (int i = x1; i <= x2; i++) {
                if (world[i][j] != Tileset.NOTHING) {
                    return false;
                }
            }
        }
        return true;
    }

    public Point findHallWayPoint(Random random) {
        double randomNum = RandomUtils.uniform(random);
        int x; int y;
        if (randomNum < 0.25) {
            x = x1;
            y = RandomUtils.uniform(random, y2, y1 + 1);
        } else if (randomNum >= 0.25 && randomNum < 0.5) {
            x = x2;
            y = RandomUtils.uniform(random, y2, y1 + 1);
        } else if (randomNum >= 0.5 && randomNum < 0.75) {
            x = RandomUtils.uniform(random, x1, x2 + 1);
            y = y1;
        } else {
            x = RandomUtils.uniform(random, x1, x2 + 1);
            y = y2;

        }
        return new Point(x, y);
    }
}
