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
import java.util.Date;

public class AddEvent implements Command<Void, String> {

    public Void run(String[] args) throws IOException {

    if(Close.isClosed){
    throw new NoFileOpenException("There is no file open. Open a file first");
    }
        if(args == null || args.length < 3){
            throw new EmptyEventParametersException("One or more parameters is missing. Usage: <date> <hallId> <eventName>");
        }
            String date = args[0];
            String hallId = args[1];
            String nameOfEvent = args[2];
            System.out.println(date);
            Event event = new Event(LocalDate.parse(date), nameOfEvent, hallId );
            if(!date.isEmpty()  && !nameOfEvent.isEmpty() && !hallId.isEmpty()){
                for(String line : Open.fileContents){
                    if(line.equals(event.toString())){
                        throw new EventAlreadyExistsException("There is a play already on this date in this hall");
                    }
                }
                Open.fileContents.add(event.toString());
            }
            else{
                throw new EmptyEventParametersException("One or more parameters is empty. Name of event, hall id and date is needed");
            }
       return null;
    }
}
// Event{date=2025-04-05, nameOfEvent=kafka, hallId=1234}