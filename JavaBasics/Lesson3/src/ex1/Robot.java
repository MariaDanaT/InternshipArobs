package ex1;

public class Robot{
    private int x;

    public Robot() {
        this.x = 1;
    }

    public void change(int k) {
        this.x += k;
    }


    public String toString(){
        return String.valueOf(this.x);
    }
}
