public class Flight {
    Airport originAirport;
    Airport destinationAirport;
    String departureTime;
    String arrivalTime;
    double airfare;

    public Flight(Airport o, Airport d, String dT, String aT, double a)
    {
        originAirport = o;
        destinationAirport = d;
        departureTime = dT;
        arrivalTime = aT;
        airfare = a;
    }

    public String toString()
    {
        return "";
    }

}
