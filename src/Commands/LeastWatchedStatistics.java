package Commands;

import Exceptions.EmptyParametersException;
import Exceptions.InvalidDateException;
import Interfaces.Command;
import Structures.Event;
import Structures.SessionInformation;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 * Implements "leastwatched" -  lists events with less than 10% occupancy,
 * and optionally writes them to a file.
 */
public class LeastWatchedStatistics implements Command<Void,String> {
SessionInformation sessionInformation;

    public LeastWatchedStatistics(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    @Override
    public Void run(String[] args) throws Exception {
        try{
            if(args.length < 2){
                throw new EmptyParametersException("Usage leastwatched <fromdate> <todate>");
            }
            LocalDate fromDate = null;
            LocalDate toDate = null;
            try{
                fromDate = LocalDate.parse(args[0]);
                toDate = LocalDate.parse(args[1]);
            }catch (DateTimeParseException e){
                System.out.println("Could not parse date ");
                return null;
            }
            if(toDate.isBefore(fromDate)){
                throw new InvalidDateException("To date must not be before from date");
            }
            Report report = new Report(sessionInformation);
            FreeSeats freeSeats = new FreeSeats(sessionInformation);

            Map<Event,Long> events = report.getTicketsSoldPerEvent(sessionInformation.getEvents());
            List<Map.Entry<Event, Long>> list = new LinkedList<>(events.entrySet());
            List<Map.Entry<Event, Long>> leastWatched = new LinkedList<>();

            for(Map.Entry<Event,Long> entry : list){
                int numberOfSeats = freeSeats.getTotalSeatsInHall(entry.getKey().getHallId());
                if(entry.getValue() < numberOfSeats * 0.10 && report.isDateBetweenTwoDates(fromDate, toDate, entry.getKey().getLocalDate())){
                    leastWatched.add(entry);
                }
            }
            System.out.println("Plays with less than 10% filled capacity ");
            for(Map.Entry<Event, Long> entry : leastWatched){
                System.out.println(entry.getKey() + " " + " visitors " + entry.getValue());
            }
            System.out.println("Would you like to download these statistics to a file? y/n");
            Scanner scanner = new Scanner(System.in);
            String agreement = scanner.nextLine();
            if(agreement.equalsIgnoreCase("y")){
                try{
                    FileWriter fileWriter;
                    System.out.println("Enter file name or press enter for default save path");
                    String line = scanner.nextLine();
                    if(line.isEmpty()){
                        fileWriter = new FileWriter("src/TestFiles/leastWatchedStatistics.txt");
                    }
                    else{
                        fileWriter = new FileWriter(line);

                    }
                    for(Map.Entry<Event, Long> entry : leastWatched){
                        fileWriter.write(entry.getKey().toString() + " taken seats " + entry.getValue() + "\n");
                    }
                    fileWriter.close();
                }catch (IOException e){
                    System.out.println("Invalid path or file name");
                }

            }
            else{
                return null;
            }
        }catch (EmptyParametersException | InvalidDateException e){
            System.out.println(e.getMessage());
        }

        return null;
    }
}
