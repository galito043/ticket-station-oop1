package Commands;

import Interfaces.Command;
import Structures.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bookings implements Command<Void,String> {

    @Override
    public Void run(String[] args) throws Exception {
        List<Booking> bookingsToList = new ArrayList<>();
        if(args.length  == 2){
            String date = args[0];
            String name = args[1];
            Open.bookings.stream().filter(booking -> booking.getTicket().getDate().toString().equals(date) && booking.getTicket().getName().equals(name)).forEach(booking -> bookingsToList.add(booking));
            System.out.println("Bookings for play " + name + " on date " + date);
            bookingsToList.forEach(booking -> System.out.println(booking.toString()));
        }
        else if(args.length == 1){
            String date = args[0];
            Open.bookings.stream().filter(booking -> booking.getTicket().getDate().toString().equals(date))
                    .forEach(booking -> bookingsToList.add(booking));
            System.out.println("Bookings on " + date);
            bookingsToList.forEach(booking -> System.out.println(booking.toString()));
        }
        else if (args.length == 0){
            Open.bookings.forEach(booking -> System.out.println(booking.toString()));
        }
       return null;

    }
}
