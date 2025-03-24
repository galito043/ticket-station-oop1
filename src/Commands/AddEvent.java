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
        Event event = new Event(LocalDate.parse(date), nameOfEvent, hallId );

        if(date != null  && !nameOfEvent.isEmpty() && !hallId.isEmpty()){
            while(br.ready()){
                String curLine = br.readLine();
                if(curLine.startsWith("Event") && curLine.contains(hallId) && curLine.contains(date.toString())){
                    throw new EventAlreadyExistsException("There is a play already on this date in this hall");
                }
            }
            String newLine = String.format("Event %s %s %s",date,hallId, nameOfEvent);
            Open.newFileContents.add(newLine);
            return null;
        }

       return null;
    }
}
