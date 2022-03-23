package ex1;

public class Coffee {
    private int temp;
    private int conc;

    Coffee(int t,int c){temp = t;conc = c;}
    int getTemp(){return temp;}
    int getConc(){return conc;}
    public String toString(){return "[coffee temperature="+temp+":concentration="+conc+"]";}

}
