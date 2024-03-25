import java.util.*;

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
        System.out.println("Fetching all models");
        HashMap<String, ArrayList<String>> ret = mongo_db.getall();
        // System.out.println("Finished fetching all models");
        for (Map.Entry<String, ArrayList<String>> entry : ret.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> values = entry.getValue();
            System.out.println("Key" + key);
        }
    }
}