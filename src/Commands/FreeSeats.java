package Commands;

import Interfaces.Command;

import java.util.Date;

public class FreeSeats implements Command<Void, String> {


public int getTotalSeatsInHall(String hallId){
    String[] curFileContents = Open.fileContents.toArray(new String[0]);
    String stringValHallId = hallId;
    String seats;
    for(String s : curFileContents){
        if(s.startsWith("Structures.Hall") && s.contains(stringValHallId)) {
            String[] splitLine =  s.split(" ");
            return Integer.parseInt(splitLine[2]) * Integer.parseInt(splitLine[3]);

        }
    }

    return 0;
}
    @Override
    public Void run(String[] args) throws Exception {
        String date;
        String eventName;
        if(args.length >= 1){
            date = args[0];
           eventName = args[1];
        }
        else{
            date = "2025-03-22";
            eventName ="play";
        }
        int totalSeatsInHall = 0;
        int takenSeats = 0;

            for(String s : Open.fileContents){

                if(s.startsWith("Structures.Ticket") && s.contains(eventName) && s.contains(date)){
                    takenSeats++;
                }


            }

            for(String s: Open.fileContents){
                if(s.startsWith("Event") && s.contains(eventName) && s.contains(date)){
                    String[] splitLine = s.split(" ");
                    totalSeatsInHall = getTotalSeatsInHall(splitLine[2]);
                }
            }

        System.out.println(totalSeatsInHall - takenSeats);
            return null;
    }
}
