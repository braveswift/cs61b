package byow.Core;

import edu.princeton.cs.introcs.StdDraw;

public class KeyBoardInput implements Input {
    public KeyBoardInput() {
        StdDraw.text((double) Engine.WIDTH / 2, Engine.HEIGHT * 0.2, "");
    }

    public char getNextKey() {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = Character.toUpperCase(StdDraw.nextKeyTyped());
                return c;
            }
        }
    }

    public boolean hasNextInput() {
        return true;
    }
}


