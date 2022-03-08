package ex6;

public class Factorial {

    static int factorialIterative(int n) {
        int nf = 1;
        for (int i = 2; i <= n; i++) {
                nf*=i;
        }
        return nf;
    }

    static int factorialRecursive(int n){
        if(n==0) return 1;
        return n*factorialRecursive(n-1);
    }

    public static void main(String[] args) {
        int n=5;
        System.out.println(n+"! = "+factorialIterative(n) +" (non recursive method)");
        System.out.println(n+"! = "+factorialRecursive(n) +" (recursive method)");
    }
}
