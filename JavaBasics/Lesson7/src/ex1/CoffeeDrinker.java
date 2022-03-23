package ex1;

import ex1.exceptions.ConcentrationException;
import ex1.exceptions.TemperatureException;

public class CoffeeDrinker {
    void drinkCoffee(Coffee c) throws TemperatureException, ConcentrationException{
        if(c.getTemp()>60)
            throw new TemperatureException(c.getTemp(),"Cofee is to hot!");
        if(c.getConc()>50)
            throw new ConcentrationException(c.getConc(),"Cofee concentration to high!");
        System.out.println("Drink cofee:"+c);
    }
}
