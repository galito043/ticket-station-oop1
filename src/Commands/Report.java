package Commands;

import Exceptions.*;
import Interfaces.Command;
import Structures.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.*;
import java.util.stream.Collectors;
/**
 * Implements the report command which prints plays tickets bought in a date range from,to in a specified hall or in all halls
 */
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
        try{
            HashSet<Event> events = new HashSet<>();
            if(args.length  < 2 || args.length > 3){
                throw new EmptyReportParametersException("Usage: report <from> <to> [<hall>]");

            }
            else {
                LocalDate fromDate;
                LocalDate toDate;
                try {
                    fromDate = LocalDate.parse(args[0]);
                    toDate = LocalDate.parse(args[1]);
                } catch (DateTimeParseException e) {
                    throw new InvalidDateException("Date must be in format YYYY-MM-DD");
                }
                if(toDate.isBefore(fromDate)){
                    throw new InvalidDateException("To date must not be before from date");
                }
                int hallId = 0;
                if (args.length  == 3) {
                    try{
                        hallId = Integer.parseInt(args[2]);
                    }catch (NumberFormatException e){
                        System.out.println("Hall ID must be a number");
                    }
                    
                    
                    if(!doesHallExist(hallId)){
                        throw new HallNotFoundException("Hall does not exist");
                    }
                    System.out.println("Report for hall with id " + hallId);
                    for(Event e : sessionInformation.getEvents()){
                        if(e.getHallId() == hallId && isDateBetweenTwoDates(fromDate, toDate, e.getLocalDate())){
                            events.add(e);
                        }
                    }
                    if(events.isEmpty()){
                        throw new NoEventsException("No events found between " + fromDate + " and " + toDate);
                    }

                    Map<Event, Long> ticketsPerEvent = new HashMap<>();
                    ticketsPerEvent = getTicketsSoldPerEvent(events);

                    for(Map.Entry<Event, Long> entry : ticketsPerEvent.entrySet()){
                        System.out.println("Event " + entry.getKey().getNameOfEvent() + " on " + entry.getKey().getLocalDate().toString()+ " has " + entry.getValue() + " tickets sold");
                    }


                }
                else if(args.length == 2){
                    System.out.println("Report for all halls");
                    for(Event e : sessionInformation.getEvents()){
                        if(isDateBetweenTwoDates(fromDate, toDate, e.getLocalDate())){
                            events.add(e);
                        }
                    }
                    if(events.isEmpty()){
                        throw new NoEventsException("No events between these dates");

                    }

                    Map<Event, Long> ticketsPerEvent = getTicketsSoldPerEvent(events);
                    boolean anyTicketsSold = false;
                    for(Map.Entry<Event,Long> entry: ticketsPerEvent.entrySet()){
                        if(entry.getValue() > 0){
                            anyTicketsSold = true;
                        }
                    }
                    if (!anyTicketsSold) {
                        throw new NoTicketsException(
                                "No tickets sold for any events between " + fromDate + " and " + toDate
                        );
                    }

                    for(Hall hall : sessionInformation.getHalls()){
                        System.out.println("Report for hall with id " + hall.getId());
                        for(Map.Entry<Event, Long> entry : ticketsPerEvent.entrySet() ){
                            if(entry.getKey().getHallId() == hall.getId()){
                                System.out.println("Event " + entry.getKey().getNameOfEvent() + " on " + entry.getKey().getLocalDate().toString()+ " has " + entry.getValue() + " tickets sold");
                            }
                        }

                    }

                }
            }

        }catch (InvalidDateException | EmptyReportParametersException | NoEventsException | NoTicketsException | HallNotFoundException | DateTimeParseException e){
            System.out.println(e.getMessage());
        }


        return null;
    }

    public Map<Event, Long> getTicketsSoldPerEvent(HashSet<Event> events) {
        Map<Event, Long> ticketsPerEvent = new HashMap<>();

        if(!events.isEmpty()){ //
            for(Event e : events){
                Long count = 0L;
                for(Purchase p : sessionInformation.getPurchases()){
                    if(p.getTicket().getName().equals(e.getNameOfEvent()) && e.getLocalDate().equals(p.getTicket().getDate())){
                        count++;
                    }
                }
                ticketsPerEvent.put(e, count);

            }

        }

        return ticketsPerEvent;
    }
    public boolean doesHallExist(int curEventHallIdId){
        for(Hall h : sessionInformation.getHalls()){
            if(curEventHallIdId == h.getId()){
                return true;
            }
        }

        return false;
    }
}



