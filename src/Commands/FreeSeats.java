package Commands;

import Exceptions.EmptyFreeSeatsCommandException;
import Exceptions.EventNotFoundException;
import Exceptions.HallNotFoundException;
import Interfaces.Command;
import Structures.Event;
import Structures.Hall;
import Structures.Ticket;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class FreeSeats implements Command<Void, String> {


    public int getTotalSeatsInHall(String hallId) {
        String[] curFileContents = Open.halls.toArray(new String[0]);
        String stringValHallId = hallId;
        Optional<Integer> seats;
        seats = Open.halls.stream().filter(hall -> hall.getId() == Integer.parseInt(hallId))
                .findFirst()
                .map(hall -> hall.getNumberOfSeatsPerRow() * hall.getNumberOfRows());

//    for(String s : curFileContents){
//        if(s.startsWith("Hall") && s.contains(stringValHallId)) {
//            String[] splitLine =  s.split(" ");
//            return Integer.parseInt(splitLine[2]) * Integer.parseInt(splitLine[3]);
//
//        }
//    }

        return 0;
    }

    @Override
    public Void run(String[] args) throws Exception {
        String date;
        String eventName;
        if (args.length == 2) {
            date = args[0];
            eventName = args[1];
        }
//        } else {
//            date = "2025-04-05";
//            eventName = "kafka";
//        }
            else{
                throw new EmptyFreeSeatsCommandException("Usage: freeseats <date> <name>\n");
            }
        int totalSeatsInHall = 0;
        int takenSeats = 0;
        Event curEvent = Open.events.stream()
                .filter(event -> event.getNameOfEvent().equals(eventName) && event.getLocalDate().toString().equals(date)).findFirst().orElseThrow(() -> new EventNotFoundException("Event is not found"));
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.addAll(Open.bookings.stream()
                .filter(booking -> booking.getTicket().getName().equals(eventName) && booking.getTicket().getDate().toString().equals(date)).map(booking -> booking.getTicket()).toList());
        ticketList.addAll(Open.purchases.stream()
                .filter(purchase -> purchase.getTicket().getName().equals(eventName) && purchase.getTicket().getDate().toString().equals(date)).map(purchase -> purchase.getTicket()).toList());
        System.out.println(ticketList);

        String curEventHallIdId = curEvent.getHallId();
        Hall curHall = Open.halls.stream().filter(hall -> hall.getId() == Integer.parseInt(curEventHallIdId)).findFirst().orElseThrow(() -> new HallNotFoundException("Hall not found"));

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
