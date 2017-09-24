package app17.rest;

/*
* bankName String
* accountNum Number
* routingNum Number
* accHolderName String
* verified Boolean
* */

public class BankAccount{
    String id = null;
    String bankName, accHolderName;
    Number accountNum, routingNum;
    Boolean verified;

    public BankAccount(String bankName, Number accountNum, Number routingNum, String accHolderName, boolean verified){
        this.bankName = bankName;
        this.accountNum = accountNum;
        this.routingNum = routingNum;
        this.accHolderName = accHolderName;
        this.verified = verified;

    }

    public void setID (String id){
        this.id = id;
    }
}