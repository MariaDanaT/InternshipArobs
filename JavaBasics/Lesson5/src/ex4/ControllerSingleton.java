package ex4;


import ex3.LightSensor;
import ex3.TemperatureSensor;

import java.util.Timer;
import java.util.TimerTask;

public class ControllerSingleton {
    private static ControllerSingleton controllerSingleton;

    private ControllerSingleton() {

    }

    public static ControllerSingleton getControllerSingleton() {
        if (controllerSingleton == null) {
            controllerSingleton = new ControllerSingleton();
        }
        return controllerSingleton;
    }

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
                if ((System.currentTimeMillis() / 1000) - startTime > 20) {
                    t.cancel();
                }
            }

        }, 0, 1000);
    }
}

