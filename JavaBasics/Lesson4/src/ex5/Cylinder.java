package ex5;

public class Cylinder extends Circle {
    private double height = 1.0;

    public Cylinder() {
        super();
    }

    public Cylinder(double radius) {
        super(radius);
    }

    public Cylinder(double radius, double height) {
        super(radius);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public double getVolume() {
        return height * 3.14 * Math.pow(super.getRadius(), 2);
    }

    @Override
    public double getArea() {
        return (2 * 3.14 * super.getRadius() * height + 2*super.getArea());
    }
}
