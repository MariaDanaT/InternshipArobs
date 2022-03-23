package ex1;

public class CoffeeMaker {
    private static int coffeeNumber = 0;
    public Coffee makeCoffee() throws Exception {
        System.out.println("Make a coffe");
        if(coffeeNumber > 9)
            throw new Exception("Empty espresso");
        int t = (int)(Math.random()*100);
        int c = (int)(Math.random()*100);
        Coffee coffee = new Coffee(t,c);
        coffeeNumber++;
        System.out.println(coffeeNumber);
        return coffee;
    }
}
