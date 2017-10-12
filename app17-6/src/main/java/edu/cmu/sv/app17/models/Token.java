package edu.cmu.sv.app17.models;

import edu.cmu.sv.app17.helpers.APPCrypt;

public class Token {

    String token = null;
    String userId = null;
    String firstName = null;
    String lastName = null;

    public Token(Driver driver) throws Exception{
        this.userId = driver.id;
//        use encrypted driver.id as token
        this.token = APPCrypt.encrypt(driver.id);
        this.firstName = driver.firstName;
        this.lastName = driver.lastName;
    }
}
