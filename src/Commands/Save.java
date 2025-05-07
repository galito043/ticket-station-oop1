package Commands;

import Interfaces.Command;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Save implements Command <Void, String> {
    public Void run(String[] args) throws IOException {

        FileWriter fw = new FileWriter(Open.curFile);
        Open.fileContents = Stream.of(Open.bookings, Open.purchases, Open.events).flatMap(Collection::stream).map(obj -> obj.toString() + "\n").collect(Collectors.toList());
        for (String line : Open.fileContents) {
            fw.write(line);
        }
        fw.close();

        return null;
    }
}
