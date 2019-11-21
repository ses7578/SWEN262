public class GetReservation {

    private String passenger;
    private Airport originAirport;
    private Airport destinationAirport;

    public GetReservation(String p, Airport o, Airport d){
        passenger = p;
        originAirport = o;
        destinationAirport = d;
    }

    public GetReservation(String p, Airport o){
        passenger = p;
        originAirport = o;
    }

    public GetReservation(String p)
    {
        passenger = p;
    }

    public String executeCommand(){
        return "";
    }
}
