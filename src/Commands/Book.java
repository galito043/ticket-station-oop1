package Commands;

import Exceptions.EmptyBookingParametersException;
import Exceptions.EventDoesNotExistException;
import Interfaces.Command;
import Structures.Booking;
import Structures.Event;
import Structures.SessionInformation;

import java.time.LocalDate;
import java.util.Arrays;

public class Book implements Command<Void, String> {
private SessionInformation sessionInformation;

    public Book(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    @Override
    public Void run(String[] args) throws Exception {
        try{
            String row = args[0];
            String seat = args[1];
            String date = args[2];
            String eventName = args[3];
            String note = args[4];
            boolean exists = false;
            for(Event e : sessionInformation.getEvents()){
                if(e.getNameOfEvent().equals(eventName) && e.getLocalDate().toString().equals(date)){
                    exists = true;
                }
            }
            if(!exists){
                throw new EventDoesNotExistException("Event " + eventName + " does not exist on this date " + date);
            }


            Booking newBooking = new Booking(row,  seat, LocalDate.parse(date), eventName, note);
            sessionInformation.addBooking(newBooking);
            System.out.println("Successfully added Booking " + newBooking.toString());
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new EmptyBookingParametersException("Usage book <row>,<seat>,<date>,<name>,<note>");
        }
        return null;
    }
}
