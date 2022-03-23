package ex1;

import ex1.exceptions.ConcentrationException;
import ex1.exceptions.TemperatureException;

public class CoffeeTest {
    public static void main(String[] args) {
        CoffeeMaker mk = new CoffeeMaker();
        CoffeeDrinker d = new CoffeeDrinker();

        for(int i = 0;i<15;i++){
            Coffee c = null;
            try {
                c = mk.makeCoffee();
                d.drinkCoffee(c);
            } catch (TemperatureException e) {
                System.out.println("Exception:"+e.getMessage()+" temp="+e.getTemp());
            } catch (ConcentrationException e) {
                System.out.println("Exception:"+e.getMessage()+" conc="+e.getConc());
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
            finally{
                System.out.println("Throw the cofee cup.\n");
            }
        }
    }
}
