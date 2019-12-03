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
    public String origin;
    public String dest;
    private static ArrayList<Itinerary> itineraries = new ArrayList<>();
    private static HashMap<Integer, Itinerary> availableItinerary = new HashMap<>();
    static Integer size = 0;

    /**
     * Creates the itinerary
     * @param flights the flights.txt for the itinerary already
     */
    Itinerary(ArrayList<Flight> flights, String origin, String dest)
    {
        this.flights = flights;
        this.origin = origin;
        this.dest = dest;
//        itineraries.add(this);
    }

//    Itinerary(ArrayList<Flight> flights)
//    {
//
//    }

//    /**
//     * adds a flight to the list
//     * @param f the flight
//     * @param passenger the passenger
//     */
//    static void addFlight(Flight f, String passenger)
//    {
//        if(itinerary.containsKey(passenger))
//            itinerary.get(passenger).add(f);
//        else {
//            ArrayList<Flight> flights1 = new ArrayList<>();
//            flights1.add(f);
//            itinerary.put(passenger, flights1);
//        }
//    }

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

        // TODO - Sort availableItinerary based off of the given sorting method

        StringBuilder s = new StringBuilder("info," + availableItinerary.size());

        for(Itinerary i : availableItinerary.values()){
            s.append(i.toString());
        }

        return s.toString();
    }



//    /**
//     * gets the itinerary for a specific person
//     * @param p the name of the passenger whose Itinerary
//     * @return Returns the string of the itinerary for the specific
//     */
//    static String getItinerary(String p)
//    {
//        ArrayList<Flight> f = itinerary.get(p);
//        Itinerary i = new Itinerary(f, p);
//        return i.toString();
//    }

    static Itinerary getItinerary(int id)
    {
        return availableItinerary.get(id-1);
    }

    private int getPrice()
    {
        int price = 0;
        if(flights == null)
            return 0;
        for(Flight f: flights)
        {
            price += f.getAirfare();
        }
        return price;
    }

//    /**
//     * removes a flight from the itinerary
//     * @param p the passenger
//     * @param origin the origin airport
//     * @param destination the destination airport
//     */
//    static void removeFlight(String p, Airport origin, Airport destination)
//    {
//        ArrayList<Flight> flights = itinerary.get(p);
//        for(Flight f: flights)
//        {
//            if(f.getOriginAirport().equals(origin)&&f.getDestinationAirport().equals(destination))
//            {
//                flights.remove(f);
//            }
//        }
//        itinerary.remove(p);
//        itinerary.put(p, flights);
//    }

//    public ArrayList<Itinerary> getAvailibleItineraries(int iD){
//        return availableItinerary.get();
//    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder("\n" + getPrice() + "," + flights.size());
        for(Flight f: flights)
        {
            s.append(f);
        }
        return s.toString();
    }

 }
