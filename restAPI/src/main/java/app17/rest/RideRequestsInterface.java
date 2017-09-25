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
* startLat Number
* startLon Number
* endLat Number
* endLon Number
* passengerId String
* driverID String
* */
@Path("rideRequests")
public class RideRequestsInterface {

    MongoCollection<Document> collection;

    public RideRequestsInterface() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("app17");

        MongoCollection<Document> collection = database.getCollection("rideRequests");
        this.collection = collection;

    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public ArrayList<RideRequest> getAll() {

        ArrayList<RideRequest> rideRequestList = new ArrayList<RideRequest>();

        FindIterable<Document> results = collection.find();
        if (results == null) {
            return  rideRequestList;
        }
        for (Document item : results) {
            RideRequest rideRequest = new RideRequest(
                    item.getDouble("startLat"),
                    item.getDouble("startLon"),
                    item.getDouble("endLat"),
                    item.getDouble("endLon"),
                    item.getString("passengerId"),
                    item.getString("driverId")
            );

            rideRequest.setId(item.getObjectId("_id").toString());
            rideRequestList.add(rideRequest);
        }
        return rideRequestList;

    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public RideRequest getOne(@PathParam("id") String id) {


        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));

        Document item = collection.find(query).first();
        if (item == null) {
            return  null;
        }
        RideRequest rideRequest = new RideRequest(
                item.getDouble("startLat"),
                item.getDouble("startLon"),
                item.getDouble("endLat"),
                item.getDouble("endLon"),
                item.getString("passengerId"),
                item.getString("driverId")
        );
        rideRequest.setId(item.getObjectId("_id").toString());
        return rideRequest;

    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public Object create(JSONObject obj) {
        try {
            Document doc = new Document("startLat",obj.getDouble("startLat"))
                    .append("startLon", obj.getDouble("startLon"))
                    .append("endLat", obj.getDouble("endLat"))
                    .append("endLon", obj.getDouble("endLon"))
                    .append("passengerId",obj.getString("passengerId"))
                    .append("driverId",obj.getString("driverId"));

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
            if (obj.has("startLat"))
                doc.append("startLat",obj.getDouble("startLat"));
            if (obj.has("startLon"))
                doc.append("startLon",obj.getDouble("startLon"));
            if (obj.has("endLat"))
                doc.append("endLat",obj.getDouble("endLat"));
            if (obj.has("endLon"))
                doc.append("endLon",obj.getDouble("endLon"));
            if (obj.has("passengerId"))
                doc.append("passengerId",obj.getString("passengerId"));
            if (obj.has("driverId"))
                doc.append("driverId",obj.getString("driverId"));
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
