package Commands;

import Interfaces.Command;

import java.io.File;
import java.io.FileReader;

public class Close  implements Command <Void, String> {

    public static boolean isClosed;

    public Void run(String[] args) throws Exception {
        Open.fr.close();
        isClosed = true;
        Open.fileContents.clear();
        System.out.println("Closing");
        return null;
    }

}
