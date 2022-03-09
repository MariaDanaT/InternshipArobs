package ex2;

public class RotatedImage implements Image {
    private String fileName;

    @Override
    public void display() {
        System.out.println("Display rotated " + fileName);
    }
}
