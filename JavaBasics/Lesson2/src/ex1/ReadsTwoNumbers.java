package ex1;

import java.util.Scanner;

public class ReadsTwoNumbers {
    public static void main(String[] args){
        System.out.println("Write two numbers:");

        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();

        if(a > b)
            System.out.println("maxim number is: "+a);
        else System.out.println("maxim number is: "+b);

    }
}
