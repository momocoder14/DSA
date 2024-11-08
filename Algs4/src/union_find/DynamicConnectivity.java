package union_find;

public class DynamicConnectivity {
    public static void main(String[] args) {
        UF uf = new UF(10);

        // Connect some elements
        uf.union(1, 2);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(6, 7);

        // Check connectivity
        System.out.println(uf.isConnected(1, 3)); // true
        System.out.println(uf.isConnected(1, 4)); // false
        System.out.println(uf.isConnected(4, 5)); // true

        // Union more components
        uf.union(3, 4);
        System.out.println(uf.isConnected(1, 5));
    }
}
