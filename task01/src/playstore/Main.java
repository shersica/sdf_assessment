package playstore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

public class Main {

    public static final int COL_NAME = 0;
    public static final int COL_CATEGORY = 1;
    public static final int COL_RATING = 2;
    private static Map<String, List<App>> appByCategory = new HashMap<>();
    private static List<App> apps;

    public static void main(String[] args) throws Exception {
        
        if(args.length <= 0){
            System.out.println("Error, no file name provided");
            System.exit(1);
        }

        try(FileReader fr = new FileReader(args[0])){
            BufferedReader br = new BufferedReader(fr);
            
            // Map<String, List<App>> categorized = br.lines()
            //     .skip(1)
            //     .map(line -> line.trim().split(","))   
            //     // .map(columns -> columns[COL_CATEGORY].toLowerCase())    //havent change category name to lowercase
            //     // .map(columns -> new App(columns[COL_NAME], columns[COL_CATEGORY], Double.parseDouble(columns[COL_RATING])))
            //     // .collect(Collector.groupingby(app -> app.getCategory()));
            //     // .collect(Collector.groupingBy(columns -> columns[COL_CATEGORY]))
            //     // .toList();

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String [] columns = line.trim().split(",");
                columns[COL_CATEGORY].toUpperCase();

                App app = new App(columns[COL_NAME], columns[COL_CATEGORY], (columns[COL_RATING]));

                String category = app.getCategory();
                if(!appByCategory.containsKey(category)){ 
                    apps = new LinkedList<>();
                    appByCategory.put(category, apps);
                    apps.add(app);
                }

                else{
                    apps = appByCategory.get(category);
                    apps.add(app);
                }

            }
        
            for(String cat : appByCategory.keySet()){                                   //Apps sorted by category unprocessed
                System.out.printf("Category: %s\n", cat);
                for(App app : apps){
                    String name = app.getName();
                    String rating = app.getRating();
                    System.out.printf("Name: %s, Rating: %s\n", name, rating);
                }
            }

            Map<String, List<App>> invalidRecords = new HashMap<>();
            Map<String,List<App>> validRecords = new HashMap<>();
            for(String cat : appByCategory.keySet()){
                // System.out.printf("Category: %s\n", cat);
                for(App app : apps){
                    if(app.getRating().equals("NaN")){
                        apps = new LinkedList<>();
                        invalidRecords.put(cat, apps);
                        apps.add(app);
                    }
                    else{
                        apps = new LinkedList<>();
                        validRecords.put(cat, apps);
                        apps.add(app);
                    }
                }
            }

            int invalidCount = 0;  //discarded
            for(String cat : invalidRecords.keySet()){
                System.out.printf("Category: %s\n", cat);
                for(App app : apps){
                    invalidCount += 1;
                }
            }

            int validCount = 0; //count
            for(String cat : invalidRecords.keySet()){
                System.out.printf("Category: %s\n", cat);
                for(App app : apps){
                    validCount += 1;
                }
            }

            double highest = 0;
            double lowest = 10; //given a number higher than max rating
            double sum = 0;
            for(String cat : validRecords.keySet()){
                for(App app : apps){
                    if (Float.parseFloat(app.getRating()) > highest){
                        highest = Float.parseFloat(app.getRating());
                    }
                    if(Float.parseFloat(app.getRating()) < lowest){
                        lowest = Float.parseFloat(app.getRating());
                    }
                    sum += Float.parseFloat(app.getRating());
                }
            }
            
            System.out.printf("Highest: %f", highest);
            System.out.printf("Lowest: %f", lowest);










        }
    }
}