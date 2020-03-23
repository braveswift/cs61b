package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] fractions;
    private int totalTime;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        totalTime = T;
        fractions = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation perco = pf.make(N);
            while (!perco.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                perco.open(row, col);
            }
            double fraction = perco.numberOfOpenSites() / (double) (N * N);
            fractions[i] = fraction;
        }
    }


    public double mean() {
        return  StdStats.mean(fractions);
    }

    public  double stddev() {
        return StdStats.stddev(fractions);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(totalTime);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(totalTime);
    }

}
