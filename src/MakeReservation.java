public class MakeReservation {
    private Itineray itinerayID;
    private String passenger;
    boolean alreadyExists;

    public MakeReservation(Itineray i, String p)
    {
        itinerayID = i;
        passenger = p;
    }

    public String executeCommand()
    {
        return "";
    }

}
