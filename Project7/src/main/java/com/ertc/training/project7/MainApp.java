package com.ertc.training.project7;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MainApp {
    public static final int CARS_COUNT = 5 ;

    public static void main (String[] args) {
        final CountDownLatch countReadyStart = new CountDownLatch(CARS_COUNT);
        final CountDownLatch countFinalCar = new CountDownLatch(CARS_COUNT);
        CyclicBarrier barrierStart = new CyclicBarrier( CARS_COUNT + 1 );

        System.out.println( "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!" );

        Race race = new Race( new Road( 60 ), new Tunnel(80, CARS_COUNT), new Road( 40 ), new Tunnel(30, CARS_COUNT));
        Car[] cars = new Car[CARS_COUNT];
        for ( int i = 0 ; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + ( int ) (Math.random() * 10 ), countReadyStart, barrierStart, countFinalCar);
        }
        for ( int i = 0 ; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            countReadyStart.await();
            System.out.println( "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!" );
            barrierStart.await();
            countFinalCar.await();
            System.out.println( "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!" );
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        for (Car car: cars) {
            if( car.getThePlace() == 1){
                System.out.println(car.getName() + " - Победил в гонке");
            }
        }
    }
}
