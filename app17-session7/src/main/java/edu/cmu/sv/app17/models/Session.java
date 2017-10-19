package edu.cmu.sv.app17.models;

import edu.cmu.sv.app17.helpers.APPCrypt;

public class Session {

    String token = null;
    String userId = null;
    String firstName = null;
    String lastName = null;

    public Session(Driver driver) throws Exception{
        this.userId = driver.id;
        this.token = APPCrypt.encrypt(driver.id);
        this.firstName = driver.firstName;
        this.lastName = driver.lastName;
    }
}
