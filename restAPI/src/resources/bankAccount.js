db = connect("localhost:27017/app17");
coll = db.bankAccounts;
coll.insert({

    "id" : 6517,
    "bankName" : "Bank Of American",
    "accountNum" : 4098710938,
    "routingNum" : 3800223,
    "accHolderName" : "John Smith",
    "verified" : true
});
coll.insert({

    "id" : 6527,
    "bankName" : "Chase",
    "accountNum" : 403402938,
    "routingNum" : 360000,
    "accHolderName" : "Arya Stark",
    "verified" : true
});
coll.insert({

    "id" : 6607,
    "bankName" : "Citi Bank",
    "accountNum" : 40987109350,
    "routingNum" : 3800200,
    "accHolderName" : "Catherine Walker",
    "verified" : false
});
coll.insert({

    "id" : 6407,
    "bankName" : "Bank Of American",
    "accountNum" : 4098700002,
    "routingNum" : 370093,
    "accHolderName" : "Alex Page",
    "verified" : true
});
coll.insert({

    "id" : 6570,
    "bankName" : "Chase",
    "accountNum" : 4308710938,
    "routingNum" : 3800200,
    "accHolderName" : "Julie Zhou",
    "verified" : true
});