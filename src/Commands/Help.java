package Commands;

import Interfaces.Command;

public class Help implements Command <Void, String>{
    public Void run(String[] args){
        System.out.println("Options");
        return null;
    }
}
