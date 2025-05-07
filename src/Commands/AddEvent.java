package Commands;

import Exceptions.EmptyEventParametersException;
import Exceptions.EventAlreadyExistsException;
import Exceptions.NoFileOpenException;
import Interfaces.Command;
import Structures.Event;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class AddEvent implements Command<Void, String> {

    public Void run(String[] args) throws IOException, DateTimeParseException {


        if(args == null || args.length < 3){
            throw new EmptyEventParametersException("Usage: <date> <hallId> <eventName>");
        }
            String date = args[0];
            String hallId = args[1];
            String nameOfEvent = args[2];
            Event newEvent = new Event(LocalDate.parse(date), nameOfEvent, hallId );
            if(!date.isEmpty()  && !nameOfEvent.isEmpty() && !hallId.isEmpty()){
                for(Event curEvent : Open.events){
                    if(curEvent.equals(newEvent)){
                        throw new EventAlreadyExistsException("There is a play already on this date in this hall");
                    }
                }
                Open.fileContents.add(newEvent.toString());
                Open.events.add(newEvent);
            }
            else{
                throw new EmptyEventParametersException("Usage: <date> <hallId> <eventName>");
            }
       return null;
    }
}
// Event{date=2025-04-05, nameOfEvent=kafka, hallId=1234}