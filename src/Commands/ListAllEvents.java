package Commands;

import Interfaces.Command;
import Structures.Event;
import Structures.SessionInformation;
/**
 * Lists all events
 */
public class ListAllEvents implements Command<Void,String> {
    private SessionInformation sessionInformation;

    public ListAllEvents(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    @Override
    public Void run(String[] args) throws Exception {
        for(Event event : sessionInformation.getEvents()){
            System.out.println(event.toString());
        }
        return null;
    }
}
