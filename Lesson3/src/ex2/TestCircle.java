package ex2;

public class TestCircle {
    public static void main(String[] args) {
        Circle circle = new Circle();
        System.out.println(circle.getRadius() == 1.0);
        System.out.println(circle.getArea() == 3.14);
        Circle circle1 = new Circle(2.0, "blue");
        System.out.println(circle1.getRadius() == 2.0);
        System.out.println(circle1.getArea() == 12.56);
    }
}

