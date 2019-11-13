package com.ertc.training.project7;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static int PLACE;
    static {
        CARS_COUNT = 0 ;
        PLACE = 0;
    }
    private Race race;
    private int speed;
    private String name;
    private CountDownLatch countReadyStart;
    private CyclicBarrier barrierStart;
    private CountDownLatch countFinalCar;
    private int thePlace;

    public String getName () {
        return name;
    }

    public int getSpeed () {
        return speed;
    }

    public int getThePlace() {
        return thePlace;
    }

    public void setThePlace() {
        PLACE++;
        this.thePlace = PLACE;
    }

    public Car (Race race, int speed, CountDownLatch countReadyStart, CyclicBarrier barrierStart, CountDownLatch countFinalCar) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.countReadyStart = countReadyStart;
        this.barrierStart = barrierStart;
        this.countFinalCar = countFinalCar;
    }

    @Override
    public void run () {
        try {
            System.out.println(this.name + " готовится (speed = " + this.speed + ")");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            countReadyStart.countDown();
            barrierStart.await();
        } catch (InterruptedException | IllegalMonitorStateException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        for ( int i = 0 ; i < race.getStages().size(); i++) {
            boolean finalRoad = ( i + 1 == race.getStages().size());
            race.getStages().get(i).go( this , finalRoad);
        }
        countFinalCar.countDown();
    }
}
