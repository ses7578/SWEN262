import java.util.ArrayList;
import java.util.HashMap;


/**
 * Class for the Itinerary for each specific person as well as a way to keep everything together
 * @author: Shannon
 */
public class Itinerary
{
    private ArrayList<Flight> flights;
    String origin;
    String dest;
    private static HashMap<Integer, Itinerary> availableItinerary = new HashMap<>();
    private static Integer size = 0;

    /**
     * Creates the itinerary
     * @param flights the flights.txt for the itinerary already
     */
    private Itinerary(ArrayList<Flight> flights, String origin, String dest)
    {
        this.flights = flights;
        this.origin = origin;
        this.dest = dest;
    }

    /**
     * Gets the flights for a trip
     * @param o origin airport
     * @param d destination airport
     * @param connections the amount of connections
     * @return the string of itinerarys
     */
    static String getFlights(Airport o, Airport d, int ... connections)
    {
        // Resets available itineraries
        size = 0;
        availableItinerary = new HashMap<>();

        int min = 0;
        if(connections.length!=0)
            min = connections[0];
        ArrayList<Flight> flight = new ArrayList<>();
        ArrayList<Flight> one = Flight.getFlight(o);
        for(Flight f: one)
        {
            if(f.getDestinationAirport().equals(d))
            {
                flight.add(f);
                ArrayList<Flight> oneFlight = new ArrayList<>();
                oneFlight.add(f);
                availableItinerary.put(size, new Itinerary(oneFlight, o.airportCode, d.airportCode));
                size++;
                break;
            }
        }
        if(min==1)
        {
            int count = -1;
            while (count < one.size() - 1)
            {
                count++;
                ArrayList<Flight> two = Flight.getFlight(one.get(count).getDestinationAirport());
                Flight f = one.get(count);
                Airport dA = f.getDestinationAirport();
                for (Flight x : two)
                {
                    if (x.getDestinationAirport().equals(d))
                    {
                        if(x.getdTime() >= f.getaTime() + dA.getConnectionsMin())
                        {
                            flight.add(f);
                            flight.add(x);
                            ArrayList<Flight> oneFlight = new ArrayList<>();
                            oneFlight.add(f);
                            oneFlight.add(x);
                            availableItinerary.put(size, new Itinerary(oneFlight, o.airportCode, d.airportCode));
                            size++;
                            break;
                        }
                    }
                }
            }
        }
        if(min == 2)
        {
            int count = -1;
            int count2 = -1;
            ArrayList<Flight> two;
            ArrayList<Flight> three;
            while(count<one.size()-1)
            {
                count++;
                two = Flight.getFlight(one.get(count).getDestinationAirport());
                Flight oF = one.get(count);
                while(count2<two.size() -1)
                {
                    count2++;
                    three = Flight.getFlight(two.get(count2).getDestinationAirport());
                    Flight tF = two.get(count2);
                    if(oF.checkIfValid(tF))
                    {
                        for (Flight x : three)
                        {
                            if (x.getDestinationAirport().equals(d))
                            {
                                if(x.checkIfValid(tF))
                                {
                                    flight.add(oF);
                                    flight.add(tF);
                                    flight.add(x);
                                    ArrayList<Flight> oneFlight = new ArrayList<>();
                                    oneFlight.add(oF);
                                    oneFlight.add(tF);
                                    oneFlight.add(x);
                                    availableItinerary.put(size, new Itinerary(oneFlight, o.airportCode, d.airportCode));
                                    size++;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        StringBuilder s = new StringBuilder("info," + availableItinerary.size());
        for(Itinerary i : availableItinerary.values()){
            s.append(i.toString());
        }
        return s.toString();
    }

    /**
     * Gets the info for a trip
     * @param o origin airport
     * @param d destination airport
     * @param sort the way to sort
     * @param connections the amount of connections
     * @return the string of itinerarys
     */
    static String getFlights(Airport o, Airport d, String sort, int ... connections)
    {
        getFlights(o, d, connections);
        SortAlgo sortAlgo;
        switch(sort){
            case "departure":
                sortAlgo = new SortByDeparture();
                break;
            case "arrival":
                sortAlgo = new SortByArrival();
                break;
            case "airfare":
                sortAlgo = new SortByAirfare();
                break;
            default:
                return "invalidSort";
        }
        HashMap<Integer, Itinerary> holder = new HashMap<>();
        int count = 0;
        for(Integer key:availableItinerary.keySet())
        {
            ArrayList<Flight> f = availableItinerary.get(key).getFlights();
            sortAlgo.sortOrder(f);
            holder.put(count, new Itinerary(f, o.getAirportCode(), d.getAirportCode()));
            count++;
        }
        availableItinerary = holder;
        StringBuilder s = new StringBuilder("info," + availableItinerary.size());
        for(Itinerary i : availableItinerary.values()){
            s.append(i.toString());
        }
        return s.toString();
    }

    /**
     * @param id the id of the itinerary being looked for
     * @return the itinerary
     */
    static Itinerary getItinerary(int id)
    {
        return availableItinerary.get(id-1);
    }

    /**
     * @return the flights
     */
    ArrayList<Flight> getFlights()
    {
        return flights;
    }

    /**
     * @return the airfare for the flight
     */
    private int getAirfare()
    {
        int airfare = 0;
        if(flights == null)
            return 0;
        for(Flight f: flights)
        {
            airfare += f.getAirfare();
        }
        return airfare;
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder("<nl>" + getAirfare() + "," + flights.size());
        for(Flight f: flights)
        {
            s.append(f);
        }
        return s.toString();
    }

 }
