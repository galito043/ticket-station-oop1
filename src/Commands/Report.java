package Commands;

import Exceptions.EmptyReportParametersException;
import Exceptions.InvalidDateException;
import Exceptions.NoEventsException;
import Interfaces.Command;
import Structures.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Report implements Command <Void,String> {
    private SessionInformation sessionInformation;

    public Report(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

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


        if (args.length  == 3) {
            String hallId = args[2];
            System.out.println("Report for hall with id " + hallId);
//            List<Event> events = Open.events.stream().filter(event -> event.getHallId().equals(hall) && isDateBetweenTwoDates(fromDate, toDate, event.getLocalDate())).toList();
            List<Event> events = new ArrayList<>();
            for(Event e : sessionInformation.getEvents()){
                if(e.getHallId().equals(hallId) && isDateBetweenTwoDates(fromDate, toDate, e.getLocalDate())){
                    events.add(e);
                }
            }
            Map<Event, Long> ticketsPerEvent = new HashMap<>();
            ticketsPerEvent = getTicketsSoldPerEvent(events);
            ticketsPerEvent.forEach((event, count) ->  {
                System.out.println("Event " + event.getNameOfEvent() + " has " + count + " tickets sold");
            });

        }
        else if(args.length == 2){
            System.out.println("Report for all halls");
            List<Event> events = new ArrayList<>();
//            List<Event> events = Open.events.stream().filter(event ->  isDateBetweenTwoDates(fromDate, toDate, event.getLocalDate())).toList();
            for(Event e : sessionInformation.getEvents()){
                if(isDateBetweenTwoDates(fromDate, toDate, e.getLocalDate())){
                    events.add(e);
                }
            }

            Map<Event, Long> ticketsPerEvent = getTicketsSoldPerEvent(events);
            for(Hall hall : sessionInformation.getHalls()){
                System.out.println("Report for hall with id " + hall.getId());
                for(Map.Entry<Event, Long> entry : ticketsPerEvent.entrySet() ){
                    if(Integer.parseInt(entry.getKey().getHallId()) == hall.getId()){
                        System.out.println("Event " + entry.getKey().getNameOfEvent() + " has " + entry.getValue() + " tickets sold");
                    }
                }

            }
    }

        }
        else{
            throw new EmptyReportParametersException("Usage: report <from> <to> [<hall>]");
        }
        return null;
    }

    public Map<Event, Long> getTicketsSoldPerEvent(List<Event> events) {
        Map<Event, Long> ticketsPerEvent = new HashMap<>();

        if(!events.isEmpty()){ //
            for(Event e : sessionInformation.getEvents()){
                Long count = 0L;
                for(Purchase p : sessionInformation.getPurchases()){
                    if(p.getTicket().getName().equals(e.getNameOfEvent()) && e.getLocalDate().equals(p.getTicket().getDate())){
                        count++;
                    }
                }
                ticketsPerEvent.put(e, count);

            }
//            ticketsPerEvent = events.stream().collect
//                    (Collectors.toMap(event -> event, event -> sessionInformation.stream()
//                            .filter(purchase -> purchase.getTicket().getName().equals(event.getNameOfEvent()) && purchase.getTicket().getDate().equals(event.getLocalDate()))
//                            .count()));
        }

        return ticketsPerEvent;
    }
}



