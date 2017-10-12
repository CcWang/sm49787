var MongoClient = require('mongodb').MongoClient;

var dbConnection = null;

var lockCount = 0;



function getDbConnection(callback){
    MongoClient.connect("mongodb://localhost/app17-6", function(err, db){
        if(err){
            console.log("Unable to connect to Mongodb");
        }else{
            dbConnection = db;
            callback();
        }
    });
};

function closeConnection() {
    if (dbConnection)
        dbConnection.close();

}

getDbConnection(function(){
    dbConnection.dropDatabase(function(err,doc){
        if (err)
            console.log("Could not drop database");
        else
            addDriver();
    });
});

function addDriver() {
    d = [{
        "firstName":    "John",
        "lastName":     "Malkovich",
        "emailAddress":        "john@malkovich.com",
        "password":     "0pKmbgxhk+w2GF5d4uM6ZQ=="
    },
        {
            "firstName":    "Robert",
            "lastName":     "Redford",
            "emailAddress":        "robert@redford.com",
            "password": "i+g6Eeh4jpRB0mkloTDdnA=="
        }];
    var drivers = dbConnection.collection('drivers');
    drivers.insertOne(d[0], function(err,doc){
        if (err){
            console.log("Could not add driver 1");
        }
        else {
            addCarstoDriver0(doc.ops[0]._id.toString());
        }
    })
    drivers.insertOne(d[1], function(err,doc){
        if (err){
            console.log("Could not add driver 1");
        }
        else {
            addCarstoDriver1(doc.ops[0]._id.toString());
        }
    })
}

function addCarstoDriver0(driverId) {
    c = [{
        "make" : "Ford",
        "model" : "Fiesta",
        "year" : 2011,
        "size" : "Compact",
        "odometer" : 34523,
        "color" : "red",
        "driverId" : driverId
    },{
        "make" : "Buick",
        "model" : "Regal",
        "year" : 2014,
        "size" : "Full",
        "odometer" : 12342,
        "color" : "black",
        "driverId" : driverId
    },{
        "make" : "Toyota",
        "model" : "Camry",
        "year" : 2010,
        "size" : "Intermediate",
        "odometer" : 34111,
        "color" : "blue",
        "driverId" : driverId
    }];
    c.forEach(function(car){
        var cars = dbConnection.collection('cars');
        cars.insertOne(car);
    })

}

function addCarstoDriver1(driverId) {
    c = [{
        "make" : "BMW",
        "model" : "X3",
        "year" : 2016,
        "size" : "Compact",
        "odometer" : 5823,
        "color" : "red",
        "driverId" : driverId
    },{
        "make" : "Toyota",
        "model" : "Prius",
        "year" : 2011,
        "size" : "Compact",
        "odometer" : 29233,
        "color" : "white",
        "driverId" : driverId
    }];
    c.forEach(function(car){
        var cars = dbConnection.collection('cars');
        cars.insertOne(car);
    })

}

setTimeout(closeConnection,5000);