package ex2;

import java.util.Scanner;

public class PrintNumberInWord {

    static void nestedIf(int a) {
        if (a == 1)
            System.out.println("ONE");
        if (a == 2)
            System.out.println("TWO");
        if (a == 3)
            System.out.println("THREE");
        if (a == 4)
            System.out.println("FOUR");
        if (a == 5)
            System.out.println("FIVE");
        if (a == 6)
            System.out.println("SIX");
        if (a == 7)
            System.out.println("SEVEN");
        if (a == 8)
            System.out.println("EIGHT");
        if (a == 9)
            System.out.println("NINE");
        if (a > 9 || a < 1) System.out.println("OTHER");
    }
    static void nestedSwitch(int a){
        switch (a) {
            case 1:
                System.out.println("ONE");
                break;
            case 2:
                System.out.println("TWO");
            case 3:
                System.out.println("THREE");
                break;
            case 4:
                System.out.println("FOUR");
                break;
            case 5:
                System.out.println("FIVE");
                break;
            case 6:
                System.out.println("SIX");
                break;
            case 7:
                System.out.println("SEVEN");
                break;
            case 8:
                System.out.println("EIGHT");
                break;
            case 9:
                System.out.println("NINE");
                break;
            default:
                System.out.println("OTHER");
                break;
        }
    }
    public static void main(String[] args){
        int a;
        System.out.println("Read number:");
        Scanner in = new Scanner(System.in);
        a = in.nextInt();
        System.out.println("nested-if:");
        nestedIf(a);
        System.out.println("switch-case:");
        nestedSwitch(a);
    }
}
