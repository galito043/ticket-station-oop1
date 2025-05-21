package Commands;

import Exceptions.EmptyFreeSeatsCommandException;
import Exceptions.EventNotFoundException;
import Exceptions.HallNotFoundException;
import Interfaces.Command;
import Structures.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;



/**
 * Implements the "freeseats" command: displays a seating map for a given event and date,
 * marking free (O) and taken (X) seats.
 */
public class FreeSeats implements Command<Void, String> {
private SessionInformation sessionInformation;

    public FreeSeats(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    /**
     * Calculates total seat count for a hall.
     *
     * @param hallId the hall identifier
     * @return rows × seats per row, or 0 if hall not found
     */

    public int getTotalSeatsInHall(int hallId) {
        int stringValHallId = hallId;
        for(Hall hall : sessionInformation.getHalls()){
            if(hall.getId() == hallId){
                return hall.getNumberOfSeatsPerRow() * hall.getNumberOfRows();
            }
        }


        return 0;
    }

    @Override
    public Void run(String[] args) throws Exception {
        try{
            String date;
            String eventName;
            if (args.length == 2) {
                date = args[0];
                eventName = args[1].toUpperCase();
            }
            else{
                throw new EmptyFreeSeatsCommandException("Usage: freeseats <date> <name>\n");
            }
            int totalSeatsInHall = 0;
            int takenSeats = 0;
            LocalDate localDate = LocalDate.parse(date);


            Event curEvent = null;
            boolean eventFound = false;
            for(Event e : sessionInformation.getEvents()){
                if(e.getLocalDate().equals(localDate) && e.getNameOfEvent().equals(eventName)){
                    curEvent = e;
                    eventFound = true;
                    break;
                }
            }
            if(!eventFound){
                throw new EventNotFoundException("Event is not found");
            }

            List<Ticket> ticketList = new ArrayList<>();

            for(Booking b : sessionInformation.getBookings()){
                if(b.getTicket().getName().equals(eventName) && b.getTicket().getDate().toString().equals(date)){
                    ticketList.add(b.getTicket());
                }
            }
            for(Purchase p: sessionInformation.getPurchases()){
                if(p.getTicket().getName().equals(eventName) && p.getTicket().getDate().toString().equals(date)){
                    ticketList.add(p.getTicket());
                }
            }
            int curEventHallIdId = curEvent.getHallId();
            Hall curHall = null;
            for(Hall h : sessionInformation.getHalls()){
                if(curEventHallIdId == h.getId()){
                    curHall = h;
                    break;
                }
            }
            if(curHall == null){
                throw new HallNotFoundException("Hall with id " + curEventHallIdId + " not found");
            }

            System.out.print("Ред\\Място: ");
            for(int x = 1; x <= curHall.getNumberOfSeatsPerRow(); x++){
                System.out.print(x + " ");
            }
            System.out.println();
            for (int i = 1; i <= curHall.getNumberOfRows(); i++) {
                System.out.printf("%-10d ", i);
                for (int z = 1; z <= curHall.getNumberOfSeatsPerRow(); z++) {
                    int finalZ = z;
                    int finalI = i;
                    boolean isTaken = false;
                    for(Ticket ticket: ticketList){
                        if(ticket.getRow() == finalI && ticket.getSeat() == finalZ){
                            isTaken = true;
                            break;
                        }
                    }
                    if (isTaken) {
                        System.out.print("X ");
                    } else {
                        System.out.print("O ");
                    }
                }
                System.out.println();
            }
        }catch (EmptyFreeSeatsCommandException | EventNotFoundException | HallNotFoundException | DateTimeParseException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

}
