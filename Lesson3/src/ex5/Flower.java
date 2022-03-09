package ex5;

public class Flower {
    private int petal;
    private static int count = 0;

    public Flower() {
        System.out.println("Flower has been created!");
        count++;
    }
    public static int getCount(){
        return count;
    }

}
