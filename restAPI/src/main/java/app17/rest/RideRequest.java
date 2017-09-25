package app17.rest;

/*
* start_lat Number
* start_lon Number
* end_lat Number
* end_lon Number
* passengerId String
* driverID String
* */

public class RideRequest{
    String id = null;
    Number startLat, startLon, endLat, endLon;
    String passengerId, driverId;


    public RideRequest(Number startLat, Number startLon, Number endLat, Number endLon,
    String passengerId, String driverId){
        this.startLat = startLat;
        this.startLon = startLon;
        this.endLat = endLat;
        this.endLon = endLon;
        this.passengerId = passengerId;
        this.driverId = driverId;

    }

    public void setId(String id){

        this.id = id;
    }
}
