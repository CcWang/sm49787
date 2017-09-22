package app17.rest;

public class Car {

    String id = null;
    String make, model, size, color;
    Number year, odometer;

    public Car(String make, String model, Number year, String size, String color, Number odometer) {
        this.make = make;
        this.model = model;
        this.size = size;
        this.year = year;
        this.odometer = odometer;
        this.color = color;
    }

    public void setId(String id) {
        this.id = id;
    }
}
