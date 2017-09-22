package app17.rest;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("version")
public class VersionInterface {


    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public JSONObject getAll() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("version", "0.1.1");
            obj.put("date", "2017-09-20");
        }
        catch(Exception e) {
            System.out.println("Could not set version");
        }
        return obj;
    }

}
