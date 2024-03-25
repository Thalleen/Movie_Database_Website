import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import java.util.ArrayList;
import java.util.HashMap;
import org.bson.Document;
import org.json.JSONObject;

public class Mongo_db {
    String connectionString;
    ServerApi serverApi;
    MongoClientSettings settings;
    MongoDatabase database;
    MongoCollection<Document> collection;
    MongoClient mongoClient;

    Mongo_db() {
        this.connectionString = "mongodb+srv://Sumukh:sumukh@cluster0.cpudzkw.mongodb.net/?retryWrites=true&w=majority";
        this.serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        this.settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        mongoClient = MongoClients.create(settings);
    }


    void connect(){
        try{
            database = mongoClient.getDatabase("MovieDatabase");
            collection = database.getCollection("Movies");
        }
        catch (Exception e){
            System.out.println("Error connecting to Mongo");
        }
    }

    HashMap<String, ArrayList<String>> getall() {
        FindIterable<Document> iterDoc = collection.find();
        System.out.println("Retrieved");
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

    HashMap<String, ArrayList<String>> getone(String movie){
        FindIterable<Document> iterDoc = collection.find();
        System.out.println("Retrieved");
        HashMap<String,ArrayList<String>>details=new HashMap<>();
        for(Document doc:iterDoc){
            String id=doc.get("_id").toString();
            String title=doc.get("Title").toString();
            String genre=doc.get("Genre").toString();
            String year=doc.get("ReleaseYear").toString();
            String rating=doc.get("Rating").toString();


            if(title.equals(movie)){
                ArrayList<String>temp=new ArrayList<>();
                temp.add(title);
                temp.add(genre);
                temp.add(year);
                temp.add(rating);
                details.put(id, temp);
            }
            
        }
        return details;
    }


    void writeMovie(JSONObject json){
        Document document=new Document();
        
    }


}
