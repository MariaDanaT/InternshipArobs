package ex1;

public class TestClass {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[3];
        shapes[0]=new Circle(2);
        shapes[1]=new Rectangle(2,3,"blue",true);
        shapes[2]=new Square(4);
        for (Shape shape:shapes
             ) {
            System.out.println(shape.toString()+" with area = "+shape.getArea());
        }
    }
}
