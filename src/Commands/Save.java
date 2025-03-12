package Commands;

import Interfaces.Command;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Save implements Command <Void, String> {
    public Void run(String[] args) throws IOException {
        FileWriter fw = new FileWriter("Filepath", true);
        return null;
    }
}
