package com.ertc.training.project7;

public class Road extends Stage {
    public Road ( int length) {
        this .length = length;
        this .description = "Дорога " + length + " метров" ;
    }
    @Override
    public void go (Car c, boolean finalRoad) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000 );
            System.out.println(c.getName() + " закончил этап: " + description);
            if( finalRoad ) {
                c.setThePlace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
