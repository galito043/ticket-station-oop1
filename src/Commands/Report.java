package Commands;

import Exceptions.EmptyReportParametersException;
import Exceptions.InvalidDateException;
import Exceptions.NoEventsException;
import Interfaces.Command;
import Structures.Event;
import Structures.Hall;
import Structures.Purchase;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Report implements Command <Void,String> {

    public boolean isDateBetweenTwoDates(LocalDate fromDate, LocalDate toDate, LocalDate dateToCheck){
        return !dateToCheck.isBefore(fromDate) && !dateToCheck.isAfter(toDate);
    }


    @Override
    public Void run(String[] args) throws Exception {
    if(args.length >= 2){
        LocalDate fromDate;
        LocalDate toDate;
        try {
            fromDate = LocalDate.parse(args[0]);
            toDate = LocalDate.parse(args[1]);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Date must be in format YYYY-MM-DD");
        }


        if (args.length > 2) {
            String hall = args[2];
            System.out.println("Report for hall with id " + hall);
            List<Event> events = Open.events.stream().filter(event -> event.getHallId().equals(hall) && isDateBetweenTwoDates(fromDate, toDate, event.getLocalDate())).toList();
            Map<Event, Long> ticketsPerEvent = new HashMap<>();
            ticketsPerEvent = getTicketsSoldPerEvent(events);
            ticketsPerEvent.forEach((event, count) ->  {
                System.out.println("Event " + event.getNameOfEvent() + " has " + count + " tickets sold");
            });

        }
        else if(args.length == 2){
            System.out.println("Report for all halls");
            List<Event> events = Open.events.stream().filter(event ->  isDateBetweenTwoDates(fromDate, toDate, event.getLocalDate())).toList();
            Map<Event, Long> ticketsPerEvent = getTicketsSoldPerEvent(events);
            for(Hall hall : Open.halls){
                System.out.println("Report for hall with id " + hall.getId());
                for(Map.Entry<Event, Long> entry : ticketsPerEvent.entrySet() ){
                    if(Integer.parseInt(entry.getKey().getHallId()) == hall.getId()){
                        System.out.println("Event " + entry.getKey().getNameOfEvent() + " has " + entry.getValue() + " tickets sold");
                    }
                }

            }
    }
//            Open.halls.stream().forEach( hall -> {System.out.println("Report for hall with id " + hall.getId());
//            Open.events.stream().filter(event -> Integer.parseInt(event.getHallId()));
//
//
//            }).
//            getTicketsSoldPerEvent(events);
        }
        else{
            throw new EmptyReportParametersException("Usage: report <from> <to> [<hall>]");
        }
        return null;
    }

    public Map<Event, Long> getTicketsSoldPerEvent(List<Event> events) {
        Map<Event, Long> ticketsPerEvent;

        if(!events.isEmpty()){ //
            ticketsPerEvent = events.stream().collect
                    (Collectors.toMap(event -> event, event -> Open.purchases.stream()
                            .filter(purchase -> purchase.getTicket().getName().equals(event.getNameOfEvent()) && purchase.getTicket().getDate().equals(event.getLocalDate()))
                            .count()));
        }
        else{
            throw new NoEventsException("No events on this date in this hall");
        }
        return ticketsPerEvent;
    }
}

//
//        if(args.length > 2){
//            String hall = args[2];
//            System.out.println("Report for hall with id " + hall );
//            for(String s : Open.fileContents){
//                if(s.startsWith("Event") && s.contains(hall)){
//                    String[] event = s.split(" ");
//                    LocalDate eventDate = LocalDate.parse(event[1]);
//                    System.out.println("event date  " + eventDate);
//                    if(isDateBetweenTwoDates(fromDate, toDate, eventDate)){
//                        String curEvent = event[2];
//                        int numberOfTicketsForEvent = 0;
//                        for(String line :  Open.fileContents){
//                            if(line.startsWith("Ticket") && line.contains(curEvent)){
//                                String[] ticket = line.split(" ");
//                                String ticketDate = ticket[3];
//                                if(ticketDate.equals(eventDate.toString())){
//                                    if(isDateBetweenTwoDates(fromDate, toDate, LocalDate.parse(ticketDate))){
//                                        numberOfTicketsForEvent++;
//                                    }
//                                }
//
//                            }
//                        }
//                        System.out.println("Number of tickets for event :" + curEvent  + " = " + numberOfTicketsForEvent);
//                    }
//
//                }
//
//            }
//
//        }
//        else{
//            System.out.println("Report for all halls");
//            for(String line : Open.halls){
//                if(line.startsWith("Hall")){
//                    String[] hall = line.split(" ");
//                    String curHallId = hall[1];
//                    System.out.println("Report for hall " + curHallId);
//                    for (String s : Open.fileContents){
//                        if(s.startsWith("Event") && s.contains(curHallId)){
//                            String[] event = s.split(" ");
//                            LocalDate eventDate = LocalDate.parse(event[1]);
//                            if(isDateBetweenTwoDates(fromDate, toDate, eventDate)){
//                                String curEvent = event[2];
//                                int numberOfTicketsForEvent = 0;
//                                for(String entry : Open.fileContents){
//                                    if(entry.startsWith("Ticket") && entry.contains(curEvent) ){
//                                        String[] ticket = entry.split(" ");
//                                        String ticketDate = ticket[3];
//                                        if(ticketDate.equals(eventDate.toString())){
//                                            if(isDateBetweenTwoDates(fromDate, toDate, LocalDate.parse(ticketDate))){
//                                                numberOfTicketsForEvent++;
//                                            }
//                                        }
//                                    }
//                                }
//                                    System.out.println("Number of tickets for event : " + curEvent  + " = " + numberOfTicketsForEvent );
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        return null;
//    }


