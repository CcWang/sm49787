package app17.rest;


import javax.ws.rs.Path;

public class Driver {
    String id;
    String firstName;
    String lastName;
    String phoneNumber;
    String address1;
    String address2;
    String stateCode;
    String countryCode;
    float rating;
    public Driver(String firstName, String lastName,
               String phoneNumber, String address1, String address2,
                  String stateCode, String countryCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address1 = address1;
        this.address2 = address2;
        this.stateCode = stateCode;
        this.countryCode = countryCode;
        this.rating = 0.0f;
    }
    public void setId(String id) {
        this.id = id;
    }
}
