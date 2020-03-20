import java.util.ArrayList;
import java.util.List;

public class BubbleGrid {
    private int[][] grid;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        int[][] copyGrid = copyArray();
        int totalBubble = totalBubble(copyGrid);
        int dartNumber = darts.length;
        int[] fallBubbleArray = new int[dartNumber];
        int fallBubbleNumber;
        for (int i = 0; i < dartNumber; i++) {
            if (copyGrid[darts[i][0]][darts[i][1]] == 0) {
                fallBubbleArray[i] = 0;
            } else {
                aferDartGrid(copyGrid, darts[i]);
                UnionFind afterDartUf = ufGrid(copyGrid);
                fallBubbleNumber = totalBubble - stuckBubble(afterDartUf, copyGrid) - 1;
                totalBubble = stuckBubble(afterDartUf, copyGrid);
                fallBubbleArray[i] = fallBubbleNumber;
            }
        }
        return fallBubbleArray;
    }

    private int[][] copyArray() {
        int[][] copyGrid = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i ++) {
            for (int j= 0 ; j < grid[i].length; j++) {
                copyGrid[i][j] = grid[i][j];
            }
        }
        return copyGrid;
    }


    /* change the grid afer dart */
    private void aferDartGrid(int[][] grid, int[] singleDart) {
        int row = singleDart[0];
        int col = singleDart[1];
        grid[row][col] = 0;
    }


    private UnionFind ufGrid(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int capacity = row * col;
        UnionFind uf = new UnionFind(capacity);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    if (i - 1 >= 0 && grid[i -1][j] == 1) {
                        uf.union(convert(i,j), convert(i -1, j));
                    }
                    if (i + 1 < grid.length && grid[i + 1][j] == 1) {
                        uf.union(convert(i, j), convert(i + 1, j));
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                        uf.union(convert(i, j), convert(i, j - 1));
                    }
                    if (j + 1 < grid[0].length && grid[i][j + 1] == 1) {
                        uf.union(convert(i, j), convert(i, j + 1));
                    }
                }
            }
        }
        return uf;
    }

    private int convert(int i, int j) {
        int returnItem = i * grid[0].length + j;
        return returnItem;
    }

    /* return the number of bubble that won't fall */
    private int stuckBubble(UnionFind uf, int[][] grid) {
        int size = 0;
        int NumberOfFirstRow = grid[0].length;
        List<Integer> disjointBubble = new ArrayList<>();

        for (int i = 0; i < NumberOfFirstRow; i++) {
            if (grid[0][i] == 1) {
                if (disjointBubble.isEmpty()) {
                    size = size + uf.sizeOf(i);
                    disjointBubble.add(i);
                } else {
                    for (int j = 0; j < disjointBubble.size(); j++) {
                        if (uf.connected(i, disjointBubble.get(j))) {
                            break;
                        }
                        size = size + uf.sizeOf(i);
                        disjointBubble.add(i);
                    }
                }
            }
        }
        return size;
    }

    private int totalBubble(int[][] grid) {
        int totalSize = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    totalSize = totalSize + 1;
                }
            }
        }
        return totalSize;
    }
}
