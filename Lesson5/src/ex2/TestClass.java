package ex2;

public class TestClass {
    public static void main(String[] args) {
        Image [] images= new Image[3];
        images[0]=new RealImage("RealImage");
        images[1]= new RotatedImage("RotatedImage");

        Image image=new ProxyImage("ProxyImage",images[0]);
        Image image1=new ProxyImage("ProxyImage",images[1]);
    }
}
