package main.java.apphw.rest;
/*
* firstName String
* middleName String
* lastName String
* emailAddress String
* password String
* addressLineOne String
* addressLineTwo String
* addressCity String
* addressState String
* addressPostal String
* drivingLicenseNumber String
* licenseIssuedState String
* rating Number
*
* */
public class Driver {
    String id = null;
    String firstName, middleName, lastName, emailAddress, password, addressLineOne,
    addressLineTwo, addressCity, addressState, addressPostal, drivingLicenseNumber,
    licenseIssuedState;
    Number rating;

    public Driver(String firstName, String middleName, String lastName, String emailAddress,
                  String password, String addressLineOne, String addressLineTwo, String addressCity,
                  String addressState, String addressPostal, String drivingLicenseNumber, String licenseIssuedState,
                  Number rating){
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.addressCity = addressCity;
        this.addressState = addressState;
        this.addressPostal = addressPostal;
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.licenseIssuedState = licenseIssuedState;
        this.rating = rating;


    }
    public void setId(String id) {
        this.id = id;
    }
}