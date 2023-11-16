package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
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
    private static List<Product> productList;
    
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


        reqID = br.readLine().trim().split(":")[1];
        itemCount = Integer.parseInt(br.readLine().trim().split(":")[1]);
        budget = Double.parseDouble(br.readLine().trim().split(":")[1]);

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
                
                case "prod_id":
                    productList = new LinkedList<>();
                    productMap.put(Integer.parseInt(terms[1]), productList);
                    break;
                
                case "prod_title":
                    // productList.add(terms[1]);
                    
            
                default:
                    break;
            }


        }


        //productList.sort(Comparator
        //               .comparingDouble(Product :: getRating).reversed()
        //               .thencomparingInt(Product :: getPrice).reversed();

        //int max = 0;
        //for(Product p : productList){
            // max = p.getRating();

        




        // bw.write("request_id: " + reqID + "\n");
        // bw.write("name: sherylyn\n");
        // bw.write("email: shersica1998@gmail.com\n");
        // bw.write("items: ");
        // bw.write("spent: ");
        // bw.write("remaining: ");
        // bw.write("client_end\n");

        // bw.flush();
        

        
    }
        

}
