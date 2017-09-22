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
                    item.getString("lastName"),
                    item.getString("phoneNumber"),
                    item.getString("address1"),
                    item.getString("address2"),
                    item.getString("stateCode"),
                    item.getString("countryCode")
            );
            driver.setId(item.getObjectId("_id").toString());
            driverList.add(driver);
        }
        return driverList;
    }

}
