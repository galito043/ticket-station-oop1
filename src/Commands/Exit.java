package Commands;

import Interfaces.Command;

import java.io.FileReader;
import java.io.IOException;

public class Exit implements Command {
    public void run() {
        ;
    }

    @Override
    public Void run(Object[] args) throws Exception {
        System.exit(0);
        return null;
    }
}
