public class DeleteReservation {
    String passenger;
    Airport originAirport;
    Airport destinationAirport;

    public DeleteReservation(String p, Airport o, Airport d)
    {
        passenger = p;
        originAirport = o;
        destinationAirport = d;
    }

    public String executeCommand(){
        return "";
    }
}
