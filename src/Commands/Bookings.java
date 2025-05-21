package Commands;

import Exceptions.NoBookingsException;
import Exceptions.NoTicketsException;
import Exceptions.TooManyParametersException;
import Interfaces.Command;
import Structures.Booking;
import Structures.SessionInformation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the "bookings" command: lists bookings filtered by date and event name or just date
 */
public class Bookings implements Command<Void,String> {
private SessionInformation sessionInformation;

    public Bookings(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    /**
     * @throws TooManyParametersException if too many params
     * @throws NoBookingsException if there are no bookings to show
     */
    @Override
    public Void run(String[] args) throws Exception {
        try {
            List<Booking> bookingsList;
            if(args.length  == 2){
                
                LocalDate date = null;
                try{
                    date = LocalDate.parse(args[0]);
                }catch (DateTimeParseException e){
                    System.out.println("Invalid date");
                }
                
                String name = args[1].toUpperCase();
                bookingsList = getBookings(date, name);
                System.out.println("Bookings for play " + name + " on date " + date);
                if(!bookingsList.isEmpty()){
                    for(Booking b : bookingsList){
                        System.out.println(b.toString());
                    }
                }
                else{
                    throw new NoBookingsException("No bookings to show with this name and on this date");
                }
            }
            else if(args.length == 1){
                LocalDate date = null;
                try{
                    date = LocalDate.parse(args[0]);
                }catch (DateTimeParseException e){
                    System.out.println("Invalid date");
                }
                bookingsList = getBookings(date);
                System.out.println("Bookings on " + date);
                if(!bookingsList.isEmpty()){
                    for(Booking b: bookingsList){
                        System.out.println(b.toString());
                    }
                }
                else{
                    throw new NoBookingsException("No bookings to on this date");
                }
            }
            else if (args.length == 0){
                if(!sessionInformation.getBookings().isEmpty()){
                    for(Booking b : sessionInformation.getBookings()){
                        System.out.println(b.toString());
                    }
                }
                else{
                    throw new NoBookingsException("No bookings to show");
                }
            }
            else{
                throw new TooManyParametersException("Too many parameters. Usage bookings [<date>] [<name>]");
            }
        } catch (TooManyParametersException | NoTicketsException | NoBookingsException | DateTimeParseException e){
            System.out.println(e.getMessage());
        }

       return null;

    }
    public List<Booking> getBookings(LocalDate date, String name){
        List<Booking> bookingsToList = new ArrayList<>();
        for(Booking b : sessionInformation.getBookings()){
            if(b.getTicket().getName().equals(name) && b.getTicket().getDate().equals(date)){
                bookingsToList.add(b);
            }
        }
        return bookingsToList;
    }
    public List<Booking> getBookings(LocalDate date){
        List<Booking> bookingsToList = new ArrayList<>();
        for(Booking b: sessionInformation.getBookings()){
            if(b.getTicket().getDate().equals(date)){
                bookingsToList.add(b);
            }
        }
        return bookingsToList;
    }


}
