package Commands;

import Interfaces.Command;

import java.io.File;
import java.io.FileReader;

public class Close  implements Command <Void, FileReader> {


    public Void run(FileReader[] args) throws Exception {
        FileReader fr = args[0];
        fr.close();
        return null;
    }

}
