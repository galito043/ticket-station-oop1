package Commands;

import Exceptions.EmptyUnbookParametersException;
import Exceptions.InvalidDateException;
import Interfaces.Command;
import Structures.Booking;
import Structures.Ticket;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Unbook implements Command<Void, String> {
    @Override
    public Void run(String[] args) throws Exception {
        if(args.length < 4){
            throw new EmptyUnbookParametersException("Usage : unbook <row> <seat> <date> <name>");
        }
        if(args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty()){
            throw new EmptyUnbookParametersException("Usage : unbook <row> <seat> <date> <name>");
        }

        String row = args[0];
        String seat = args[1];
        String date = args[2];
        String name = args[3];
        LocalDate date1;
        try{
            date1 = LocalDate.parse(date);
        }
        catch (DateTimeParseException ex){
            throw new InvalidDateException("Invalid date date must be in format YYYY-MM-DD");
        }
        boolean removed = Open.bookings.removeIf(booking -> booking.getTicket().equals(new Ticket(row, seat, date1, name)));
        if(removed){
            System.out.println("Removed "+  "Booking " + String.join(" ", args));

        }
        else{
            System.out.println("No matching booking found  for " + String.join(" ", args));
        }
        return null;
    }
}
