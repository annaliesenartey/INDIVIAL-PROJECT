package Assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Airliness  {
    /**
	 * Instance Variables/Fields
	 */
    
    private String airline_id;
    private String airline_name;
    private String alias;
    private String airline_iata;
    private String ailine_icao;
    private String callsign;

    public static Map<String,List<Airliness>> Hmap=new HashMap<String,List<Airliness>>();
 /**
	 * Constructor: 
	 * Build and initialise objects of this class 
	 * @param airline_id the unique OpenFlights identifier for the airline.
	 * @param airline_name the name of airline.
	 * @param alias the also known as of the airline.
	 * @param airline_iata the 2-letter IATA code
	 * @param  airlinr_icao the 3-letter ICAO code
	 * @param callsign the Airline callsign.
	 */
    public Airliness(String airline_id, String airline_name, String alias, String airline_iata, String ailine_icao,
    String callsign) {
    this.airline_id = airline_id;
    this.airline_name = airline_name;
    this.alias = alias;
    this.airline_iata = airline_iata;
    this.ailine_icao = ailine_icao;
    this.callsign = callsign;
    }
     
    @Override
    public String toString() {
        return "Airliness [ailine_icao=" + ailine_icao + ", airline_iata=" + airline_iata + ", airline_id=" + airline_id
                + ", airline_name=" + airline_name + ", alias=" + alias + ", callsign=" + callsign + "]"; }


       /**
     * @returns none
     * This method reads the file, splits the lines in the file, 
     * and stores the values in a hashmap. 
     * The keys of the hashmap are strings and the values are arraylists of objects
    */
    public static void reader(){
     
        BufferedReader br;
        // String file = "airports.csv";
        String file = "airlines.csv";

        try{
            br = new BufferedReader(new FileReader(file));
            String line;
            Airliness airline;
            String key ;
          
            while((line= br.readLine())!= null){
        
                String[] values = line.split(",");
                java.util.Arrays.toString(values);
              
                String airline_id = values[0];
                String airline_name = values[1];
                String alias = values[2];
                String airline_iata = values[3];
                String airline_icao = values[4];
                String callsign = values[5];

    /**'airline' is the object for the value in the hasmap and 'key' is the key in the hashmap
     */
                key = airline_icao;
                airline = new Airliness(airline_id, airline_name, alias, airline_iata, airline_icao, callsign);


/**this section checks if there is already a key in the hashmap with the same name.
 * if it does, it adds the new object to an arraylist belinging to the particular key already in the hashmap
*/
                if(Hmap.containsKey(key)){
                    Hmap.get(key).add(airline);
                
                } else {
                   List<Airliness> list = new ArrayList<>();
                   list.add(airline);
                   Hmap.put(key, list);
                }
            }
            br.close();
              
        }catch(IOException e){
            e.printStackTrace();
        }
    }

   /**
	 * @param args
	 */
    public static void main(String[]args){
        reader();
    }
}
