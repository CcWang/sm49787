db = connect("localhost:27017/app17");
coll = db.paymentMethods;
coll.insert({

    "cardNum" : 4400083719378183,
    "cardType" : "visa",
    "expDate" : new Date(2017, 7, 0, 0, 0),
    "secCode" : NumberInt(123),
    "cardHolderName" : "John Smith",
});
coll.insert({

    "cardNum" : 4400083719364000,
    "cardType" : "discover",
    "expDate" : new Date(2017, 10, 0, 0, 0),
    "secCode" : NumberInt(321),
    "cardHolderName" : "Arya Stark"
});
coll.insert({

    "cardNum" : 44000837193092308,
    "cardType" : "visa",
    "expDate" : new Date(2014, 1, 0, 0, 0),
    "secCode" : NumberInt(121),
    "cardHolderName" : "Ben Smith"
});
coll.insert({

    "cardNum" : 4400083719308800,
    "cardType" : "master",
    "expDate" : new Date(2019, 4, 0, 0, 0),
    "secCode" : NumberInt(203),
    "cardHolderName" : "Alex Zhou"
});
coll.insert({

    "cardNum" : 4400083719378000,
    "cardType" : "visa",
    "expDate" : new Date(2017, 9, 0, 0, 0),
    "secCode" : NumberInt(455),
    "cardHolderName" : "John Brown"
});