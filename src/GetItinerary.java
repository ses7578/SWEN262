public class GetItinerary {
    private Airport originAirport;
    private Airport destinationAirport;
    private int maxConnections;
    private Itineray itineray;

    public GetItinerary(Airport o, Airport d, int i, Itineray itineray)
    {
        originAirport = o;
        destinationAirport = d;
        maxConnections = i;
        this.itineray = itineray;
    }

    public GetItinerary(Airport o, Airport d, int i)
    {
        originAirport = o;
        destinationAirport = d;
        maxConnections = i;
    }

    public GetItinerary(Airport o, Airport d)
    {
        originAirport = o;
        destinationAirport = d;
    }

    public void executeCommand()
    {

    }
}
