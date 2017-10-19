package edu.cmu.sv.app17.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.cmu.sv.app17.exceptions.APPBadRequestException;
import edu.cmu.sv.app17.exceptions.APPInternalServerException;
import edu.cmu.sv.app17.exceptions.APPNotFoundException;
import edu.cmu.sv.app17.exceptions.APPUnauthorizedException;
import edu.cmu.sv.app17.helpers.APPCrypt;
import edu.cmu.sv.app17.helpers.APPListResponse;
import edu.cmu.sv.app17.helpers.APPResponse;
import edu.cmu.sv.app17.models.Car;
import edu.cmu.sv.app17.models.Driver;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;


import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("drivers")
public class DriversInterface {

    private MongoCollection<Document> driverCollection;
    private MongoCollection<Document> carCollection;
    private ObjectWriter ow;


    public DriversInterface() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("app17-7");

        this.driverCollection = database.getCollection("drivers");
        this.carCollection = database.getCollection("cars");
        ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public APPResponse getAll() {

        ArrayList<Driver> driverList = new ArrayList<Driver>();

        FindIterable<Document> results = driverCollection.find();
        if (results == null) {
            return new APPResponse(driverList);
        }
        for (Document item : results) {
            Driver driver = new Driver(
                    item.getString("firstName"),
                    item.getString("lastName"),
                    item.getString("emailAddress")
            );
            driver.setId(item.getObjectId("_id").toString());
            driverList.add(driver);
        }
        return new APPResponse(driverList);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public APPResponse getOne(@Context HttpHeaders headers, @PathParam("id") String id) {
        try {
            checkAuthentication(headers,id);
            BasicDBObject query = new BasicDBObject();

            query.put("_id", new ObjectId(id));
            Document item = driverCollection.find(query).first();
            if (item == null) {
                throw new APPNotFoundException(0, "No such car, my friend");
            }
            Driver driver = new Driver(
                    item.getString("firstName"),
                    item.getString("lastName"),
                    item.getString("emailAddress")
            );
            driver.setId(item.getObjectId("_id").toString());
            return new APPResponse(driver);

        }
        catch(APPNotFoundException e) {
            throw e;
        }
        catch(APPUnauthorizedException e) {
            throw e;
        }
        catch(IllegalArgumentException e) {
            throw new APPBadRequestException(45,"Doesn't look like MongoDB ID");
        }
        catch(Exception e) {
            throw new APPInternalServerException(99,"Unexpected error");
        }


    }


    @POST
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public APPResponse create( Object request) {
        JSONObject json = null;
        try {
            json = new JSONObject(ow.writeValueAsString(request));
            if (!json.has("firstName"))
                throw new APPBadRequestException(55, "missing firstName");
            if (!json.has("lastName"))
                throw new APPBadRequestException(55, "missing lastName");
            if (!json.has("emailAddress"))
                throw new APPBadRequestException(55, "missing emailAddress");
            if (!json.has("password"))
                throw new APPBadRequestException(55, "missing password");
            Document doc = new Document("firstName", json.getString("firstName"))
                    .append("lastName", json.getString("lastName"))
                    .append("emailAddress", json.getString("emailAddress"))
                    .append("password", APPCrypt.encrypt(json.getString("password")));
            driverCollection.insertOne(doc);
            return new APPResponse(request);
        }
        catch (JsonProcessingException e) {
            throw new APPBadRequestException(33, e.getMessage());
        }
        catch (APPBadRequestException e) {
            throw new APPBadRequestException(33, e.getMessage());
        }
        catch (Exception e) {
            throw new APPInternalServerException(0, e.getMessage());
        }
    }


    @GET
    @Path("{id}/cars")
    @Produces({MediaType.APPLICATION_JSON})
    public APPListResponse getCarsForDriver(@Context HttpHeaders headers, @PathParam("id") String id,
                                        @DefaultValue("20") @QueryParam("count") int count,
                                        @DefaultValue("0") @QueryParam("offset") int offset
    ) {

        ArrayList<Car> carList = new ArrayList<Car>();

        try {
//            checkAuthentication(headers,id);
            BasicDBObject query = new BasicDBObject();
            query.put("driverId", id);

            long resultCount = carCollection.count(query);
            FindIterable<Document> results = carCollection.find(query).skip(offset).limit(count);
            for (Document item : results) {
                String make = item.getString("make");
                Car car = new Car(
                        make,
                        item.getString("model"),
                        item.getInteger("year", -1),
                        item.getString("size"),
                        item.getString("color"),
                        item.getInteger("odometer"),
                        item.getString("driverId")
                );
                car.setId(item.getObjectId("_id").toString());
                carList.add(car);
            }
            return new APPListResponse(carList,resultCount,offset,carList.size());

        }
        catch(APPBadRequestException e) {
            throw e;
        }
        catch(APPUnauthorizedException e) {
            throw e;
        }
        catch(Exception e) {
            System.out.println("EXCEPTION!!!!");
            e.printStackTrace();
            throw new APPInternalServerException(99,e.getMessage());
        }

    }


    @POST
    @Path("{id}/cars")
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public APPResponse createCar(@PathParam("id") String id, Object request) {
        JSONObject json = null;
        try {
            json = new JSONObject(ow.writeValueAsString(request));
        }
        catch (JsonProcessingException e) {
            throw new APPBadRequestException(33, e.getMessage());
        }
        if (!json.has("make"))
            throw new APPBadRequestException(55,"missing make");
        if (!json.has("model"))
            throw new APPBadRequestException(55,"missing model");
        if (!json.has("color"))
            throw new APPBadRequestException(55,"missing color");
        if (!json.has("year"))
            throw new APPBadRequestException(55,"missing year");
        if (!json.has("size"))
            throw new APPBadRequestException(55,"missing size");
        if (!json.has("odometer"))
            throw new APPBadRequestException(55,"missing odometer");
        if (json.getInt("odometer") < 0) {
            throw new APPBadRequestException(56, "Invalid odometer - cannot be less than 0");
        }
        Document doc = new Document("make", json.getString("make"))
                .append("model", json.getString("model"))
                .append("size", json.getString("size"))
                .append("color", json.getString("color"))
                .append("year", json.getInt("year"))
                .append("odometer", json.getInt("odometer"))
                .append("driverId", id);
        carCollection.insertOne(doc);
        return new APPResponse();
    }

    void checkAuthentication(HttpHeaders headers,String id) throws Exception{
        List<String> authHeaders = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
        if (authHeaders == null)
            throw new APPUnauthorizedException(70,"No Authorization Headers");
        String token = authHeaders.get(0);
        String clearToken = APPCrypt.decrypt(token);
        if (id.compareTo(clearToken) != 0) {
            throw new APPUnauthorizedException(71,"Invalid token. Please try getting a new token");
        }
    }
}
