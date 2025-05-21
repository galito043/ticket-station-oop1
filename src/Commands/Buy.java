package Commands;

import Exceptions.*;
import Interfaces.Command;
import Structures.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
/**
 * Implements the "buy" command: attempts to purchase a ticket,
 * removing any existing booking first.
 */
public class Buy implements Command<Void,String> {
    private SessionInformation sessionInformation;
    private Book book;
    public Buy(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
        book = new Book(sessionInformation);
    }
    /**
     * @param args array of four strings as above
     * @return always null
     * @throws EmptyPurchaseParametersException if wrong number of args or any is empty
     * @throws InvalidRowAndSeatNumbers       if row/seat parsing fails
     * @throws EventNotFoundException          if no matching event
     * @throws RowOrSeatOverLimitException     if seat outside hall bounds
     * @throws TicketAlreadyBoughtException    if purchase already exists
     */
    @Override
    public Void run(String[] args) throws Exception {
        try{
            if(args.length != 4 || args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty()){
                throw new EmptyPurchaseParametersException("Usage buy <row> <seat> <date> <name>");
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
            LocalDate date = LocalDate.parse(dateString);
            String name = args[3].toUpperCase();
            Event curEvent = eventExists(name, date);

            if(curEvent == null){
                throw new EventNotFoundException("Event with name " + name + " not found on this date");
            }

            if(book.isRowSeatOverLimit(row, seat, curEvent.getHallId())){
                throw new RowOrSeatOverLimitException("The row or seat you entered does not exist in this hall");
            }

            Purchase newPurchase = new Purchase(row, seat, date, name);
            if(!sessionInformation.getPurchases().contains(newPurchase)){
                sessionInformation.getBookings().remove(new Booking(newPurchase.getTicket()));


                sessionInformation.addPurchase(newPurchase);
                System.out.println("Ticket bought");
            }
            else{
                throw new TicketAlreadyBoughtException("The ticket is already bought.");
            }
        }catch ( EmptyPurchaseParametersException |TicketAlreadyBoughtException | EventNotFoundException | RowOrSeatOverLimitException | DateTimeParseException | InvalidRowAndSeatNumbers e){
            System.out.println(e.getMessage());
        }


        return null;
    }
    public Event eventExists(String eventName, LocalDate date){
        for(Event e : sessionInformation.getEvents()){
             if(e.getNameOfEvent().equalsIgnoreCase(eventName) && e.getLocalDate().equals(date)){
                return e;
             }
        }
        return null;
    }
}
