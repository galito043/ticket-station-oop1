package Commands;

import Interfaces.Command;
import Structures.Purchase;
import Structures.SessionInformation;

public class Purchases implements Command<Void, String> {
    private SessionInformation sessionInformation;

    public Purchases(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    @Override
    public Void run(String[] args) throws Exception {
        for(Purchase p :sessionInformation.getPurchases()){
            System.out.println(p.toString());
        }
        return null;
    }
}
