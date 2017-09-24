package app17.rest;

import java.util.List;

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
* bankAccountId String
*
* */
public class Driver {
    String id = null;
    String firstName, middleName, lastName, emailAddress, password, addressLineOne,
            addressLineTwo, addressCity, addressState, addressPostal, drivingLicenseNumber,
            licenseIssuedState, bankAccountId;
    Number rating;


    public Driver(String firstName, String middleName, String lastName, String emailAddress,
                  String password, String addressLineOne, String addressLineTwo, String addressCity,
                  String addressState, String addressPostal, String drivingLicenseNumber, String licenseIssuedState,
                  Number rating, String bankAccountId){
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
        this.bankAccountId = bankAccountId;


    }
    public void setId(String id) {
        this.id = id;
    }
}