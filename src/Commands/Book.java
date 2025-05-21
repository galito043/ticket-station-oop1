package Commands;

import Exceptions.*;
import Interfaces.Command;
import Structures.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Handles booking a seat for a specific event and date.
 */

public class Book implements Command<Void, String> {
private SessionInformation sessionInformation;

    public Book(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    /**
     * @throws InvalidRowAndSeatNumbers     if row or seat are not integers
     * @throws EventDoesNotExistException   if event not found on given date
     * @throws RowOrSeatOverLimitException  if row or seat exceed hall limits
     * @throws EmptyBookingParametersException if args length is not 5
     */
    @Override
    public Void run(String[] args) throws Exception {
        try{
            if(args.length == 5){
                int row;
                int seat;
                LocalDate date = null;
                try{
                    row = Integer.parseInt(args[0]);
                    seat = Integer.parseInt(args[1]);

                }catch (NumberFormatException e){
                    throw new InvalidRowAndSeatNumbers("Invalid row and seat choice");

                }
                try{
                    date = LocalDate.parse(args[2]);
                }catch (DateTimeParseException e ){
                    System.out.println("Invalid date");
                    return null;
                }


                String eventName = args[3].toUpperCase();
                String note = args[4];
                boolean exists = false;
                Event curEvent = null;
                for(Event e : sessionInformation.getEvents()){
                    if(e.getNameOfEvent().equals(eventName) && e.getLocalDate().equals(date)){
                        curEvent = e;
                        exists = true;
                    }
                }
                if(!exists){
                    throw new EventDoesNotExistException("Event " + eventName + " does not exist on this date " + date);
                }

                if(isRowSeatOverLimit(row, seat, curEvent.getHallId())){
                    throw  new RowOrSeatOverLimitException("The row or seat you entered does not exist in this hall");
                }
                Booking newBooking = new Booking(row,  seat, date, eventName, note);
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
            catch (EventDoesNotExistException | EmptyBookingParametersException | BookingAlreadyExistsException | RowOrSeatOverLimitException | DateTimeParseException | InvalidRowAndSeatNumbers e ) {
                System.out.println(e.getMessage());
            }

        return null;
        }

public boolean isRowSeatOverLimit(int row, int seat, int hallId){
        for(Hall h : sessionInformation.getHalls()){
            if(h.getId() == hallId){
                int hRows = h.getNumberOfRows();
                int hSeats = h.getNumberOfSeatsPerRow();
                if(row > hRows || row < 1 ||  seat > hSeats || seat < 1){
                    return true;
                }
            }

        }
        return false;
}
    }

