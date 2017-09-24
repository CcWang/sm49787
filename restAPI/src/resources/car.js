db = connect("localhost:27017/app17");
coll = db.cars;
coll.insert({
    "make":"MINI",
    "model":"2 Door Cooper",
    "year":NumberInt(2013),
    "size":"mini",
    "licensePlate":"JKHP006",
    "licenseState":"CA",
    "vin":"vin029375skumehn9091",
    "odometer":NumberInt(35250),
    "currentInsurer":"Farmers",
    "purchasedYear":NumberInt(2013),
    "ownerNameInTitle":"Cecilia",
    "everBeenInAccident":false
});
coll.insert({
    "make" : "Ford",
    "model" : "Fiesta",
    "year" : NumberInt(2011),
    "size" : "Compact",
    "licensePlate":"OPHP126",
    "licenseState":"CA",
    "vin":"vin029375skdjehn9090",
    "odometer" : NumberInt(34523),
    "currentInsurer":"Farmers",
    "purchasedYear":NumberInt(2012),
    "ownerNameInTitle":"David",
    "everBeenInAccident":true});
coll.insert({
    "make" : "Buick",
    "model" : "Regal",
    "year" : NumberInt(2014),
    "size" : "Full",
    "licensePlate":"1YU26OL",
    "licenseState":"IL",
    "vin":"vin90IL75skdjehn9090",
    "odometer" : NumberInt(12342),
    "currentInsurer":"AAA",
    "purchasedYear":NumberInt(2015),
    "ownerNameInTitle":"Alex",
    "everBeenInAccident":false});
coll.insert({
    "make" : "Chrysler",
    "model" : "300M",
    "year" : NumberInt(2013),
    "size" : "Full",
    "licensePlate":"1YU26OL",
    "licenseState":"CA",
    "vin":"vin90IL75skdjehn9820",
    "odometer" : NumberInt(18342)
    "currentInsurer":"Geico",
    "purchasedYear":NumberInt(2013),
    "ownerNameInTitle":"Anne",
    "everBeenInAccident":true});
coll.insert({
    "make" : "Toyota",
    "model" : "Sienna",
    "year" : NumberInt(2010),
    "size" : "Full",
    "licensePlate":"YU92YOL",
    "licenseState":"MA",
    "vin":"vin90IL75skdolhn9820",
    "odometer" : NumberInt(44000)
    "currentInsurer":"Geico",
    "purchasedYear":NumberInt(2011),
    "ownerNameInTitle":"Catherine",
    "everBeenInAccident":true});

