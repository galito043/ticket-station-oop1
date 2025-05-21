package Structures;

import java.time.LocalDate;
import java.util.*;
/**
 * Singleton class that stores the current session data
 * including loaded events, bookings, purchases, and halls
 */
public class SessionInformation {
    public  List<String> fileContents = new ArrayList<>();
    public  HashSet<Booking> bookings = new HashSet<>();
    public  HashSet<Event> events = new HashSet<>();
    public  HashSet<Purchase> purchases = new HashSet<>();
    public  HashSet<Hall> halls = new HashSet<>() {
    };

    private static SessionInformation sessionInformation;
    /**
     * @return the singleton instance of SessionInformation
     */
    public static SessionInformation getInstance(){
        if(sessionInformation == null){
            sessionInformation = new SessionInformation();
        }
        return sessionInformation;
    }


    public  void putObject( String string, String[] params){

        if(string.startsWith("Event")){
            int hallId = 0;
            try{
                hallId = Integer.parseInt(params[3]);
            }catch (NumberFormatException e){
                System.out.println("Invalid hall id in file");
            }

            events.add(new Event(LocalDate.parse(params[1]), params[2], hallId));
        }
        else if(string.startsWith("Booking")){
            bookings.add(new Booking(Integer.parseInt(params[1]), Integer.parseInt(params[2]), LocalDate.parse(params[3]), params[4], params[5] ));
        }
        else if(string.startsWith("Purchase")){
            Purchase currentPurchase = new Purchase(Integer.parseInt(params[2]), Integer.parseInt(params[3]),LocalDate.parse(params[4]), params[5], params[1]);
            purchases.add(currentPurchase);

        }

    }
    public  void putHall(String[] contents) {
        halls.add(new Hall(Integer.parseInt(contents[1]), Integer.parseInt(contents[2]), Integer.parseInt(contents[3])));
    }
    public  void reset(){
        bookings.clear();
        events.clear();
        purchases.clear();
        halls.clear();
    }

    public List<String> getFileContents() {
        return fileContents;
    }

    public HashSet<Booking> getBookings() {
        return bookings;
    }

    public HashSet<Event> getEvents() {
        return events;
    }

    public HashSet<Purchase> getPurchases() {
        return purchases;
    }

    public HashSet<Hall> getHalls() {
        return halls;
    }
    public void addBooking(Booking booking){
        bookings.add(booking);
    }
    public void addEvent(Event event){
        events.add(event);
    }
    public void addPurchase(Purchase purchase){
        purchases.add(purchase);
    }

}
