package com.ertc.training.project7;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private static Semaphore stopTunnel;

    public Tunnel (int length, int carsСount) {
        this.length = length ;
        this.description = "Тоннель " + length + " метров" ;
        if(stopTunnel == null){
            stopTunnel = new Semaphore( Math.round(carsСount/2) );
        }
    }
    @Override
    public void go (Car c, boolean finalRoad) {
        try {
            try {
                if(!stopTunnel.tryAcquire()) {
                    System.out.println(c.getName() + " готовится к этапу(ждет): " +
                            description);
                    stopTunnel.acquire();
                }
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " +
                        description);
                stopTunnel.release();
                if( finalRoad ) {
                    c.setThePlace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
