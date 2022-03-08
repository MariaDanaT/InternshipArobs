package ex5;

import java.util.Random;


public class SortElements {

    static public void main(String[] args) {
        int[] arr = new int[10];
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            arr[i] = r.nextInt(100);
        }
        for (int e : arr
        ) {
            System.out.println(e);
        }
        //bubble sort
        int n = 10, aux;
        while (n > 1) {
            for (int i = 1; i < n; i++) {
                if (arr[i - 1] > arr[i]) {
                    aux = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = aux;
                }
            }
            n--;
        }
        for (int e : arr
        ) {
            System.out.println(e);
        }
    }
}
