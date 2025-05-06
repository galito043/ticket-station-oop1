package Commands;

import Interfaces.Command;
import Structures.Hall;

public class AddHall implements Command<Void,String> {

    @Override
    public Void run(String[] args) throws Exception {
        int hallNumber = Integer.parseInt(args[0]);
        System.out.println(hallNumber);
        int rows = Integer.parseInt(args[1]);
        int columns = Integer.parseInt(args[2]);
        Open.fileContents.add(new Hall(hallNumber, rows, columns).toString());
        return null;
    }
}
