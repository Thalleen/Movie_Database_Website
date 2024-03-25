import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.*;
import java.util.*;

public class Server {
    int portnumber;
    Model model;

    Server(int portnumber) {
        this.portnumber = portnumber;
        this.model = new Model("MovieDatabase", "Movies");
    }

    void start() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", this.portnumber), 0);
        server.createContext("/", (exchange -> {
            if ("GET".equals(exchange.getRequestMethod())) {
                String response = model.FetchAll().toString();
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }));
        server.createContext("/write", (exchange -> {
            if ("POST".equals(exchange.getRequestMethod())) {
                InputStream requestBody = exchange.getRequestBody();
                StringBuilder requestBodyString = new StringBuilder();
                int i;
                while ((i = requestBody.read()) != -1) {
                    requestBodyString.append((char) i);
                }
                requestBody.close();
                System.out.println(requestBodyString.toString());
                ArrayList<String> list = parseList(requestBodyString.toString());
                String response ;
                try{
                    response = model.writeMovie(list.get(0), list.get(1), list.get(2), list.get(3));
                }
                catch (Exception e) {
                    response = "Error";
                }
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }));
        server.createContext("/delete",(exchange->{
            if ("POST".equals(exchange.getRequestMethod())) {
                 InputStream requestBody = exchange.getRequestBody();
                StringBuilder requestBodyString = new StringBuilder();
                int i;
                while ((i = requestBody.read()) != -1) {
                    requestBodyString.append((char) i);
                }
                requestBody.close();
                String response ;
                try {
                    model.deleteRecord(requestBodyString.toString());
                    response = "Success";
                }
                catch(Exception e){
                    response = "Error";
                }
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }));
        server.createContext("/update",(exchange->{
            if("POST".equals(exchange.getRequestMethod())){
                InputStream requestBody = exchange.getRequestBody();
                StringBuilder requestBodyString = new StringBuilder();
                int i;
                while ((i = requestBody.read()) != -1) {
                    requestBodyString.append((char) i);
                }
                requestBody.close();
                String[] data = requestBodyString.toString().split(":");
                ArrayList<String> list = new ArrayList<>();
                list.add(data[0]);
                ArrayList<String> list2 = parseList(data[1]);
                list.addAll(list2);
                String response ;
                try {
                     model.deleteRecord(requestBodyString.toString());
                    response = "Success";
                }
                catch (Exception e) {
                    response = "Error";
                }
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }));

        server.start();
        System.out.println("Server running on port " + this.portnumber);
    }
    private ArrayList<String> parseList(String input){
        String trimmedInput = input.substring(1, input.length() - 1);
        ArrayList<String> ret = new ArrayList<>();
        String[] languages = trimmedInput.split(", ");
        for (String language : languages) {
            ret.add(language);
        }
        return ret;
    }
}
