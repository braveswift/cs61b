package byow.Core;

import java.io.Serializable;

public class Point implements Serializable {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }
    public int y() { return y; }

}
