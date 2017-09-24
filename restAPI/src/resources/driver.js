db = connect("localhost:27017/app17");
coll = db.drivers;
coll.insert({
        "firstName" : "Arya",
        "middleName": "Edie",
        "lastName" : "Stark",
        "emailAddress":"arya.e.stark@gmail.com",
        "password":"9kdwo9d1!",
        "addressLineOne":"89 Main Street",
        "addressLineTwo":"Apt 1",
        "adderssCity": "Mountain View",
        "addressState": "CA",
        "addressPostal" : "94043",
        "drivingLicenseNumber":"84SL2CYA",
        "licenseIssuedState":"CA",
        "rating": Number(4.0),
        "bankAccountId":"610"
    }
);
coll.insert({
        "firstName" : "Naomi",
        "middleName": "Jaz",
        "lastName" : "Brown",
        "emailAddress":"naomi.j.brown@gmail.com",
        "password":"7jdnlznw!",
        "addressLineOne":"89 Jackson Street",
        "addressLineTwo":"Apt 9",
        "adderssCity": "Palo Alto",
        "addressState": "CA",
        "addressPostal" : "94000",
        "drivingLicenseNumber":"8LK8L2CYA",
        "licenseIssuedState":"CA",
        "rating": Number(4.8),
        "bankAccountId":"611"
    }
);
coll.insert({
        "firstName" : "Mari",
        "middleName": "K",
        "lastName" : "Harvey",
        "emailAddress":"mari.k.harvey@gmail.com",
        "password":"9kdwop1010",
        "addressLineOne":"603 Johns Ave",
        "addressLineTwo":"Apt 2",
        "adderssCity": "San Jose",
        "addressState": "CA",
        "addressPostal" : "90323",
        "drivingLicenseNumber":"94SL2CHA",
        "licenseIssuedState":"CA",
        "rating": Number(4.6),
        "bankAccountId":"612"
    }
);
coll.insert({
        "firstName" : "Charles",
        "middleName": "M",
        "lastName" : "PAGE",
        "emailAddress":"charles.m.page@gmail.com",
        "password":"mki110@9!",
        "addressLineOne":"one blvd",
        "addressLineTwo":"Apt 1302",
        "adderssCity": "Chicago",
        "addressState": "IL",
        "addressPostal" : "60606",
        "drivingLicenseNumber":"IL189L2CYA",
        "licenseIssuedState":"IL",
        "rating": Number(3.8),
        "bankAccountId":"613"
    }
);
coll.insert({
        "firstName" : "Daniel",
        "middleName": "J",
        "lastName" : "Walker",
        "emailAddress":"daniel.j.walker@gmail.com",
        "password":"LKI9019!@HK",
        "addressLineOne":"990 Hardway Blvd",
        "addressLineTwo":"Apt 10",
        "adderssCity": "San Jose",
        "addressState": "CA",
        "addressPostal" : "95050",
        "drivingLicenseNumber":"9LO19CPA",
        "licenseIssuedState":"CA",
        "rating": Number(4.7),
        "bankAccountId":"614"
    }
);
