import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.json.JSONException;

public class Mongo_db {
    String connectionString;
    ServerApi serverApi;
    MongoClientSettings settings;
    MongoDatabase database;
    MongoCollection<Document> collection;

    Mongo_db() {
        this.connectionString = "mongodb+srv://Sumukh:sumukh@cluster0.cpudzkw.mongodb.net/?retryWrites=true&w=majority";
        this.serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        this.settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
    }


    void connect(){
        try(MongoClient mongoClient = MongoClients.create(settings)){
            database = mongoClient.getDatabase("MovieDatabase");
            collection = database.getCollection("Movies");

        }
    }

    HashMap<String,ArrayList<String>> getall(){
        FindIterable<Document> iterDoc = collection.find();

        HashMap<String,ArrayList<String>>details=new HashMap<>();
        for(Document doc:iterDoc){
            String id=doc.get("_id").toString();
            String title=doc.get("Title").toString();
            String genre=doc.get("Genre").toString();
            String year=doc.get("ReleaseYear").toString();
            String rating=doc.get("Rating").toString();

            ArrayList<String>temp=new ArrayList<>();
            temp.add(title);
            temp.add(genre);
            temp.add(year);
            temp.add(rating);
            details.put(id, temp);

        }
        return details;
    }


}
