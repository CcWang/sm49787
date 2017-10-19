var MongoClient = require('mongodb').MongoClient;

var dbConnection = null;

var lockCount = 0;



function getDbConnection(callback){
    MongoClient.connect("mongodb://localhost/app17-7", function(err, db){
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
            addCarstoDriver(doc.ops[0]._id.toString(),45);
        }
    })
    drivers.insertOne(d[1], function(err,doc){
        if (err){
            console.log("Could not add driver 1");
        }
        else {
            addCarstoDriver(doc.ops[0]._id.toString(),120);
        }
    })
}

makeList = ['Ford','Chevrolet','Toyota','Lexus','BMW','Honda','Subaru'];
modelList = {};
modelList["Ford"] = ['Escape','Escort','Fusion','Explorer'];
modelList["Chevrolet"] = ['Trailblazer','Corsica','Spark','Volt','Suburban'];
modelList["Toyota"] = ['Camry','Avalon','Prius','Sienna','RAV4'];
modelList["Lexus"] = ['RX450','LX400','LS400'];
modelList["BMW"] = ['323','525','X1','X3'];
modelList["Honda"] = ['Accord','Civic','Pilot','Odyssey','Element'];
modelList["Subaru"] = ['Justy','Outback'];
sizeList = ['Compact','Full','Intermdiate'];
colorList = ['red','blue','white','green','silver'];

function addCarstoDriver(driverId,count) {
    sequence = Array(count);
    console.log("sequence",sequence);
    var c = [];
    for (i=0;i<count;i++){
        console.log("Trying")
        var make = makeList[Math.floor(Math.random() * makeList.length)];
        var model = modelList[make][Math.floor(Math.random() * modelList[make].length)];
        var year = Number(Math.floor(Math.random()*20) + 1997);
        var size = sizeList[Math.floor(Math.random() * sizeList.length)];
        var odometer = Number(Math.floor(Math.random()*100000));
        var color = colorList[Math.floor(Math.random() * colorList.length)];

        c.push ({
            make: make,
            model: model,
            year: year,
            size: size,
            odometer: odometer,
            color: color,
            driverId: driverId
        });

    }

    c.forEach(function(car){
        var cars = dbConnection.collection('cars');
        cars.insertOne(car);
    })

}

setTimeout(closeConnection,5000);