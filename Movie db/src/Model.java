import java.util.*;
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

    public ArrayList<ArrayList<String>> FetchAll() {
        ArrayList<ArrayList<String>> all_movies = new ArrayList<>();
        HashMap<String, ArrayList<String>> ret = mongo_db.getall();
        for (Map.Entry<String, ArrayList<String>> entry : ret.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> values = entry.getValue();
            ArrayList<String> temp = new ArrayList<>();

            temp.add(key);
            for (String i : values) {
                temp.add(i);
            }

            all_movies.add(temp);
        }
        return all_movies;

    }

    public ArrayList<ArrayList<String>> fetchOne(String movie) {
        ArrayList<ArrayList<String>> all_movies = new ArrayList<>();
        HashMap<String, ArrayList<String>> ret = mongo_db.getone(movie);
        for (Map.Entry<String, ArrayList<String>> entry : ret.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> values = entry.getValue();
            ArrayList<String> temp = new ArrayList<>();

            temp.add(key);
            for (String i : values) {
                temp.add(i);
            }

            all_movies.add(temp);
        }
        return all_movies;
    }

    public String writeMovie(String movie, String rating, String year, String genre) throws Exception {
        try {
            JSONObject obj = new JSONObject();
            obj.put("Movie", movie);
            obj.put("Rating", rating);
            obj.put("ReleaseYear", year);
            obj.put("Genre", genre);
            mongo_db.writeMovie(obj);
            return "Successful";
        } catch (Exception e) {
            System.out.println("Exeception in writing " + e.getMessage());
            return "Error";
        }

    }

    public void deleteRecord(String movie) throws Exception {
        mongo_db.delete(movie);
    }

}