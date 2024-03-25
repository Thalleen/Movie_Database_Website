import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

import com.mongodb.client.model.Updates;
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

    void connect() {
        try {
            database = mongoClient.getDatabase("MovieDatabase");
            collection = database.getCollection("Movies");
        } catch (Exception e) {
            System.out.println("Error connecting to Mongo");
        }
    }

    HashMap<String, ArrayList<String>> getall() {
        FindIterable<Document> iterDoc = collection.find();
        System.out.println("Retrieved");
        HashMap<String, ArrayList<String>> details = new HashMap<>();
        for (Document doc : iterDoc) {
            // String id = doc.get("_id").toString();
            String title = doc.get("Title").toString();
            String genre = doc.get("Genre").toString();
            String year = doc.get("ReleaseYear").toString();
            String rating = doc.get("Rating").toString();

            ArrayList<String> temp = new ArrayList<>();
            // temp.add(title);
            temp.add(genre);
            temp.add(year);
            temp.add(rating);
            details.put(title, temp);

        }
        return details;
    }

    HashMap<String, ArrayList<String>> getone(String movie) {
        FindIterable<Document> iterDoc = collection.find();
        System.out.println("Retrieved");
        HashMap<String, ArrayList<String>> details = new HashMap<>();
        for (Document doc : iterDoc) {
            // String id = doc.get("_id").toString();
            String title = doc.get("Title").toString();
            String genre = doc.get("Genre").toString();
            String year = doc.get("ReleaseYear").toString();
            String rating = doc.get("Rating").toString();

            if (title.equals(movie)) {
                ArrayList<String> temp = new ArrayList<>();
                // temp.add(title);
                temp.add(genre);
                temp.add(year);
                temp.add(rating);
                details.put(title, temp);
            }

        }
        return details;
    }

    void writeMovie(JSONObject json) throws Exception {
        FindIterable<Document> iterDoc = collection.find();
        System.out.println("Retrieved");

        for (Document doc : iterDoc) {
            if (doc.get("Title").equals(json.get("Movie"))) {
                Bson combinedUpdates = Updates.combine(
                        Updates.set("ReleaseYear", json.get("ReleaseYear")),
                        Updates.set("Genre", json.get("Genre")),
                        Updates.set("Rating", json.get("Rating")));

                collection.updateOne(
                        Filters.eq("Title", json.get("Movie")),
                        combinedUpdates);

                System.out.println("Document updated successfully");
                return;
            }

        }

        Document document = new Document();
        document.append("Title", json.get("Movie"));
        document.append("ReleaseYear", json.get("ReleaseYear"));
        document.append("Genre", json.get("Genre"));
        document.append("Rating", json.get("Rating"));

        collection.insertOne(document);
        System.out.println("Succesful insertion");

    }

    void delete(String movie) throws Exception {
        collection.deleteOne(Filters.eq("Title", movie));
        System.out.println("Delete succeesful");
    }

}
