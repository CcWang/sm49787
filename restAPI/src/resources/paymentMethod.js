db = connect("localhost:27017/app17");
coll = db.paymentMethods;
coll.insert({

    "cardNum" : 4400083719378183,
    "cardType" : "visa",
    "expDate" : "2011-07-00T00:00:00",
    "secCode" : 123,
    "cardHolderName" : "John Smith",
});
coll.insert({

    "cardNum" : 4400083719364000,
    "cardType" : "discover",
    "expDate" : "2017-10-00T00:00:00",
    "secCode" : 321,
    "cardHolderName" : "Arya Stark"
});
coll.insert({

    "cardNum" : 44000837193092308,
    "cardType" : "visa",
    "expDate" : "2014-01-00T00:00:00",
    "secCode" : 121,
    "cardHolderName" : "Ben Smith"
});
coll.insert({

    "cardNum" : 4400083719308800,
    "cardType" : "master",
    "expDate" : "2019-04-00T00:00:00",
    "secCode" : 203,
    "cardHolderName" : "Alex Zhou"
});
coll.insert({

    "cardNum" : 4400083719378000,
    "cardType" : "visa",
    "expDate" : "2017-09-00T00:00:00",
    "secCode" : 499,
    "cardHolderName" : "John Brown"
});