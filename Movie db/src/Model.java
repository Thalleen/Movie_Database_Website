
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.util.*;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import org.json.JSONException;
import org.json.JSONObject;

public class Model {
    String DatabaseName;
    String CollectionName;
    Mongo_db mongo_db = new Mongo_db();


    Model(String DatabaseName, String CollectionName) {
        this.DatabaseName = DatabaseName;
        this.CollectionName = CollectionName;
        mongo_db.connect();
    }
    
    public void FetchAll() {
        
        HashMap<String, ArrayList<String>> ret = mongo_db.getall();
        for (Map.Entry<String, ArrayList<String>> entry : ret.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> values = entry.getValue();
            System.out.println("Key" + key);
        }
    }

    public void fetchOne(String movie){
        HashMap<String, ArrayList<String>> ret = mongo_db.getone(movie);
        for (Map.Entry<String, ArrayList<String>> entry : ret.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> values = entry.getValue();
            System.out.println("Key" + key);
        }
    }

    public void writeMovie(String movie,String rating, String year) throws Exception{
        try {
            JSONObject obj = new JSONObject();
            obj.put("Movie", movie);
            obj.put("Rating", rating);
            obj.put("ReleaseYear", year);
            mongo_db.writeMovie(obj);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exeception in writing ");
        }

    }
}