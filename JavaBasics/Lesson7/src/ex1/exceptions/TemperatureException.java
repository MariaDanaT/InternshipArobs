package ex1.exceptions;

public class TemperatureException extends Exception{
    int t;
    public TemperatureException(int t,String msg) {
        super(msg);
        this.t = t;
    }

    public int getTemp(){
        return t;
    }
}
