package edu.cmu.sv.app17.models;

public class Car {

    String id = null;
    String make, model, size, color, driverId;
    Number year, odometer ;

    public Car(String make, String model, Number year, String size,
               String color, Number odometer, String driverId) {
        this.make = make;
        this.model = model;
        this.size = size;
        this.year = year;
        this.odometer = odometer;
        this.color = color;
        this.driverId = driverId;
    }
    public void setId(String id) {
        this.id = id;
    }
}
