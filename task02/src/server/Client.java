package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Client {
    
    public static int PORT;
    public static String HOST;
    private static String reqID;
    private static double budget;
    private static int itemCount;
    private static String title;
    private static int id;
    private static double price;
    private static double rating;
    private static Map<Integer, List<Product>> productMap = new HashMap<>();
    private static List<Product> productList = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {
        // if (args.length < 0) {
        //     PORT = 3000;
        //     HOST = "localhost";
        // }
        // else if(args.length == 1){
        //     PORT = Integer.parseInt(args[0]);
        //     HOST = "localhost";
        // }
        // else if(args.length == 2){
        //     PORT = Integer.parseInt(args[1]);
        //     HOST = args[0];
        // }
        // else{
        //     System.out.println("Invalid input");
        // }

        Socket socket = new Socket("localhost", 3000);
        System.out.println("Established connection");

        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter ows = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(ows);

        String line;

        while((line = br.readLine()) != null){
            line = line.trim(); //replaceALL
            if(line.isEmpty()){
                continue;
            }
            String [] terms = line.split(":");
            // System.out.println(line);
            switch (terms[0]) {
                case "request_id":
                    reqID = terms[1];
                    break;
                
                case "budget":
                    budget = Double.parseDouble(terms[1]);
                    break;
                case "prod_list":
                case "prod_start":
                    break;
                
                case "prod_id":
                    id = Integer.parseInt(terms[1]);
                    break;
                
                case "title":
                    title = terms[1];
                    break;

                case "price":
                    price = Double.parseDouble(terms[1]);
                    break;

                case "rating":
                    rating = Double.parseDouble(terms[1]);
                    break;

                case "prod_end":
                    productList.add(new Product(title, id, price, rating));                
                    break;
                
                default:
                    break;
            }

        }

        for(Product p : productList){
            System.out.println(p.getTitle());
        }


        productList.sort(Comparator
                      .comparingDouble(Product :: getRating).reversed()
                      .thenComparingDouble(Product :: getPrice).reversed());

        
        List<Product> selectedList = new ArrayList<>();
        for(Product p : productList){
            if(p.getPrice() > budget){
                continue;
            } 
            else{
                selectedList.add(p);
                budget -= p.getPrice();
            }
        }



        




        // bw.write("request_id: " + reqID + "\n");
        // bw.write("name: sherylyn\n");
        // bw.write("email: shersica1998@gmail.com\n");
        // for(Product p : selectedList){
        //     System.out.printf("%d ,\n", p.getId());
        // }
        // bw.write("items: ");
        // bw.write("spent: ");
        // bw.write("remaining: ");
        // bw.write("client_end\n");

        // bw.flush();
        

        
    }
        

}
