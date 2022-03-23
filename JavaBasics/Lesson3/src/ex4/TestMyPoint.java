package ex4;

public class TestMyPoint {
    public static void main(String[] args) {
        MyPoint myPoint = new MyPoint();
        assert myPoint.getX() == 0;
        assert myPoint.getY() == 0;
        myPoint.setX(1);
        assert myPoint.getX() == 1;
        myPoint.setY(2);
        assert myPoint.getY() == 2;
        myPoint.setXY(0, 0);
        assert myPoint.getX() == 0;
        assert myPoint.getY() == 0;
        assert myPoint.toString().equals("(0,0)");
        assert myPoint.distance(0, 2) == 2.0;
        MyPoint myPoint1 = new MyPoint(4, 3);
        assert myPoint.distance(myPoint1) == 5.0;

        System.out.println("All tests passed");
    }
}
