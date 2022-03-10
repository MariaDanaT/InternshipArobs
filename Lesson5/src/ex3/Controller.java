package ex3;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    private TemperatureSensor temperatureSensor;
    private LightSensor lightSensor;

    public void control() {
        Timer t = new Timer();
        long startTime = System.currentTimeMillis() / 1000;
        System.out.println(startTime);
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                System.out.println("Temperature sensor: " +
                        new TemperatureSensor().readValue());

                System.out.println("Light sensor: " +
                        new LightSensor().readValue());
                if((System.currentTimeMillis() / 1000)-startTime > 20){
                    t.cancel();
                }
            }

        }, 0, 1000);

    }
}
