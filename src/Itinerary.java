import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class for the Itinerary for each specific person as well as a way to keep everything together
 * @author: Shannon
 */
public class Itinerary
{
    private ArrayList<Flight> flights;
    private String passenger;
    private static HashMap<String, ArrayList<Flight>> itinerary = new HashMap<>();

    /**
     * Creates the itinerary
     * @param flights the flights.txt for the itinerary already
     * @param passenger the passenger who's itinerary is
     */
    Itinerary(ArrayList<Flight> flights, String passenger)
    {
        this.flights = flights;
        this.passenger = passenger;
        itinerary.put(passenger, flights);
    }

    /**
     * adds a flight to the list
     * @param f the flight
     * @param passenger the passenger
     */
    static void addFlight(Flight f, String passenger)
    {
        if(itinerary.containsKey(passenger))
            itinerary.get(passenger).add(f);
        else {
            ArrayList<Flight> flights1 = new ArrayList<>();
            flights1.add(f);
            itinerary.put(passenger, flights1);
        }
    }

    /**
     * gets the itinerary for a specific person
     * @param p the name of the passenger whose Itinerary
     * @return Returns the string of the itinerary for the specific
     */
    static String getItinerary(String p)
    {
        ArrayList<Flight> f = itinerary.get(p);
        Itinerary i = new Itinerary(f, p);
        return i.toString();
    }

    static int getPrice(String p)
    {
        int price = 0;
        ArrayList<Flight> flights = itinerary.get(p);
        for(Flight f: flights)
        {
            price += f.getAirfare();
        }
        return price;
    }

    /**
     * removes a flight from the itinerary
     * @param p the passenger
     * @param origin the origin airport
     * @param destination the destination airport
     */
    static void removeFlight(String p, Airport origin, Airport destination)
    {
        ArrayList<Flight> flights = itinerary.get(p);
        for(Flight f: flights)
        {
            if(f.getOriginAirport().equals(origin)&&f.getDestinationAirport().equals(destination))
            {
                flights.remove(f);
            }
        }
        itinerary.remove(p);
        itinerary.put(p, flights);
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder(passenger + "'s Itinerary:\n");
        for(Flight f: flights)
        {
            s.append(f).append("\n");
        }
        return s.toString();
    }

 }
