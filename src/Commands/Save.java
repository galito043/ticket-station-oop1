package Commands;

import Interfaces.Command;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Save implements Command <Void, String> {
    public Void run(String[] args) throws IOException {

        FileWriter fw = new FileWriter(Open.curFile);
        for(String s : Open.fileContents){
            if(!s.isEmpty()){
                fw.write(s + "\n");
            }

        }
//        FileWriter fw = new FileWriter(Open.curFile, true);
//        for(String s : Open.newFileContents){
//            fw.write("\n" + s);
//        }
        fw.close();

        return null;
    }
}
