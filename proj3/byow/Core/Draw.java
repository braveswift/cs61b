package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Draw {
    public static void drawAllNothing(TETile[][] world) {
        for (int x = 0; x < Engine.WIDTH ; x += 1) {
            for (int y = 0; y < Engine.HEIGHT ; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void drawRoom(TETile[][] world, Room room) {
        int x1 = room.x1();
        int y1 = room.y1();
        int x2 = room.x2();
        int y2 = room.y2();

        for (int j = y2; j <= y1; j++) {
            for (int i = x1; i <= x2; i++) {
                world[i][j] = Tileset.FLOOR;
            }
        }
    }

    public static void drawHallWay(TETile[][] world, Point p1, Point p2) {
        int x1 = p1.x();
        int y1 = p1.y();
        int x2 = p2.x();
        int y2 = p2.y();

        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            world[x][Math.max(y1, y2)] = Tileset.FLOOR;
        }

        if ((x1 - x2) * (y1 - y2) >= 0) {
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                world[Math.min(x1, x2)][y] = Tileset.FLOOR;
            }
        } else {
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                world[Math.max(x1, x2)][y] = Tileset.FLOOR;
            }
        }
    }

    public static void drawWall(TETile[][] world) {
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}, {-1, 1}, {1, 1}, {-1, -1}, {1, -1}};
        for (int x = 0; x < Engine.WIDTH ; x += 1) {
            for (int y = 0; y < Engine.HEIGHT ; y += 1) {
                for (int[] dir : dirs) {
                    int i = x + dir[0];
                    int j = y + dir[1];
                    if (world[x][y] == Tileset.FLOOR && world[i][j] == Tileset.NOTHING) world[i][j] = Tileset.WALL;
                }
            }
        }
    }


    public static void drawDoor(TETile[][] world, Random random) {
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        List<Point> pointList = new ArrayList<>();
        for (int x = 0; x < Engine.WIDTH ; x += 1) {
            for (int y = 0; y < Engine.HEIGHT; y += 1) {
                boolean hasFloor = false;
                boolean hasNothing = false;
                for (int[] dir : dirs) {
                    int i = x + dir[0];
                    int j = y + dir[1];
                    if (world[x][y] == Tileset.WALL && i >= 0 && i < Engine.WIDTH && j >= 0 && j < Engine.HEIGHT) {
                        if (world[i][j] == Tileset.FLOOR) hasFloor = true;
                        if (world[i][j] == Tileset.NOTHING) hasNothing = true;
                    }
                }
                if (hasFloor && hasNothing) pointList.add(new Point(x, y));
            }
        }
        int i = RandomUtils.uniform(random, pointList.size());
        world[pointList.get(i).x()][pointList.get(i).y()] = Tileset.LOCKED_DOOR;
    }



    public static TETile[][] drawPrimitive(long seed) {

        TETile[][] world = new TETile[Engine.WIDTH][Engine.HEIGHT];
        Draw.drawAllNothing(world);

        Random random = new Random(seed);
        int roomNum = RandomUtils.uniform(random,2, 21);
        int currRoomNum = 0;
        List<Point> pointList = new ArrayList<>();
        while (currRoomNum <= roomNum) {
            Room room = new Room(random);
            if (room.isValidRoom(world)) {
                currRoomNum += 1;
                Draw.drawRoom(world, room);
                pointList.add(room.findHallWayPoint(random));
            }
        }

        for (int i = 0; i < pointList.size() - 1; i++) {
            Draw.drawHallWay(world, pointList.get(i), pointList.get(i + 1));
        }

        Draw.drawWall(world);
        Draw.drawDoor(world, random);

        return world;

    }

    public static Point drawPlayer(TETile[][] world) {
        int x1 = 0;
        int y1 = 0;
        for (int x = 0; x < Engine.WIDTH; x += 1) {
            for (int y = 0; y < Engine.HEIGHT; y += 1) {
                if (world[x][y] == Tileset.FLOOR) {
                    world[x][y] = Tileset.AVATAR;
                    x1 =x;
                    y1 = y;
                    return new Point(x1, y1);
                }
            }
        }
        return new Point(x1, y1);
    }

}
