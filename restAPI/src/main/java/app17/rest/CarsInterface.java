package app17.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;

/*
* make string
* model string
* year int
* size string
* licensePlate string
* licensedState string
* vin string
* odometer int
* currentInsurer string
* purchasedYear int
* ownerNameInTitle string
* everBeenInAccident boolean
* */
@Path("cars")
public class CarsInterface {

    MongoCollection<Document> collection;

    public CarsInterface() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("app17");

        MongoCollection<Document> collection = database.getCollection("cars");
        this.collection = collection;

    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public ArrayList<Car> getAll() {

        ArrayList<Car> carList = new ArrayList<Car>();

        FindIterable<Document> results = collection.find();
        if (results == null) {
            return  carList;
        }
        for (Document item : results) {
            Car car = new Car(
                    item.getString("make"),
                    item.getString("model"),
                    item.getInteger("year", -1),
                    item.getString("size"),
                    item.getString("licensePlate"),
                    item.getString("licensedState"),
                    item.getString("vin"),
                    item.getInteger("odometer"),
                    item.getString("currentInsurer"),
                    item.getInteger("purchasedYear"),
                    item.getString("ownerNameInTitle"),
                    item.getBoolean("everBeenInAccident")
            );
            car.setId(item.getObjectId("_id").toString());
            carList.add(car);
        }
        return carList;

    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Car getOne(@PathParam("id") String id) {


        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));

        Document item = collection.find(query).first();
        if (item == null) {
            return  null;
        }
        Car car = new Car(
                item.getString("make"),
                item.getString("model"),
                item.getInteger("year", -1),
                item.getString("size"),
                item.getString("licensePlate"),
                item.getString("licensedState"),
                item.getString("vin"),
                item.getInteger("odometer"),
                item.getString("currentInsurer"),
                item.getInteger("purchasedYear"),
                item.getString("ownerNameInTitle"),
                item.getBoolean("everBeenInAccident")
        );
        car.setId(item.getObjectId("_id").toString());
        return car;

    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public Object create(JSONObject obj) {
        try {
            Document doc = new Document("make",obj.getString("make"))
                    .append("model", obj.getString("model"))
                    .append("year", obj.getInt("year"))
                    .append("size", obj.getString("size"))
                    .append("licensePlate",obj.getString("licensePlate"))
                    .append("licensedState", obj.getString("licensedState"))
                    .append("vin", obj.getString("vin"))
                    .append("odometer", obj.getInt("odometer"))
                    .append("currentInsurer",obj.getString("currentInsurer"))
                    .append("purchasedYear", obj.getInt("purchasedYear"))
                    .append("ownerNameInTitle",obj.getString("ownerNameInTitle"))
                    .append("everBeenInAccident", obj.getBoolean("everBeenInAccident"));
            collection.insertOne(doc);


        } catch(JSONException e) {
            System.out.println("Failed to create a document");
        }
//        maybe return the obj id
//        lot of post just simply return "success"
//        return 200
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
            if (obj.has("make"))
                doc.append("make",obj.getString("make"));
            if (obj.has("model"))
                doc.append("model",obj.getString("model"));
            if (obj.has("year"))
                doc.append("year",obj.getInt("year"));
            if (obj.has("size"))
                doc.append("size",obj.getString("size"));
            if (obj.has("licensePlate"))
                doc.append("licensePlate",obj.getString("licensePlate"));
            if (obj.has("licensedState"))
                doc.append("licensedState",obj.getString("licensedState"));
            if (obj.has("vin"))
                doc.append("vin",obj.getString("vin"));
            if (obj.has("odometer"))
                doc.append("odometer",obj.getInt("odometer"));
            if (obj.has("currentInsurer"))
                doc.append("currentInsurer",obj.getString("currentInsurer"));
            if (obj.has("purchasedYear"))
                doc.append("purchasedYear",obj.getInt("purchasedYear"));
            if (obj.has("ownerNameInTitle"))
                doc.append("ownerNameInTitle",obj.getString("ownerNameInTitle"));
            if (obj.has("everBeenInAccident"))
                doc.append("everBeenInAccident",obj.getBoolean("everBeenInAccident"));

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
