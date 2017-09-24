package app17.rest;

/*
* make string
* model string
* year int
* size string
* licensePlate string
* licensedState string
* vin string
* odometer int
* currentInsurer string
* purchasedYear int
* ownerNameInTitle string
* everBeenInAccident boolean
* */

public class Car {

    String id = null;
    String make, model,size,licensePlate, licensedState, vin, currentInsurer, ownerNameInTitle;
    Number year, odometer, purchasedYear;
    Boolean everBeenInAccident;

    public Car(String make, String model, Number year, String size, String licensePlate, String licensedState,
               String vin, Number odometer, String currentInsurer, Number purchasedYear, String ownerNameInTitle,
               boolean everBeenInAccident) {
        this.make = make;
        this.model = model;
        this.size = size;
        this.year = year;
        this.licensePlate = licensePlate;
        this.licensedState = licensedState;
        this.vin = vin;
        this.odometer = odometer;
        this.currentInsurer = currentInsurer;
        this.purchasedYear = purchasedYear;
        this.ownerNameInTitle = ownerNameInTitle;
        this.everBeenInAccident = everBeenInAccident;

    }

    public void setId(String id) {
        this.id = id;
    }
}
