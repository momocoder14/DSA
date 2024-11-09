package union_find;

public class QuickUnionWeight_Size {
    private int[] id;    // Array to hold the parent of each element
    private int[] size;  // Array to track the size of each component's root

    // Constructor: Initialize each element to be its own root and set size to 1
    public QuickUnionWeight_Size(int N) {
        id = new int[N];
        size = new int[N];  // Initialize size array

        for (int i = 0; i < N; i++) {
            id[i] = i;       // Each element points to itself (root of its own component)
            size[i] = 1;     // Each component initially has size 1
        }
    }

    // Find the root of x by following parent pointers until reaching the root
    private int root(int x) {
        while (x != id[x]) {  // Follow the path until x points to itself (root)
            x = id[x];
        }
        return x;
    }

    // Check if x and y are in the same component by comparing their roots
    public boolean isConnected(int x, int y) {
        return root(x) == root(y);
    }

    // Connect two elements by linking the smaller tree to the larger one
    public void union(int x, int y) {
        int rootX = root(x);  // Find root of x
        int rootY = root(y);  // Find root of y

        if (rootX == rootY) return;  // They are already connected, no action needed

        // Attach the smaller tree to the root of the larger tree
        if (size[rootX] > size[rootY]) {
            id[rootY] = rootX;         // Make rootX the parent of rootY
            size[rootX] += size[rootY]; // Update size of rootX
        } else {
            id[rootX] = rootY;         // Make rootY the parent of rootX
            size[rootY] += size[rootX]; // Update size of rootY
        }
    }

    // Test the implementation
    public static void main(String[] args) {
        QuickUnionWeight_Size qu = new QuickUnionWeight_Size(10);

        qu.union(2, 3);
        qu.union(1, 3);
        qu.union(4, 3);
        qu.union(7, 8);
        qu.union(5, 6);

        System.out.println(qu.isConnected(1, 5));  // Expected: false
        System.out.println(qu.isConnected(8, 7));  // Expected: true
        System.out.println(qu.isConnected(3, 6));  // Expected: false

        System.out.println(qu.root(1));  // Outputs the root of element 1
        System.out.println(qu.root(2));  // Outputs the root of element 2
        System.out.println(qu.root(4));  // Outputs the root of element 4
    }
}
