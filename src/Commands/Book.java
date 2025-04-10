package Commands;

import Interfaces.Command;
import Structures.Booking;

import java.time.LocalDate;
import java.util.Arrays;

public class Book implements Command<Void, String> {

    @Override
    public Void run(String[] args) throws Exception {
        String row = args[0];
        String seat = args[1];
        String date = args[2];
        String name = args[3];
        String note = args[4];
        boolean anyEmpty = Arrays.stream(args).anyMatch(s -> s.isEmpty());

        if(!anyEmpty){
            Booking newBooking = new Booking(row,  seat, LocalDate.parse(date), name, note);
            Open.fileContents.add(newBooking.toString());
            System.out.println("Successfully added Booking " + newBooking.toString());
        }
        else{
            return null;
        }

        return null;
    }
}
