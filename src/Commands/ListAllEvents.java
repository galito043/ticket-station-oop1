package Commands;

import Interfaces.Command;
import Structures.Event;

public class ListAllEvents implements Command<Void,String> {

    @Override
    public Void run(String[] args) throws Exception {
        for(Event event : Open.events){
            System.out.println(event.toString());
        }
        return null;
    }
}
