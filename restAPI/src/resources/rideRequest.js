db = connect("localhost:27017/app17");
coll = db.rideRequests;
coll.insert({

    "startLat" : Number(37.4165197),
    "startLon" : Number(122.0939422),
    "endLat" : Number(37.4117685),
    "endLon" : Number(122.085033),
    "passengerId": "65",
    "driverId": "450"
});
coll.insert({

    "startLat" : Number(47.4165197),
    "startLon" : Number(112.0939422),
    "endLat" : Number(37.4117685),
    "endLon" : Number(122.085033),
    "passengerId": "55",
    "driverId": "455"
});
coll.insert({

    "startLat" : Number(71.4165197),
    "startLon" : Number(112.0939422),
    "endLat" : Number(47.4117685),
    "endLon" : Number(132.085033),
    "passengerId": "45",
    "driverId": "450"
});
coll.insert({

    "startLat" : Number(37.4758197),
    "startLon" : Number(120.934422),
    "endLat" : Number(37.4117685),
    "endLon" : Number(122.085033),
    "passengerId": "61",
    "driverId": "671"
});
coll.insert({

    "startLat" : Number(37.4165197),
    "startLon" : Number(122.0939422),
    "endLat" : Number(42.4117685),
    "endLon" : Number(132.085033),
    "passengerId": "90",
    "driverId": "655"
});