package ex3;

public class TestCounter {
    public static void main(String[] args) {
        Thread counter = new Counter(1);
        Thread counter1 = new Counter(101);
        counter.start();
        counter1.start();
    }
}
