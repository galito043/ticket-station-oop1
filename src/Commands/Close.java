package Commands;

import Interfaces.Command;

import java.io.File;
import java.io.FileReader;

public class Close  implements Command <Void, String> {


    public Void run(String[] args) throws Exception {
        Open.fr.close();
        Open.fileContents.clear();
        System.out.println("CLosing");
        return null;
    }

}
