package byow.Core;

import byow.InputDemo.KeyboardInputSource;
import byow.TileEngine.TERenderer;
import edu.princeton.cs.introcs.StdDraw;

import java.io.*;

public class Engine {
    TERenderer ter = new TERenderer();
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
        Game game = modeChoose(k);

        ter.renderFrame(game.getWorld());
        movePlayer(game, k);

    }


    public void interactWithInputString(String input) {
        inputType = 0;
        ter.initialize(WIDTH, HEIGHT);
        StringInput st = new StringInput(input);
        Game game = modeChoose(st);
        ter.renderFrame(game.getWorld());
        movePlayer(game, st);
    }


    private Game modeChoose(Input input) {
        Game game = null;
        while (input.hasNextInput()) {
            char c = input.getNextKey();
            switch (c) {
                case 'N':
                    game = newGame(input);
                    return game;
                case 'L' :
                    game = loadGame();
                    if (game == null) {
                        System.exit(0);
                        break;
                    }
                    return game;
                case 'Q' :
                    saveGame(game);
                    System.exit(0);
                    break;
                default: return game;
            }
        }
        return game;
    }


    private Game newGame(Input input) {
        if (inputType == 1) {
            StdDraw.text((double) WIDTH / 2, HEIGHT * 0.2, "Please type a number and end with S:");
            StdDraw.show();
        }
        long seed = inputToSeed(input);
        Game game = new Game(seed);
        return game;
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

    private void saveGame(Game game) {
        File f = new File("./save.txt");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(game);
        }  catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    private Game loadGame() {
        File f = new File("./save.txt");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                return (Game) os.readObject();
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

    private void movePlayer(Game game, Input input) {
        while (input.hasNextInput()) {
            char c = input.getNextKey();
            if (c == 'W') {
                game.moveUp();
                ter.renderFrame(game.getWorld());
            }
            if (c == 'A') {
                game.moveLeft();
                ter.renderFrame(game.getWorld());
            }
            if (c == 'S') {
                game.moveDown();
                ter.renderFrame(game.getWorld());
            }
            if (c == 'D') {
                game.moveRight();
                ter.renderFrame(game.getWorld());
            }
            if (c == 'Q') {
                saveGame(game);
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
