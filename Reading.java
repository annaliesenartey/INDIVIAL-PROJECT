

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
    static Stack<String> frontier = new Stack<String>();
    

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
                System.out.println("line2 is in here ")  ;
                for ( int i = 0; i<Airports.Hmap.get(line2).size(); i++){
                    iata2 = Airports.Hmap.get(line2).get(i).iata_code;
                    if(iata2 =="\\N"){
                        continue;}
                }
            }
                
            if (Airports.Hmap.containsKey(line)){
                System.out.println("Goooooooooood ");      
                for ( int i = 0; i<Airports.Hmap.get(line).size(); i++){
                    cur = Airports.Hmap.get(line).get(i).iata_code;
                    if(cur =="\\N"){
                        continue;}
                    else{
                        
                        findCode(cur);
                        System.out.println(cur);}
                }  
            }   
            else{
                System.out.println("Cannot find the key "+ line);
            }                     
        }catch(IOException e){
            e.printStackTrace();}
    }

   static ArrayList<String> solution_path= new ArrayList<> ();
    /**
     * This method uses depth-first search to find the AITA codes of all the airports from the
     * start point to the end destination
     * @param sourceidd
     * @return no return value
     * This resource was obtained from Doctor Ayorkor Korsah's Implementation of Depth-first search algorithm
     */
    public static  void  findCode(String sourceidd){
        Routes.reader();
        System.out.println("The source id is "+ sourceidd);
        List<String> explored= new ArrayList<>();
        

    /*Check if the AITA code passed as a parameter can be found as a key in the Routes hashmap  */
        if (Routes.Hmap.containsKey(sourceidd)){
            int number =Routes.Hmap.get(sourceidd).size();
            System.out.println(number);
        
            /*Check if the AITA code is the start point is the same as the end point */  
            if(sourceidd.equals(iata2)){      
                System.out.println("Found"); 
                System.exit(0);}
            else{
                frontier.add(sourceidd);}
                //solution_path.add(sourceidd);

            /* This loop will run until the frontier is empty */
            while (frontier.size() > 0){
                String removed_code = frontier.pop();
                // System.out.println("REMOVED CODE IS "+removed_code);
                explored.add(removed_code);
              
                if (Routes.Hmap.containsKey(removed_code)){
                    
                /*The variable number2 gets the number of values that the this perticular
                 hashmap key has and run a loop for that number of times */
                    int number2 =Routes.Hmap.get(removed_code).size();

                    for ( int j = 0; j<number2; j++ ){
                        String child = Routes.Hmap.get(removed_code).get(j).destinAir;
                        // System.out.println("The child is "+child);
                        if ((!(explored.contains(child))) && !(frontier.contains(child))  ){
                            if (!solution_path.contains(removed_code)) {
                                // Adding the element to the ArrayList if it
                                // is not present
                                solution_path.add(removed_code);
                            }
                            
                            if(child.equals(iata2)){
                               
                                
                                solution_path.add(iata2);
                                //System.out.println(solution_path);
                                outputFile();
                                System.exit(0);}
                            else{
                                frontier.add(child);}
                        }
                    } 
                }
                else{
                    System.out.println("Moving to next node");
                    continue;  }       
            }
        }          
    }
                
    public static void outputFile(){
    try {
        PrintWriter writer = new PrintWriter(new FileOutputStream("output.txt"));
        for (int i = 0; i < solution_path.size(); i++){
            // outsStream.write("From "+solution_path.get(i) + " to "+ solution_path.get(i) );
           
            writer.print("From "+solution_path.get(i) + " to "+ solution_path.get(i++));
            i++;
            writer.println(" to "+ solution_path.get(i));
            writer.println("The solution path is "+ solution_path);
            writer.close();
            
        }
        


        writer.close();
    } catch (FileNotFoundException f) {
        System.out.println("Invalid");
        System.exit(0);
    }

}                  
        
    


    public static void main(String[]args){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type in you file");
        String file = sc.nextLine();
        csvReader(file);
      
    }
    
}


