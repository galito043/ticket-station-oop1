package Structures;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SessionInformation {
    public  List<String> fileContents = new ArrayList<>();
    public  List<Booking> bookings = new ArrayList<>();
    public  List<Event> events = new ArrayList<>();
    public  List<Purchase> purchases = new ArrayList<>();
    public  List<Hall> halls = new ArrayList<>();

    private static SessionInformation sessionInformation;
    public static SessionInformation getInstance(){
        if(sessionInformation == null){
            sessionInformation = new SessionInformation();
        }
        return sessionInformation;
    }


    public  void putObject( String string, String[] params){
        if(string.startsWith("Event")){
            events.add(new Event(LocalDate.parse(params[1]), params[2], params[3]));
        }
        else if(string.startsWith("Booking")){
            bookings.add(new Booking(params[1], params[2], LocalDate.parse(params[3]), params[4], params[5] ));
        }
        else if(string.startsWith("Purchase")){
            Purchase currentPurchase = new Purchase(params[2], params[3],LocalDate.parse(params[4]), params[5], params[1]);
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

    public List<Booking> getBookings() {
        return bookings;
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public List<Hall> getHalls() {
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
