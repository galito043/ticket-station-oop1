package Commands;

import Interfaces.Command;
import Structures.Booking;
import Structures.Event;
import Structures.Purchase;
import Structures.SessionInformation;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Save implements Command <Void, String> {
    private SessionInformation sessionInformation;

    public Save(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    public Void run(String[] args) throws IOException {

        FileWriter fw = new FileWriter(Open.curFile);

        for(Event e: sessionInformation.getEvents()){
            fw.write(e.toString() + "\n");
        }
        for(Booking b : sessionInformation.getBookings()){
            fw.write(b.toString() + "\n");
        }
        for(Purchase p : sessionInformation.getPurchases()){
            fw.write(p.toString() + "\n");
        }
        System.out.println("Saving...");
        fw.close();
        return null;
    }
}
