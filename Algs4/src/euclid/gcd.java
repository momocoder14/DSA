package euclid;

public class gcd {

    public static int gcd(int p, int q)
    {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

    public static void main(String[] args) {
        int answer = gcd(101, 5);
        System.out.println(answer);
    }
}
