package Commands;

import Interfaces.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bookings implements Command<Void,String> {

    @Override
    public Void run(String[] args) throws Exception {
        List<String > bookings = new ArrayList<>();
        if(args.length  == 2){

            String date = args[0];
            String name = args[1];


            Open.fileContents.stream().filter ( booking -> booking.contains("Booking") && booking.contains(date) && booking.contains(name) )
                    .forEach(booking -> bookings.add(booking));
            System.out.println("Booking for play " + name + " on date " + date);

            bookings.forEach(booking -> System.out.println(booking.toString()));


        }
        if(args.length == 1){
            String date = args[0];
            Open.fileContents.stream().filter ( booking -> booking.contains("Booking") && booking.contains(date) )
                    .forEach(booking -> bookings.add(booking));
            System.out.println("Booking on date " + date);

            bookings.forEach(booking -> System.out.println(booking.toString()));
        }
        else {

            Open.fileContents.stream().filter ( booking -> booking.contains("Booking") )
                    .forEach(booking -> bookings.add(booking));
            System.out.println("All bookings for all dates ");
            bookings.forEach(booking -> System.out.println(booking.toString()));
        }



       return null;

    }
}
