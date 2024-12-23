public class Percolation {

    private int[] parent; // Union-find structure for connected components
    private boolean[] open; // To track which sites are open
    private int gridSize;
    private int openSitesCount; // To keep track of the number of open sites

    // Creates an n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0 ) throw new IllegalArgumentException("Number is outside of range");

        gridSize = n;
        parent = new int[n * n];  // Union-find parent array
        open = new boolean[n * n];  // Array to track open sites
        openSitesCount = 0; // Initially, no sites are open

        // Initialize the parent array: each site is its own parent
        for (int i = 0; i < n * n; i++) {
            parent[i] = i;
            open[i] = false;  // Initially, no sites are open
        }
    }

    // Convert 2D grid coordinates (row, col) to 1D index
    private int xyTo1D(int row, int col) {
        return row * gridSize + col;
    }

    // Opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || col <= 0 ) throw new IllegalArgumentException("Number is outside of range");


        int site = xyTo1D(row, col);
        if (!open[site]) {
            open[site] = true;  // Mark the site as open
            openSitesCount++;  // Increment the count of open sites

            // Connect the site to its neighboring open sites
            if (isOpen(row - 1, col)) {
                union(site, xyTo1D(row - 1, col));  // Connect to the site above
            }
            if (row < gridSize - 1 && isOpen(row + 1, col)) {
                union(site, xyTo1D(row + 1, col));  // Connect to the site below
            }
            if (isOpen(row, col - 1)) {
                union(site, xyTo1D(row, col - 1));  // Connect to the site on the left
            }
            if (col < gridSize - 1 && isOpen(row, col + 1)) {
                union(site, xyTo1D(row, col + 1));  // Connect to the site on the right
            }
        }
    }

    // Is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || col <= 0 ) throw new IllegalArgumentException("Number is outside of range");

        return open[xyTo1D(row, col)];
    }

    // Is the site (row, col) full? (i.e., connected to the top row)
    public boolean isFull(int row, int col) {
        if (row <= 0 || col <= 0 ) throw new IllegalArgumentException("Number is outside of range");
        int site = xyTo1D(row, col);
        for (int c = 0; c < gridSize; c++) {
            if (isOpen(0, c) && isConnected(xyTo1D(0, c), site)) {
                return true;
            }
        }
        return false;
    }

    // Returns the number of open sites
    public int numberOfOpenSites() {
        return openSitesCount;
    }

    // Does the system percolate? (i.e., there is a path from top to bottom)
    public boolean percolates() {
        for (int col = 0; col < gridSize; col++) {
            if (isConnected(xyTo1D(0, col), xyTo1D(gridSize - 1, col))) {
                return true;  // There's a path from top to bottom
            }
        }
        return false;
    }

    // Find the root of the set that the site belongs to
    private int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];  // Path compression
            p = parent[p];
        }
        return p;
    }

    // Union of two sites
    private void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP != rootQ) {
            parent[rootP] = rootQ;  // Union the two components
        }
    }

    // Check if two sites are connected
    private boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // Test client (optional)
    public static void main(String[] args) {
        // Create a 5x5 percolation system
        Percolation percolation = new Percolation(5);

    }
}
