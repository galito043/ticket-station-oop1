package Commands;

import Exceptions.EmptyFreeSeatsCommandException;
import Exceptions.EventNotFoundException;
import Exceptions.HallNotFoundException;
import Interfaces.Command;
import Structures.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class FreeSeats implements Command<Void, String> {
private SessionInformation sessionInformation;

    public FreeSeats(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    public int getTotalSeatsInHall(String hallId) {
        String stringValHallId = hallId;
        Optional<Integer> seats;

//        seats = Open.halls.stream().filter(hall -> hall.getId() == Integer.parseInt(hallId))
//                .findFirst()
//                .map(hall -> hall.getNumberOfSeatsPerRow() * hall.getNumberOfRows());
        for(Hall hall : sessionInformation.getHalls()){
            if(hall.getId() == Integer.parseInt(hallId)){
                return hall.getNumberOfSeatsPerRow() * hall.getNumberOfRows();
            }
        }


        return 0;
    }

    @Override
    public Void run(String[] args) throws Exception {
        String date;
        String eventName;
//        System.out.println(args.length);
        if (args.length == 2) {
            date = args[0];
            eventName = args[1];
        }
            else{
                throw new EmptyFreeSeatsCommandException("Usage: freeseats <date>,<name>\n");
            }
        int totalSeatsInHall = 0;
        int takenSeats = 0;
        LocalDate localDate = LocalDate.parse(date);

//        Event curEvent = Open.events.stream()
//                .filter(event -> event.getNameOfEvent().equals(eventName) && event.getLocalDate().toString().equals(date)).findFirst().orElseThrow(() -> new EventNotFoundException("Event is not found"));
        Event curEvent = null;
        boolean eventFound = false;
//        System.out.println(date + " " + eventName);
        for(Event e : sessionInformation.getEvents()){
            System.out.println(e.getLocalDate().toString()  + " " + e.getNameOfEvent());
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
//        ticketList.addAll(Open.bookings.stream()
//                .filter(booking -> booking.getTicket().getName().equals(eventName) && booking.getTicket().getDate().toString().equals(date)).map(booking -> booking.getTicket()).toList());
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

//        ticketList.addAll(Open.purchases.stream()
//                .filter(purchase -> purchase.getTicket().getName().equals(eventName) && purchase.getTicket().getDate().toString().equals(date)).map(purchase -> purchase.getTicket()).toList());
//        System.out.println(ticketList);

        String curEventHallIdId = curEvent.getHallId();
//        Hall curHall = Open.halls.stream().filter(hall -> hall.getId() == Integer.parseInt(curEventHallIdId)).findFirst().orElseThrow(() -> new HallNotFoundException("Hall not found"));
        Hall curHall = null;
        for(Hall h : sessionInformation.getHalls()){
            if(Integer.parseInt(curEventHallIdId) == h.getId()){
                curHall = h;
                break;
            }
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
                boolean isTaken = ticketList.stream().anyMatch(ticket -> Integer.parseInt(ticket.getRow()) == finalI && Integer.parseInt(ticket.getSeat()) == finalZ);
                if (isTaken) {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
        return null;
    }

}
