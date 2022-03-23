package ex6;

public class TestClass {
    public static void main(String[] args) {
        Circle circle = new Circle(2);
        System.out.println(circle);
        circle.setColor("blue");
        circle.setFilled(false);
        System.out.println("updated values: "+circle);

        Square square = new Square();
        System.out.println("square side == 1.0"+(square.getSide() == 1.0));
        square.setLength(3);
        System.out.println("new side value: "+square.getSide());
        square.setWidth(12);
        System.out.println("Updated width (updated length, also). Length = "+square.getLength());
        square.setColor("pink");
        System.out.println("square color: "+square.getColor());
        System.out.println(square);

    }
}
