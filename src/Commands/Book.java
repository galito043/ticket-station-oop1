package Commands;

import Exceptions.EmptyBookingParametersException;
import Exceptions.EventDoesNotExistException;
import Interfaces.Command;
import Structures.Booking;

import java.time.LocalDate;
import java.util.Arrays;

public class Book implements Command<Void, String> {

    @Override
    public Void run(String[] args) throws Exception {
        try{
            String row = args[0];
            String seat = args[1];
            String date = args[2];
            String name = args[3];
            if(!Open.events.stream().anyMatch( event -> event.getNameOfEvent().equals(name) && event.getLocalDate().toString().equals(date))){
                throw new EventDoesNotExistException("Event does not exist on this date");
            }
            String note = args[4];
            Booking newBooking = new Booking(row,  seat, LocalDate.parse(date), name, note);
            Open.fileContents.add(newBooking.toString());
            Open.bookings.add(newBooking);
            System.out.println("Successfully added Booking " + newBooking.toString());
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new EmptyBookingParametersException("One or more booking parameter is empty");
        }
        return null;
    }
}
