package union_find;

public class QuickFindUF {
    private int[] id;

    public QuickFindUF(int size){
        initializeObjects(size);
    }

    private void initializeObjects(int size) {
        id = new int[size];

        for(int i =0;i<size; i++){
            id[i]=i;
        }
    }

    private void union(int x, int y){
        int idx = find(x);
        int idy = find(y);

        for (int i=0;i<id.length;i++){
            if(id[i] == idx) id[i] = idy;
        }

    }

    private int find (int x){
        return id[x];
    }

    private boolean isConnected(int x, int y){
        return find(x) == find(y);

    }

    public static void main(String[] args) {
        QuickFindUF qf = new QuickFindUF(10);

        qf.union(2,3);
        qf.union(1,3);
        qf.union(4,3);
        qf.union(7,8);
        qf.union(5,6);


        System.out.println(qf.isConnected(1,5));
        System.out.println(qf.isConnected(8,7));
        System.out.println(qf.isConnected(3,6));

        System.out.println(qf.find(1));
        System.out.println(qf.find(2));
        System.out.println(qf.find(4));





    }

}
