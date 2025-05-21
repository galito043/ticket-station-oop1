package Commands;

import Exceptions.EmptyUnbookParametersException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidRowAndSeatNumbers;
import Interfaces.Command;
import Structures.Booking;
import Structures.SessionInformation;
import Structures.Ticket;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Iterator;


/**
 * Unbooks a previously added booking
 */
public class Unbook implements Command<Void, String> {
    private SessionInformation sessionInformation;

    public Unbook(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    @Override
    public Void run(String[] args) throws Exception {
        try{
            if(args.length < 4){
                throw new EmptyUnbookParametersException("Usage : unbook <row> <seat> <date> <name>");
            }
            if(args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty()){
                throw new EmptyUnbookParametersException("Usage : unbook <row> <seat> <date> <name>");
            }

            int row;
            int seat;

            try{
                row = Integer.parseInt(args[0]);
                seat = Integer.parseInt(args[1]);

            }catch (NumberFormatException e){
                throw new InvalidRowAndSeatNumbers("Invalid row and seat choice");
            }

            String dateString = args[2];
            String nameOfEvent = args[3].toUpperCase();
            LocalDate localDate;

            try{
                localDate = LocalDate.parse(dateString);
            }
            catch (DateTimeParseException ex){
                throw new InvalidDateException("Invalid date date must be in format YYYY-MM-DD");
            }
            Iterator<Booking> it = sessionInformation.getBookings().iterator();
            Ticket ticketToMatch = new Ticket(row,seat,localDate, nameOfEvent);
            boolean removed = false;
            while (it.hasNext()){
                Booking b = it.next();
                if(b.getTicket().equals(ticketToMatch)){
                    it.remove();
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
        }catch (EmptyUnbookParametersException | InvalidDateException | NumberFormatException | InvalidRowAndSeatNumbers | DateTimeParseException e){
            System.out.println(e.getMessage());
        }

        return null;
    }
}
