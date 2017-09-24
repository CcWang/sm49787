db = connect("localhost:27017/app17");
coll = db.cars;
coll.insert({    "make" : "Ford",
    "model" : "Fiesta",
    "year" : NumberInt(2011),
    "size" : "Compact",
    "odometer" : NumberInt(34523),
    "color" : "red"});
coll.insert({    "make" : "Buick",
    "model" : "Regal",
    "year" : NumberInt(2014),
    "size" : "Full",
    "odometer" : NumberInt(12342),
    "color" : "black"});
coll.insert({    "make" : "Chrysler",
    "model" : "300M",
    "size" : "Full",
    "color" : "black",
    "year" : NumberInt(2014),
    "odometer" : NumberInt(12342)});
coll.insert({    "make" : "Toyota",
    "model" : "Sienna",
    "size" : "Full",
    "color" : "black",
    "year" : NumberInt(2010),
    "odometer" : NumberInt(44)});
coll.insert({    "make" : "Honda",
    "model" : "Accord",
    "size" : "Full",
    "color" : "black",
    "year" : NumberInt(2016),
    "odometer" : NumberInt(5000)});
