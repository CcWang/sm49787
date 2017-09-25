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
* bankName String
* accountNum Number
* routingNum Number
* accHolderName String
* verified Boolean
* */
@Path("bankAccounts")
public class BankAccountsInterface {

    MongoCollection<Document> collection;

    public BankAccountsInterface() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("app17");

        MongoCollection<Document> collection = database.getCollection("bankAccounts");
        this.collection = collection;

    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public ArrayList<BankAccount> getAll() {

        ArrayList<BankAccount> bankAccountList = new ArrayList<BankAccount>();

        FindIterable<Document> results = collection.find();
        if (results == null) {
            return  bankAccountList;
        }
        for (Document item : results) {
            BankAccount bankAccount = new BankAccount(
                    item.getString("bankName"),
                    item.getDouble("accountNum"),
                    item.getDouble("routingNum"),
                    item.getString("accHolderName"),
                    item.getBoolean("verified")
            );

            bankAccount.setID(item.getObjectId("_id").toString());
            bankAccountList.add(bankAccount);
        }
        return bankAccountList;

    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public BankAccount getOne(@PathParam("id") String id) {


        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));

        Document item = collection.find(query).first();
        if (item == null) {
            return  null;
        }
        BankAccount ba = new BankAccount(
                item.getString("bankName"),
                item.getDouble("accountNum"),
                item.getDouble("routingNum"),
                item.getString("accHolderName"),
                item.getBoolean("verified")
        );
        ba.setID(item.getObjectId("_id").toString());
        return ba;

    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public Object create(JSONObject obj) {
        try {
            Document doc = new Document("bankName",obj.getString("bankName"))
                    .append("accountNum", obj.getDouble("accountNum"))
                    .append("routingNum", obj.getDouble("routingNum"))
                    .append("accHolderName",obj.getString("accHolderName"))
                    .append("verified", obj.getBoolean("verified"));
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
            if (obj.has("bankName"))
                doc.append("bankName",obj.getString("bankName"));
            if (obj.has("accountNum"))
                doc.append("accountNum",obj.getDouble("accountNum"));
            if (obj.has("routingNum"))
                doc.append("routingNum",obj.getDouble("routingNum"));
            if (obj.has("accHolderName"))
                doc.append("accHolderName",obj.getString("accHolderName"));
            if (obj.has("verified"))
                doc.append("verified",obj.getBoolean("verified"));

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
