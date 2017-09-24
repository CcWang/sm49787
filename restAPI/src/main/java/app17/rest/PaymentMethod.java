package app17.rest;

/*
* cardNum Number;
* cardType String;
* expDate Date;
* secCode Number;
* cardHolderName String;
*
* */

import java.util.Date;

public class PaymentMethod{
    String id = null;
    String cardType, cardHolderName;
    Number cardNum, secCode;
    Date expDate;

    public PaymentMethod(Number cardNum, String cardType, Date expDate, Number secCode, String cardHolderName){
        this.cardNum = cardNum;
        this.cardType = cardType;
        this.expDate = expDate;
        this.secCode = secCode;
        this.cardHolderName = cardHolderName;

    }
    public void setId (String id){this.id = id;}
}