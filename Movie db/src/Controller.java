public class Controller {
    public static void main(String[] args) throws Exception {
        Model model = new Model("storage", "Collection");
        model.FetchAll();
        Server server = new Server(8080);
        server.start();
    }
}
