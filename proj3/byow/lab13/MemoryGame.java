package byow.lab13;

import edu.princeton.cs.introcs.StdDraw;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        Random rand = new Random(seed);
        MemoryGame game = new MemoryGame(80, 80, rand);
        game.startGame();
    }

    public MemoryGame(int width, int height, Random random) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.rand = random;
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //Done: Initialize random number generator
    }

    public String generateRandomString(int n) {
        //Done:Generate random string of letters of length n
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int i = rand.nextInt(26);
            sb.append(CHARACTERS[i]);
            n = n - 1;
        }
        return sb.toString();
    }

    public void drawFrame(String s) {
        //DONE: Take the string and display it in the center of the screen
        //DONE: If game is not over, display relevant game information at the top of the screen
        StdDraw.clear(Color.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.textLeft(2,height - 2,"ROUND:" + round);
        StdDraw.textRight(width - 2, height - 2, "You can do it!");
        StdDraw.text((double) width / 2, (double ) height / 2, s);

        if (playerTurn) {
            StdDraw.text((double) width / 2, height - 2, "Type!");
        } else {
            StdDraw.text((double) width / 2, height - 2, "Watch!");
        }

        StdDraw.show();
    }

    public void drawState(String s) {
        StdDraw.clear(Color.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text((double) width / 2, (double ) height / 2, s);
        StdDraw.show();
        StdDraw.pause(500);
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for (int i = 0; i < letters.length(); i++) {
            String s = letters.substring(i, i + 1);
            drawFrame(s);
            StdDraw.pause(1000);
            drawFrame("");
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        char[] charArray = new char[n];
        int i = 0;
        while (i < n) {
            if (StdDraw.hasNextKeyTyped()) {
                charArray[i] = StdDraw.nextKeyTyped();
                i = i + 1;
            }
        }
        return new String(charArray);
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        //TODO: Establish Engine loop
        round = 3;
        String randomString = "";
        String userString = "";
        while (userString.equals(randomString)) {
            round += 1;
            drawState("Round" + round);
            playerTurn = false;
            randomString = generateRandomString(round);
            flashSequence(randomString);
            playerTurn = true;
            drawFrame("");
            userString = solicitNCharsInput(round);
        }
        drawState("Game Over");
    }

}
