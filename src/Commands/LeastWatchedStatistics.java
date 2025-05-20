package Commands;

import Interfaces.Command;
import Structures.Event;
import Structures.SessionInformation;

import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LeastWatchedStatistics implements Command<Void,String> {
SessionInformation sessionInformation;

    public LeastWatchedStatistics(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    @Override
    public Void run(String[] args) throws Exception {
        try{
            Report report = new Report(sessionInformation);
            FreeSeats freeSeats = new FreeSeats(sessionInformation);

            Map<Event,Long> events = report.getTicketsSoldPerEvent(sessionInformation.getEvents());
            List<Map.Entry<Event, Long>> list = new LinkedList<>(events.entrySet());
            List<Map.Entry<Event, Long>> leastWatched = new LinkedList<>();

            for(Map.Entry<Event,Long> entry : list){
                int numberOfSeats = freeSeats.getTotalSeatsInHall(entry.getKey().getHallId());
                if(entry.getValue() <= numberOfSeats * 0.10){
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
                FileWriter fileWriter = new FileWriter("src/TestFiles/leastWatchedStatistics.txt");
                for(Map.Entry<Event, Long> entry : leastWatched){
                    fileWriter.write(entry.getKey().toString() + " taken seats " + entry.getValue() + "\n");
                }
                fileWriter.close();
            }
            else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }
}
