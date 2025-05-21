package Enums;

import Exceptions.UnknownCommandException;

public enum CommandType {
    OPEN("open"),
    EXIT("exit"),
    CLOSE("close"),
    HELP("help"),
    SAVE("save"),
    SAVE_AS("saveas"),
    ADD_EVENT("addevent"),
    FREE_SEATS("freeseats"),
    BOOK("book"),
    UNBOOK("unbook"),
    BUY("buy"),
    BOOKINGS("bookings"),
    CHECK("check"),
    REPORT("report"),
    LIST_ALL_EVENTS("listallevents"),
    MOST_WATCHED("mostwatched"),
    LEAST_WATCHED("leastwatched"),
    PURCHASES("purchases");



private final String command;

    CommandType(String command) {
        this.command = command;
    }
    public String getCommand(){
        return command;
    }
    public static CommandType fromString(String keyword){
        try{
            for(CommandType type : CommandType.values()){
                if(type.command.equalsIgnoreCase(keyword)){
                    return type;
                }
            }
            throw new UnknownCommandException("Unkown command");
        }catch (UnknownCommandException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
