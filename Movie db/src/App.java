public class App {
    public static void main(String[] args) throws Exception {
        Model model = new Model("MovieDatabase", "Movie");
        model.FetchAll();
        model.fetchOne("Titanic");

    }
}
