package ex3;

import java.util.stream.IntStream;

public class Counter extends Thread {
    private int count;

    public Counter(int count) {
        this.count = count;
    }

    synchronized void getValues() {
        IntStream.range(count, count + 100).forEach(System.out::println);
    }

    public void run() {
        getValues();
    }
}
