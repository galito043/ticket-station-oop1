package Commands;

import Exceptions.BookingAlreadyExistsException;
import Exceptions.EmptyBookingParametersException;
import Exceptions.EventDoesNotExistException;
import Interfaces.Command;
import Structures.Booking;
import Structures.Event;
import Structures.Purchase;
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
            if(args.length == 5){
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
                if(!sessionInformation.getBookings().contains(newBooking) && !sessionInformation.getPurchases().contains(new Purchase(newBooking.getTicket()))){
                    sessionInformation.addBooking(newBooking);
                    System.out.println("Successfully added Booking " + newBooking.toString());
                }
                else{
                    throw new BookingAlreadyExistsException("Ticket is already booked or bought");
                }

            }
            else{
                throw new EmptyBookingParametersException("Usage book <row> <seat> <date> <name> <note>");
            }

        }
            catch (EventDoesNotExistException | EmptyBookingParametersException | BookingAlreadyExistsException e) {
                System.out.println(e.getMessage());
            }

        return null;
        }

    }

