package union_find;

public class QuickUnion {
    private int[] id;
    public QuickUnion(int N){
        id = new int[N];
        for (int i = 0; i < N; i++){
            id[i]=i;
        }

    }

    private int root (int x){
        while (x != id[x]) {
            x = id[x];
        }
        return x;

    }

    private boolean isConnected(int x , int y){
        return root(x) == root(y);
    }

    private void union(int x, int y){
         int rootX =(id[x]);
         int rootY= root(id[y]);

        id[rootX] = rootY;    }

    public static void main(String[] args) {
        QuickUnion qu = new QuickUnion(10);

        qu.union(2,3);
        qu.union(1,3);
        qu.union(4,3);
        qu.union(7,8);
        qu.union(5,6);


        System.out.println(qu.isConnected(1,5));
        System.out.println(qu.isConnected(8,7));
        System.out.println(qu.isConnected(3,6));

        System.out.println(qu.root(1));
        System.out.println(qu.root(2));
        System.out.println(qu.root(4));

    }
}
