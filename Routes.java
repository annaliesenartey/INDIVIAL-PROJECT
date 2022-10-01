

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Routes {
    /**
	 * Constructor: 
	 * Build and initialise objects of this class 
	 * @param airline the 2-letter (IATA) or 3-letter (ICAO) code of the airline.
	 * @param airline_id the unique OpenFlights identifier for airline
	 * @param source_airport the Unique OpenFlights identifier for source airport 
	 * @param sourceAir_id the Unique OpenFlights identifier for source airport
	 * @param  destinAir the 3-letter (IATA) or 4-letter (ICAO) code of the destination airport
	 * @param stops the number of stops on this flight ("0" for direct)
	 */
    public Routes(String airline, String airline_id, String source_airport, String sourceAir_id, String destinAir, String stops) {
        this.airline = airline;
        this.airline_id = airline_id;
        this.source_airport = source_airport;
        this.sourceAir_id = sourceAir_id;
        this.destinAir = destinAir;
        this.stops = stops;
    }

     /**
	 * Instance Variables/Fields
	 */
    public static Map<String,List<Routes>> Hmap=new HashMap<String,List<Routes>>();
    public String airline;
    public String airline_id;
    public String source_airport;
    public String sourceAir_id;
    public String destinAir;
    public String stops;


  
    @Override
    public String toString() {
        return "Routes [airline=" + airline + ", airline_id=" + airline_id + ", source_airport=" + source_airport + ", sourceAir_id=" + sourceAir_id +", destinAir=" + destinAir
                +   ", stops=" + stops + "]";
    }


       /**
     * @returns none
     * This method reads the file, splits the lines in the file, 
     * and stores the values in a hashmap. 
     * The keys of the hashmap are strings and the values are arraylists of objects
    */
    public static void reader(){
    
       BufferedReader br;
        String file = "routes.csv";
        String []values = null;
        try{
            br = new BufferedReader(new FileReader(file));
            String line;
            
            while((line= br.readLine())!= null){
                values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
               
                String airline = values[0];
                String airline_id = values[1];
                String source_airport = values[2];
                String sourceAir_id= values[3];
                String destinAir= values[4];
                String stops = values[7];

    /**'routes' is the object for the value in the hasmap and 'key' is the key in the hashmap
 */
                Routes routes = new Routes(airline, airline_id, source_airport, sourceAir_id, destinAir, stops);
                String key  = source_airport ;


                /**this section checks if there is already a key in the hashmap with the same name.
                 * if it does, it adds the new object to an arraylist belinging to the particular key already in the hashmap
                */
                if(Hmap.containsKey(key)){
              
                    Hmap.get(key).add(routes);
                    
                } else {
                   List<Routes> list = new ArrayList<>();
                   list.add(routes);
                   Hmap.put(key, list);
                }
            }
            br.close();
            
        }catch(IOException e){
            e.printStackTrace();   }
    }


    /**
	 * @param args
	 */
    public static void main(String[]args){
        // CSVFileStream myreader = new CSVFileStream();
        // myreader.csvReader("routes.csv");
        reader();
    }
}
