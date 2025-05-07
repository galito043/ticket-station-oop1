package Commands;

import Interfaces.Command;
import Structures.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Open implements Command <Void, String>{
public static FileReader fr = null;
public static String curFile = null;
public static List<String> fileContents = new ArrayList<>();
public static List<Booking> bookings = new ArrayList<>();
public static List<Event> events = new ArrayList<>();
public static List<Purchase> purchases = new ArrayList<>();
public static List<Hall> halls = new ArrayList<>();
private String hallsFile = "src/TestFiles/halls.txt";
    public Void run(String[] args) throws IOException {

            fileContents.clear();
            halls.clear();
            events.clear();
            purchases.clear();
            bookings.clear();
            if(args.length >= 1){
                curFile = args[0];
            }
            else{
                curFile = "C:\\Users\\galit\\IdeaProjects\\TicketStation\\src\\TestFiles\\alternateInfoFile.txt";
            }
        System.out.println("Opening " + curFile);
            fr = new FileReader(curFile);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                String line = br.readLine();
                fileContents.add(line);
                String[] contents = line.split(" ");
                getObject(line, contents);

            }
            FileReader hallReader = new FileReader(hallsFile);
            BufferedReader bufferedHallReader = new BufferedReader(hallReader);

            while(bufferedHallReader.ready()){
                String line = bufferedHallReader.readLine();
                if(!line.isEmpty()){
                    String[] contents  = line.split(" ");

                    System.out.println(Arrays.toString(contents));
                    halls.add(new Hall(Integer.parseInt(contents[1]), Integer.parseInt(contents[2]), Integer.parseInt(contents[3])));

                }

            }
        System.out.println(bookings);
        System.out.println(events);
        System.out.println(purchases);
            return null;
        }
        public static void getObject( String string, String[] params){
            if(string.startsWith("Event")){
                events.add(new Event(LocalDate.parse(params[1]), params[2], params[3]));
            }
            else if(string.startsWith("Booking")){
                bookings.add(new Booking(params[1], params[2], LocalDate.parse(params[3]), params[4], params[5] ));
            }
            else if(string.startsWith("Purchase")){
                purchases.add(new Purchase(params[2], params[3],LocalDate.parse(params[4]), params[5], params[1]));
            }
        }

}
