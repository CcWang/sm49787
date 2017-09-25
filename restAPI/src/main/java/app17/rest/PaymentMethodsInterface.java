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
* cardNum Number;
* cardType String;
* expDate Date;
* secCode Number;
* cardHolderName String;
*
* */
@Path("paymentMethods")
public class PaymentMethodsInterface {

    MongoCollection<Document> collection;

    public PaymentMethodsInterface() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("app17");

        MongoCollection<Document> collection = database.getCollection("paymentMethods");
        this.collection = collection;

    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public ArrayList<PaymentMethod> getAll() {

        ArrayList<PaymentMethod> paymentMethodList = new ArrayList<PaymentMethod>();

        FindIterable<Document> results = collection.find();
        if (results == null) {
            return  paymentMethodList;
        }
        for (Document item : results) {
            PaymentMethod pm = new PaymentMethod(
                    item.getInteger("cardNum",-1),
                    item.getString("cardType"),
                    item.getDate("expDate"),
                    item.getInteger("secCode", -1),
                    item.getString("cardHolderName")
            );

            pm.setId(item.getObjectId("_id").toString());
            paymentMethodList.add(pm);
        }
        return paymentMethodList;

    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public PaymentMethod getOne(@PathParam("id") String id) {


        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));

        Document item = collection.find(query).first();
        if (item == null) {
            return  null;
        }
        PaymentMethod pm = new PaymentMethod(
                item.getInteger("cardNum",-1),
                item.getString("cardType"),
                item.getDate("expDate"),
                item.getInteger("secCode", -1),
                item.getString("cardHolderName")
        );
        pm.setId(item.getObjectId("_id").toString());
        return pm;

    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public Object create(JSONObject obj) {
        try {
            Document doc = new Document("cardNum",obj.getInt("cardNum"))
                    .append("cardType", obj.getString("cardType"))
                    .append("expDate",obj.getString("expDate"))
                    .append("secCode", obj.getInt("secCode"))
                    .append("cardHolderName",obj.getString("cardHolderName"));

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
            if (obj.has("cardNum"))
                doc.append("cardNum",obj.getInt("cardNum"));
            if (obj.has("cardType"))
                doc.append("cardType",obj.getString("cardType"));
            if (obj.has("expDate"))
                doc.append("expDate",obj.getString("expDate"));
            if (obj.has("secCode"))
                doc.append("secCode",obj.getInt("secCode"));
            if (obj.has("cardHolderName"))
                doc.append("cardHolderName",obj.getString("cardHolderName"));
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
