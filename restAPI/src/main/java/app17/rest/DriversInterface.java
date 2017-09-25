package app17.rest;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/*
* firstName String
* middleName String
* lastName String
* emailAddress String
* password String
* addressLineOne String
* addressLineTwo String
* addressCity String
* addressState String
* addressPostal String
* drivingLicenseNumber String
* licenseIssuedState String
* rating Number
* bankAccountId String
*
* */
@Path("drivers")
public class DriversInterface {

    MongoCollection<Document> collection;

    public DriversInterface() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("app17");

        this.collection = database.getCollection("drivers");
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public ArrayList<Driver> getAll() {

        ArrayList<Driver> driverList = new ArrayList<Driver>();

        FindIterable<Document> results = collection.find();
        if (results == null) {
            return  driverList;
        }
        for (Document item : results) {
            Driver driver = new Driver(
                    item.getString("firstName"),
                    item.getString("middleName"),
                    item.getString("lastName"),
                    item.getString("emailAddress"),
                    item.getString("password"),
                    item.getString("addressLineOne"),
                    item.getString("addressLineTwo"),
                    item.getString("addressCity"),
                    item.getString("addressState"),
                    item.getString("addressPostal"),
                    item.getString("drivingLicenseNumber"),
                    item.getString("licenseIssuedState"),
                    item.getDouble("rating"),
                    item.getString("bankAccountId")
            );
            driver.setId(item.getObjectId("_id").toString());
            driverList.add(driver);
        }
        return driverList;
    }
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Driver getOne(@PathParam("id") String id){
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));

        Document item = collection.find(query).first();
        if (item == null){
            return null;
        }
        Driver driver = new Driver(
                item.getString("firstName"),
                item.getString("middleName"),
                item.getString("lastName"),
                item.getString("emailAddress"),
                item.getString("password"),
                item.getString("addressLineOne"),
                item.getString("addressLineTwo"),
                item.getString("addressCity"),
                item.getString("addressState"),
                item.getString("addressPostal"),
                item.getString("drivingLicenseNumber"),
                item.getString("licenseIssuedState"),
                item.getDouble("rating"),
                item.getString("bankAccountId")
        );
        driver.setId(item.getObjectId("_id").toString());
        return driver;

    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Object create(JSONObject obj){
        try {
            Document doc = new Document("firstName", obj.getString("firstName"))
                    .append("middleName", obj.getString("middleName"))
                    .append("lastName", obj.getString("lastName"))
                    .append("emailAddress", obj.getString("emailAddress"))
                    .append("password", obj.getString("password"))
                    .append("addressLineOne", obj.getString("addressLineOne"))
                    .append("addressLineTwo", obj.getString("addressLineTwo"))
                    .append("addressCity", obj.getString("addressCity"))
                    .append("addressState",obj.getString("addressState"))
                    .append("addressPostal", obj.getString("addressPostal"))
                    .append("drivingLicenseNumber", obj.getString("drivingLicenseNumber"))
                    .append("licenseIssuedState", obj.getString("licenseIssuedState"))
                    .append("rating", obj.getDouble("rating"))
                    .append("bankAccountId",obj.getString("bankAccountId"));
            collection.insertOne(doc);

        } catch (JSONException e){
            System.out.println("Failed to create a document");
        }
        return obj;
    }
    @PATCH
    @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public Object update(@PathParam("id") String id, JSONObject obj) {
        try {

            BasicDBObject query = new BasicDBObject();
            query.put("_id", new ObjectId(id));

            Document doc = new Document();
            if (obj.has("firstName"))
                doc.append("firstName",obj.getString("firstName"));
            if (obj.has("middleName"))
                doc.append("middleName",obj.getString("middleName"));
            if (obj.has("lastName"))
                doc.append("lastName",obj.getString("lastName"));
            if (obj.has("emailAddress"))
                doc.append("emailAddress",obj.getString("emailAddress"));
            if (obj.has("password"))
                doc.append("password",obj.getString("password"));
            if (obj.has("addressLineOne"))
                doc.append("addressLineOne",obj.getString("addressLineOne"));
            if (obj.has("addressLineTwo"))
                doc.append("addressLineTwo",obj.getString("addressLineTwo"));
            if (obj.has("addressCity"))
                doc.append("addressCity",obj.getString("addressCity"));
            if (obj.has("addressState"))
                doc.append("addressState",obj.getString("addressState"));
            if (obj.has("addressPostal"))
                doc.append("addressPostal",obj.getString("addressPostal"));
            if (obj.has("drivingLicenseNumber"))
                doc.append("drivingLicenseNumber",obj.getString("drivingLicenseNumber"));
            if (obj.has("licenseIssuedState"))
                doc.append("licenseIssuedState",obj.getString("licenseIssuedState"));
            if (obj.has("rating"))
                doc.append("rating",obj.getDouble("rating"));
            if (obj.has("bankAccountId"))
                doc.append("bankAccountId",obj.getString("bankAccountId"));

            Document set = new Document("$set", doc);
            collection.updateOne(query,set);

        } catch(JSONException e) {
            System.out.println("Failed to update a document");

        }
        return obj;
    }


    @DELETE
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Object delete(@PathParam("id") String id) {
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));

        collection.deleteOne(query);

        return new JSONObject();
    }
}



