package performance.ignore.testing;

public class Meme {
    public static void main(String[] args) {
        int a = 100;
        int mask = ((1 << 0) - 1) ;
        System.out.println(1 & mask);
        System.out.println(2 & mask);
        System.out.println(3 & mask);
        System.out.println(4 & mask);
        System.out.println(5 & mask);
        System.out.println(6 & mask);
        System.out.println(7 & mask);
        System.out.println(8 & mask);
        System.out.println(9 & mask);
        System.out.println(10 & mask);
        System.out.println(11 & mask);
        System.out.println(12 & mask);
        for (int i = 0; i < 8; i++) {
            System.out.println(a & 4);
            a = a >> 2;
            System.out.println("a : " + a);
        }
    }
}
