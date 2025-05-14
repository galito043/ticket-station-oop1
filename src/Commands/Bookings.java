package Commands;

import Exceptions.TooManyParametersException;
import Interfaces.Command;
import Structures.Booking;
import Structures.SessionInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bookings implements Command<Void,String> {
private SessionInformation sessionInformation;

    public Bookings(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    @Override
    public Void run(String[] args) throws Exception {
        List<Booking> bookingsToList = new ArrayList<>();
        if(args.length  == 2){
            String date = args[0];
            String name = args[1];
            for(Booking b : sessionInformation.getBookings()){
                if(b.getTicket().getName().equals(name) && b.getTicket().getDate().toString().equals(date)){
                    bookingsToList.add(b);
                }
            }
//            Open.bookings.stream().filter(booking -> booking.getTicket().getDate().toString().equals(date) && booking.getTicket().getName().equals(name)).forEach(booking -> bookingsToList.add(booking));
            System.out.println("Bookings for play " + name + " on date " + date);
            bookingsToList.forEach(booking -> System.out.println(booking.toString()));
        }
        else if(args.length == 1){
            String date = args[0];
            for(Booking b: sessionInformation.getBookings()){
                if(b.getTicket().getDate().toString().equals(date)){
                    bookingsToList.add(b);
                }
            }
            System.out.println("Bookings on " + date);
            bookingsToList.forEach(booking -> System.out.println(booking.toString()));
        }
        else if (args.length == 0){
            for(Booking b : sessionInformation.getBookings()){
                System.out.println(b.toString());
            }
        }
        else{
            throw new TooManyParametersException("Too many parameters. Usage bookings [<date>] [<name>]");
        }
       return null;

    }
}
