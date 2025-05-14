package Commands;

import Exceptions.EmptyUnbookParametersException;
import Exceptions.InvalidDateException;
import Interfaces.Command;
import Structures.Booking;
import Structures.SessionInformation;
import Structures.Ticket;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Unbook implements Command<Void, String> {
    private SessionInformation sessionInformation;

    public Unbook(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

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
        String dateString = args[2];
        String nameOfEvent = args[3];
        LocalDate localDate;
        try{
            localDate = LocalDate.parse(dateString);
        }
        catch (DateTimeParseException ex){
            throw new InvalidDateException("Invalid date date must be in format YYYY-MM-DD");
        }
//        boolean removed = Open.bookings.removeIf(booking -> booking.getTicket().equals(new Ticket(row, seat, date1, name)));
        boolean removed = false;
        for(Booking b : sessionInformation.getBookings()){
            if(b.getTicket().equals(new Ticket(row,seat,localDate, nameOfEvent))){
                sessionInformation.getBookings().remove(b);
                removed = true;
                break;

            }
        }
        if(removed){
            System.out.println("Removed "+  "Booking " + String.join(" ", args));

        }
        else{
            System.out.println("No matching booking found  for " + String.join(" ", args));
        }
        return null;
    }
}
