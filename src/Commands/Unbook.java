package Commands;

import Interfaces.Command;

public class Unbook implements Command<Void, String> {
    @Override
    public Void run(String[] args) throws Exception {
        String row = args[0];
        String seat = args[1];
        String date = args[2];
        String name = args[3];


        Open.fileContents.removeIf(s -> s.contains("Booking") && s.contains(row) && s.contains(seat) && s.contains(date) && s.contains(name));
        System.out.println("Removed "+  "Booking " + String.join(" ", args));
        return null;
    }
}
