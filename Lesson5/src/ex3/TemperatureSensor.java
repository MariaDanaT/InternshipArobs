package ex3;

import java.util.Random;

public class TemperatureSensor extends Sensor{
    @Override
    public int readValue() {
        return new Random().nextInt(101);
    }
}
