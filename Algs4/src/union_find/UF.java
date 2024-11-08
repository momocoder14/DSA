package union_find;

public class UF {

    private int[] size;
    private int[] parent;
    int count;

    public UF(int N){
       iniatilizeObjects(N);
    }

    private void iniatilizeObjects(int N){
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 0;
        }
    }

     public void union (int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA > rootB){
            parent[rootB] = rootA;
        }
        else if (rootA < rootB){
            parent[rootA] = rootB;
        } else if (rootA == rootB) {
            parent[rootB] = rootA;
            size[rootA]++;
        }

     };

    public int find (int a){
        boolean isSelfParent = parent[a] == a;
        if(!isSelfParent){
            return find(parent[a]);
        }
        return parent[a];
    }
    public boolean isConnected(int a, int b){
      return find(a) == find(b);
    };
}
