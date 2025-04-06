package Commands;

import Exceptions.EventAlreadyExistsException;
import Interfaces.Command;
import Structures.Event;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class AddEvent implements Command<Void, String> {

    public Void run(String[] args) throws IOException {
        FileReader fr = Open.fr;
        BufferedReader br = new BufferedReader(fr);
//        LocalDate date = LocalDate.parse(args[0]);
        String nameOfEvent = args[2];
        String hallId = args[1];
        String date = args[0];
        System.out.println(date);
        Event event = new Event(LocalDate.parse(date), nameOfEvent, hallId );

        if(date != null  && !nameOfEvent.isEmpty() && !hallId.isEmpty()){
            for(String line : Open.fileContents){
                if(line.equals(event.getHallId()) && line.equals(event.getLocalDate().toString())){
                    throw new EventAlreadyExistsException("There is a play already on this date in this hall");
                }
            }
            Open.fileContents.add(event.toString());
        }
       return null;
    }
}
