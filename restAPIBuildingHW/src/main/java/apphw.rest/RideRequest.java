package main.java.apphw.rest;
/*
* start_lat Number
* start_lon Number
* end_lat Number
* end_lon Number
* */

public class RideRequest{
    String id = null;
    Number start_lat, start_lon, end_lat, end_lon;


    public RideRequest(Number start_lat, Number start_lon, Number end_lat, Number end_lon){
        this.start_lat = start_lat;
        this.start_lon = start_lon;
        this.end_lat = end_lat;
        this.end_lon = end_lon;

    }

    public void sedId(String id){
        this.id = id;
    }
}