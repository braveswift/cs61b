package byow.Core;

import byow.SaveDemo.Editor;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.io.*;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;
    private Point player;
    private int inputType; //1 : KeybordInput  0: StringInput


    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        inputType = 1;
        ter.initialize(WIDTH, HEIGHT);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text((double) WIDTH / 2, HEIGHT * 0.7, "Welcome!");
        StdDraw.text((double) WIDTH / 2, HEIGHT * 0.5, "New Game (N)");
        StdDraw.text((double) WIDTH / 2, HEIGHT * 0.4, "Load Game (L)");
        StdDraw.text((double) WIDTH / 2, HEIGHT * 0.3, "Quit (Q)");
        StdDraw.show();

        KeyBoardInput k = new KeyBoardInput();
        TETile[][] world = modeChoose(k);

        ter.renderFrame(world);
        movePlayer(world, k);

    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public void interactWithInputString(String input) {
        ter.initialize(WIDTH, HEIGHT);
        StringInput st = new StringInput(input);
        TETile[][] world = modeChoose(st);
        ter.renderFrame(world);
        movePlayer(world, st);
    }


    private TETile[][] modeChoose(Input input) {
        TETile[][] world = null;
        while (input.hasNextInput()) {
            char c = input.getNextKey();
            switch (c) {
                case 'N':
                    world = newWord(input);
                    return world;
                case 'L' :
                    world = loadWorld();
                    if (world == null) {
                        System.exit(0);
                        break;
                    }
                    return world;
                case 'Q' :
                    saveWorld(world);
                    System.exit(0);
                    break;
                default: return world;
            }
        }
        return world;
    }


    private TETile[][] newWord(Input input) {
        if (inputType == 1) {
            StdDraw.text((double) WIDTH / 2, HEIGHT * 0.2, "Please type a number and end with S:");
            StdDraw.show();
        }
        long seed = inputToSeed(input);
        TETile[][] world = primitiveWorld(seed);
        return world;
    }

    private void saveWorld(TETile[][] world) {
        File f = new File("./save.txt");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(world);
        }  catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    private TETile[][] loadWorld() {
        File f = new File("./save.txt");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                return (TETile[][]) os.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }

        /* In the case no Editor has been saved yet, we return a new one. */
        return null;
    }



    private long inputToSeed(Input input) {
        String numbers = "0123456789";
        long seed = 0;

        while (input.hasNextInput()) {
            char c = input.getNextKey();
            if (numbers.indexOf(c) != -1) {
                seed = seed * 10 + numbers.indexOf(c);
            }
           if (c == 'S') {
               break;
            }
        }
        return seed;
    }

    private TETile[][] primitiveWorld(long seed) {
        TETile[][] world = Draw.drawPrimitive(seed);
        player = Draw.drawPlayer(world);
        return world;
    }

    private void movePlayer(TETile[][] world, Input input) {
        while (input.hasNextInput()) {
            char c = input.getNextKey();
            if (c == 'W') {
                player = Move.moveUp(world, player);
                ter.renderFrame(world);
            }
            if (c == 'A') {
                player = Move.moveLeft(world, player);
                ter.renderFrame(world);
            }
            if (c == 'S') {
                player = Move.moveDown(world, player);
                ter.renderFrame(world);
            }
            if (c == 'D') {
                player = Move.moveRight(world, player);
                ter.renderFrame(world);
            }
            if (c == 'Q') {
                saveWorld(world);
                System.exit(0);
            }
        }
    }


    public static void main(String[] args) {
        Engine e = new Engine();
        //e.interactWithInputString(args[0]);
        //Input k = new KeyBoardInput();

        e.interactWithKeyboard();
        //TETile[][] world = e.interactWithInputString("n785");
        //e.ter.renderFrame(world);

    }

}
