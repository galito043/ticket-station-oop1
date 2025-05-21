package Commands;

import Exceptions.EmptySaveAsParametersException;
import Interfaces.Command;
import Structures.Booking;
import Structures.Event;
import Structures.Purchase;
import Structures.SessionInformation;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * Saves to a newly specified file
 */
public class SaveAs implements Command <Void, String>{
    private SessionInformation sessionInformation;

    public SaveAs(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    public Void run(String[] args) throws IOException {
        try{
            if(args.length  > 0){
                String saveAsPath = args[0];

                FileWriter fw = new FileWriter(saveAsPath);
                System.out.println(saveAsPath);

                for(Event e: sessionInformation.getEvents()){
                    fw.write(e.toString() + "\n");
                }
                for(Booking b : sessionInformation.getBookings()){
                    fw.write(b.toString() + "\n");
                }
                for(Purchase p : sessionInformation.getPurchases()){
                    fw.write(p.toString() + "\n");
                }
                System.out.println("Saving to " + saveAsPath + "....");
                fw.close();
            }
            else{
                throw new EmptySaveAsParametersException("Enter name of file you would like to save as to");
            }
        }catch (EmptySaveAsParametersException | FileNotFoundException e){
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

}
