package ex5;

public class TestCylinder {
    public static void main(String[] args) {
        Circle circle = new Circle(2);
        Cylinder cylinder = new Cylinder();
        System.out.println("cylinder radius: "+cylinder.getRadius());
        System.out.println("cylinder volume: "+cylinder.getVolume());
        System.out.println("cylinder area == 12.56: "+(cylinder.getArea() == 12.56));
        System.out.println("cylinder area: "+cylinder.getArea());


        Circle circle1 = new Cylinder(2, 2);
        System.out.println("circle radius: "+circle1.getRadius());
        Circle circle2 = new Cylinder(1,2);
        System.out.println("(downcasting) height of cylinder: "+((Cylinder) circle2).getHeight());
        System.out.println("(downcasting) area of cylinder:"+((Cylinder) circle2).getArea());

    }
}
