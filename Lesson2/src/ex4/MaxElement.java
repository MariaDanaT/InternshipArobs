package ex4;

public class MaxElement {
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 7, 5};
        int max;
        if (arr.length > 0)
            max = arr[0];
        else max = 0;
        for (int e : arr
        ) {
            if (max < e)
                max = e;
        }
        if (arr.length > 0)
            System.out.println("maximum element is: " + max);
        else System.out.println("The array is empty!");
    }
}
