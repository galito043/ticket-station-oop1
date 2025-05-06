package Commands;

import Interfaces.Command;

import java.time.LocalDate;
import java.util.Date;

public class Report implements Command <Void,String> {

    @Override
    public Void run(String[] args) throws Exception {

        LocalDate fromDate = LocalDate.parse(args[0]);
        LocalDate toDate = LocalDate.parse(args[1]);

        if(args.length > 2){
            String hall = args[2];
            System.out.println("Report for hall with id " + hall );
            for(String s : Open.fileContents){
                if(s.startsWith("Event") && s.contains(hall)){
                    String[] event = s.split(" ");
                    LocalDate eventDate = LocalDate.parse(event[1]);
                    System.out.println("event date  " + eventDate);
                    if(isDateBetweenTwoDates(fromDate, toDate, eventDate)){
                        String curEvent = event[2];
                        int numberOfTicketsForEvent = 0;
                        for(String line :  Open.fileContents){
                            if(line.startsWith("Ticket") && line.contains(curEvent)){
                                String[] ticket = line.split(" ");
                                String ticketDate = ticket[3];
                                if(ticketDate.equals(eventDate.toString())){
                                    if(isDateBetweenTwoDates(fromDate, toDate, LocalDate.parse(ticketDate))){
                                        numberOfTicketsForEvent++;
                                    }
                                }

                            }
                        }
                        System.out.println("Number of tickets for event :" + curEvent  + " = " + numberOfTicketsForEvent);
                    }

                }

            }

        }
        else{
            System.out.println("Report for all halls");
            for(String line : Open.halls){
                if(line.startsWith("Hall")){
                    String[] hall = line.split(" ");
                    String curHallId = hall[1];
                    System.out.println("Report for hall " + curHallId);
                    for (String s : Open.fileContents){
                        if(s.startsWith("Event") && s.contains(curHallId)){
                            String[] event = s.split(" ");
                            LocalDate eventDate = LocalDate.parse(event[1]);
                            if(isDateBetweenTwoDates(fromDate, toDate, eventDate)){
                                String curEvent = event[2];
                                int numberOfTicketsForEvent = 0;
                                for(String entry : Open.fileContents){
                                    if(entry.startsWith("Ticket") && entry.contains(curEvent) ){
                                        String[] ticket = entry.split(" ");
                                        String ticketDate = ticket[3];
                                        if(ticketDate.equals(eventDate.toString())){
                                            if(isDateBetweenTwoDates(fromDate, toDate, LocalDate.parse(ticketDate))){
                                                numberOfTicketsForEvent++;
                                            }
                                        }
                                    }
                                }
                                    System.out.println("Number of tickets for event : " + curEvent  + " = " + numberOfTicketsForEvent );
                            }
                        }
                    }
                }
            }
        }

        return null;
    }
    public boolean isDateBetweenTwoDates(LocalDate fromDate, LocalDate toDate, LocalDate dateToCheck){
        return !dateToCheck.isBefore(fromDate) && !dateToCheck.isAfter(toDate);
    }
}
