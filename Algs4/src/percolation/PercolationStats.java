import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] thresholds; // Array to store percolation thresholds for each trial
    private final int trials;

    // Perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Grid size and number of trials must be greater than 0");
        }

        this.trials = trials;
        thresholds = new double[trials];

        // Run the trials
        for (int t = 0; t < trials; t++) {
            Percolation perc = new Percolation(n);
            int openSites = 0;

            // Open random sites until the system percolates
            while (!perc.percolates()) {
                int row = StdRandom.uniformInt(n);
                int col = StdRandom.uniformInt(n);

                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                    openSites++;
                }
            }

            // Store the percolation threshold for this trial
            thresholds[t] = (double) openSites / (n * n);
        }
    }

    // Sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // Sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // Low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(trials));
    }

    // High endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(trials));
    }

    // Test client
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]); // Grid size
        int trials = Integer.parseInt(args[1]); // Number of trials

        PercolationStats stats = new PercolationStats(n, trials);

        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" +
                stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
