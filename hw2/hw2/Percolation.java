package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private WeightedQuickUnionUF ufWithTB;
    private WeightedQuickUnionUF ufWithT;
    private int sizeOfOpen;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new int[N][N];
        ufWithTB = new WeightedQuickUnionUF(N * N + 2);
        ufWithT = new WeightedQuickUnionUF(N * N + 1);
        sizeOfOpen = 0;
    }

    public void open(int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }

        grid[row][col] = 1;
        sizeOfOpen += 1;
        unionAround(row, col, ufWithT);
        unionAround(row, col, ufWithTB);
        if (row == grid.length - 1) {
            ufWithTB.union(rcTo1D(row, col), grid.length * grid.length + 1);
        }
    }

    private void unionAround(int row, int col, WeightedQuickUnionUF uf) {
        if (row == 0) {
            uf.union(rcTo1D(row, col), 0);
        }
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : dirs) {
            int r = row + dir[0];
            int c = col + dir[1];
            if (r >= 0 && r < grid.length && c >=0 && c < grid[0].length && grid[r][c] == 1) {
                uf.union(rcTo1D(row, col), rcTo1D(r, c));
            }
        }
    }

    private int rcTo1D (int row, int col) {
        return row * grid[0].length + col + 1;
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            throw new IndexOutOfBoundsException();
        }
        return ufWithT.connected(0, rcTo1D(row, col));
    }

    public int numberOfOpenSites() {
        return sizeOfOpen;
    }

    public boolean percolates() {
        return ufWithTB.connected(0, grid.length * grid.length + 1);
    }

    public static void main(String[] arg){

    }

}
