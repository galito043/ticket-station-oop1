package Commands;

import Interfaces.Command;
import Structures.Event;
import Structures.SessionInformation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

        list = list.stream().sorted((i1,i2) -> i2.getValue().compareTo(i1.getValue())).toList();
//        Collections.sort(list, (i1, i2) -> i1.getValue().compareTo(i2.getValue()));
        System.out.println("#########Statistics#########");
        System.out.println("Top three most watched plays");
        System.out.println(list.get(0).getKey().toString() + " has " + list.get(0).getValue() + " bought or reserved tickets");
        System.out.println(list.get(1).getKey().toString() + " has " + list.get(2).getValue() + " bought or reserved tickets");
        System.out.println(list.get(2).getKey().toString() + " has " + list.get(2).getValue() + " bought or reserved tickets");



        return null;

    }

}
