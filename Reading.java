

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/**
 * @author Annaliese Nartey
 *
 */
public class Reading{
   

    static String line ;
    static String iata2;
    static String cur;
    static String line2;
    static Queue<String> frontier = new ArrayDeque<String>();
    

 /**
 * @returns none
 * This method reads the user file, splits the lines in the file, 
 * and determine the start and the end destination
 * it calls the 'findcode' method to return a route that links the start to the destination
    */
    public static void csvReader (String file) {
        BufferedReader br;
        try{
            br = new BufferedReader(new FileReader(file));
            line = br.readLine();
            line2 = br.readLine();
        
            Airports.reader();
            if (Airports.Hmap.containsKey(line2)){
                for ( int i = 0; i<Airports.Hmap.get(line2).size(); i++){
                    iata2 = Airports.Hmap.get(line2).get(i).iata_code;
                    if(iata2 =="\\N"){
                        continue;}
                }
            }
                
            if (Airports.Hmap.containsKey(line)){
         
                for ( int i = 0; i<Airports.Hmap.get(line).size(); i++){
                    cur = Airports.Hmap.get(line).get(i).iata_code;
                    if(cur =="\\N"){
                        continue;}
                    else{
                        
                        findCode(cur);
                       }
                }  
            }   
            else{
                System.out.println("Cannot find the key "+ line);
            }                     
        }catch(IOException e){
            e.printStackTrace();}
    }

   static ArrayList<String> solution_path= new ArrayList<> ();
   static  HashMap<String, String> sol_path = new HashMap<>();
    /**
     * This method uses depth-first search to find the AITA codes of all the airports from the
     * start point to the end destination
     * @param start_iata
     * @return no return value
     * This resource was obtained from Doctor Ayorkor Korsah's Implementation of Depth-first search algorithm
     */
    public static  void  findCode(String start_iata){
        Routes.reader();
        List<String> explored= new ArrayList<>();
        

    /*Check if the AITA code passed as a parameter can be found as a key in the Routes hashmap  */
        if (Routes.Hmap.containsKey(start_iata)){
            
            int number =Routes.Hmap.get(start_iata).size();
         
            /*Check if the AITA code is the start point is the same as the end point */  
            if(start_iata.equals(iata2)){      
                System.out.println("Your start state is your destination goal");
                System.exit(0);}
                
            else{
                frontier.add(start_iata);}
              

            /* This loop will run until the frontier is empty */
            while (frontier.size() > 0){
                String removed_code = frontier.remove();
                
                explored.add(removed_code);
              
                if (Routes.Hmap.containsKey(removed_code)){
                    
                /*The variable number2 gets the number of values that the this perticular
                 hashmap key has and run a loop for that number of times */
                    int number2 =Routes.Hmap.get(removed_code).size();

                    for ( int j = 0; j<number2; j++ ){
                        String child = Routes.Hmap.get(removed_code).get(j).destinAir;
                        // System.out.println("The child is "+child);
                        if ((!(explored.contains(child))) && !(frontier.contains(child))  ){
                           
                            sol_path.put(child, removed_code);
                            
                            if(child.equals(iata2)){
                         
                                System.out.println(correctpath(iata2));
                                outputFile();
                                System.exit(0);}
                            else{
                                frontier.add(child);}
                               
                        }
                    } 
                }
                else{

                    continue;  }       
            }
        } 

    }
    static ArrayList<String> answer = new ArrayList<>();
    public static ArrayList<String> correctpath(String endpoint) {

      
        answer.add(endpoint);
        String ending = endpoint;

        while (sol_path.containsKey(ending)) {
            ending = sol_path.get(ending);
            
            answer.add(ending);
        }
        Collections.reverse(answer);
        return answer;
    }

    
                
    public static void outputFile(){
   
    
    PrintWriter writer = null;

            try {
                writer = new PrintWriter(new FileOutputStream("From " +line +" to "+line2 +"output.txt"));
                int mysize = answer.size();
                if(answer.size() == 0){
                    writer.print("NO route found");
                }
                int final_size = mysize - 1;
                
                int start = 0;
                while (start <answer.size()-1) {
                    writer.println("From " + answer.get(start)+ " to " + answer.get(start+1) + ", stops = 0");
                    start ++;
                }
                // writer.println("The answer path is "+ answer);
                writer.println("Total flights = " + final_size);
                writer.println("Total Additional stops = 0. ");
            } catch (IOException e) {
                System.out.println( "Invalid");
                e.printStackTrace();
            System.exit(0);

            }
            

            writer.close();
        }
        

                  
        
    


    public static void main(String[]args){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type in you file");
        String file = sc.nextLine();
        csvReader(file);
        sc.close();
     
    
}


 