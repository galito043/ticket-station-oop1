    package Commands;

    import Exceptions.EmptyEventParametersException;
    import Exceptions.EventAlreadyExistsException;

    import Exceptions.HallNotFoundException;
    import Interfaces.Command;
    import Structures.Event;
    import Structures.Hall;
    import Structures.SessionInformation;


    import java.io.IOException;
    import java.time.LocalDate;
    import java.time.format.DateTimeParseException;


    /**
     * Implements the add-event command adds a new event to the session
     */
    public class AddEvent implements Command<Void, String> {
    private SessionInformation sessionInformation;

        public AddEvent(SessionInformation sessionInformation) {
            this.sessionInformation = sessionInformation;
        }

        @Override
        public Void run(String[] args) throws IOException {


        try{
            if(args == null || args.length < 3){
                throw new EmptyEventParametersException("Usage: <date> <hallId> <eventName>");
            }
            int hallId;
            try{

                hallId = Integer.parseInt(args[1]);
                String date = args[0];

                boolean hallExists = false;
                for(Hall h : sessionInformation.getHalls()){
                    if(hallId == h.getId()){
                        hallExists = true;
                        break;
                    }
                }
                if(!hallExists){
                    throw new HallNotFoundException("Hall with id " + hallId + " does not exist");
                }

                String nameOfEvent = args[2];
                Event newEvent = new Event(LocalDate.parse(date), nameOfEvent.toUpperCase(), hallId );
                if(!date.isEmpty()  && !nameOfEvent.isEmpty()){
                    if(sessionInformation.getEvents().contains(newEvent)){
                        throw new EventAlreadyExistsException("There is a play already on this date in this hall");
                    }
                    System.out.println("Event added " + newEvent.toString());
                    sessionInformation.events.add(newEvent);
                }
                else{
                    throw new EmptyEventParametersException("Usage: <date> <hallId> <eventName>");
                }
            }catch (NumberFormatException e){
                System.out.println("Invalid hallId. Must contain only numbers");
            }


        }catch (EmptyEventParametersException | DateTimeParseException | EventAlreadyExistsException | HallNotFoundException e){
            System.out.println(e.getMessage());
        }
           return null;
        }
    }
