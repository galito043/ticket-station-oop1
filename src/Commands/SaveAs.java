package Commands;

import Interfaces.Command;

import java.io.FileWriter;
import java.io.IOException;

public class SaveAs implements Command <Void, String>{
    public Void run(String[] args) throws IOException {
        String saveAsPath = args[0];
        String content = args[1];
        FileWriter fw = new FileWriter(saveAsPath);
        fw.write(content);
        fw.close();
        return null;
    }
}
