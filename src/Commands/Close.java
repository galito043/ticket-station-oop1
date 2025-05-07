package Commands;

import Interfaces.Command;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Close  implements Command <Void, String> {

    public static boolean isClosed;

    public Void run(String[] args) throws IOException {
        Open.fr.close();
        isClosed = true;
        Open.fileContents.clear();
        Open.curFile = null;
        Open.fr = null;
        System.out.println("Closing");
        return null;
    }

}
