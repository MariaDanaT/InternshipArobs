package ex2;

public class RotatedImage implements Image {
    private String fileName;

    public RotatedImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        System.out.println("Display rotated " + fileName);
    }
}
