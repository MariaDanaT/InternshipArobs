package ex3;

import java.util.Scanner;

public class PrimeNumbersBetweenAAndB {

    static boolean prime(int n) {
        int d;
        if (n < 2) return false;
        for (d = 2; d <= Math.sqrt(n); d++)
            if (n % d == 0) return false;
        return true;
    }

    public static void main(String[] args) {
        int A, B, count = 0, i;
        System.out.println("Give 2 numbers:");
        Scanner in = new Scanner(System.in);
        A = in.nextInt();
        B = in.nextInt();
        System.out.println("The prime numbers are:");
        for (i = A; i <= B; i++) {
            if (prime(i)) {
                System.out.println(i);
                count++;
            }
        }
        System.out.println(count + " (prime numbers)");
    }
}
