package ex7;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Random r = new Random();
        int n = r.nextInt(), tries = 0, answer;
        System.out.println(n);
        System.out.println("Guess the number");
        Scanner in = new Scanner(System.in);
        answer = in.nextInt();
        while (tries < 2 && answer != n) {
            if (answer > n)
                System.out.println("Wrong answer, your number it too high");
            else if (answer < n)
                System.out.println("Wrong answer, your number is too low");
            tries++;
            System.out.println("Guess the number");
            answer = in.nextInt();
        }
        if(answer == n)
            System.out.println("You won");
        else System.out.println("You lost");
    }
}
