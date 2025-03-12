package Commands;

import Interfaces.Command;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Open implements Command <List<String>, String>{


    public List<String> run(String[] args) throws IOException {
        FileReader fr = new FileReader("info.txt");
        BufferedReader br = new BufferedReader(fr);
        List<String> lines = new ArrayList<>();
        while (br.ready()) {
            lines.add(br.readLine());
        }
        return lines;
    }


}
