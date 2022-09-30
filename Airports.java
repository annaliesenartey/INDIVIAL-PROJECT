

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Annaliese Nartey
 *
 */
public class Airports {
    /**
	 * Instance Variables/Fields
	 */
    public static Map<String,List<Airports>> Hmap=new HashMap<String,List<Airports>>();
    public String airport_id;
    public String name;
    public String city;
    public String country;
    public String iata_code;
    public String icao_code;

    /**
	 * Constructor: 
	 * Build and initialise objects of this class 
	 * @param airport_id the unique OpenFlights identifier for the airport.
	 * @param name the name of airport.
	 * @param city the main city served by airport.
	 * @param country the country where airport is located.
	 * @param iata_code the 3-letter IATA code
	 * @param icao_code the 4-letter ICAO code
	 */
   
    public Airports(String airport_id, String name, String city, String country, String iata_code, String icao_code) {
        this.airport_id = airport_id;
        this.name = name;
        this.city = city;
        this.country = country;
        this.iata_code = iata_code;
        this.icao_code = icao_code;
    }

    @Override
    public String toString() {
        return "Airports [airport_id=" + airport_id + ", name=" + name  +", city=" + city + ", country=" + country + ", iata_code="
                + iata_code + ", icao_code=" + icao_code + "]";
    }

    /**
 * @returns none
 * This method reads the file, splits the lines in the file, 
 * and stores the values in a hashmap. 
 * The keys of the hashmap are strings and the values are arraylists of objects
    */
    public static void reader(){
       
        BufferedReader br;
        String file = "airports.csv";
        String []values;
        try{
            br = new BufferedReader(new FileReader(file));
            String line;
            while((line= br.readLine())!= null){
                values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                
                String airport_id = values[0];
                String name = values[1];
                String city = values[2];
                String country = values[3];
                String iata_code = values[4];
                String icao_code = values[5];
                
                /**'airport' is the object for the value in the hasmap and 'key' is the key in the hashmap
                 */
                Airports airport = new Airports(airport_id, name, city, country, iata_code, icao_code);
                String key  = city +", " + country ;


                /**this section checks if there is already a key in the hashmap with the same name.
                 * if it does, it adds the new object to an arraylist belinging to the particular key already in the hashmap
                */
                if(Hmap.containsKey(key)){
                    Hmap.get(key).add(airport);
                } else {
                   List<Airports> list = new ArrayList<>();
                   list.add(airport);
                   Hmap.put(key, list);
                }
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();      }
       
    }


    /**
	 * @param args
	 */
    public static void main(String[]args){
        reader();
    }
}
