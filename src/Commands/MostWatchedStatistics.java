package Commands;

import Exceptions.NoEventsToShowException;
import Interfaces.Command;
import Structures.Event;
import Structures.SessionInformation;

import java.util.*;

public class MostWatchedStatistics implements Command<Void,String> {
    SessionInformation sessionInformation;

    public MostWatchedStatistics(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    @Override
    public Void run(String[] args) throws Exception {
        Report report = new Report(sessionInformation);
        Map<Event,Long> events = report.getTicketsSoldPerEvent(sessionInformation.getEvents());
        List<Map.Entry<Event, Long>> list = new LinkedList<>(events.entrySet());

        list.sort(new Comparator<Map.Entry<Event, Long>>() {
            @Override
            public int compare(Map.Entry<Event, Long> o1, Map.Entry<Event, Long> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        int eventsToShow = Math.min(3, list.size());
        if(eventsToShow == 0 ){
            throw new NoEventsToShowException("No events to show");
        }
        else{
            System.out.println("#########Statistics#########");
            System.out.println("Top three most watched plays");
            for(int i = 0; i < eventsToShow; i++){
                System.out.println(list.get(i).getKey().toString() + " has " + list.get(i).getValue() + " bought or reserved tickets");
            }
        }


        return null;

    }

}
