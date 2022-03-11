package ex1.exceptions;

public class ConcentrationException extends Exception{
    int c;
    public ConcentrationException(int c,String msg) {
        super(msg);
        this.c = c;
    }

    public int getConc(){
        return c;
    }
}
