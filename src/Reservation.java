import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * @author Shannon Sloan
 */
public class Reservation
{
    private static ArrayList<Reservation> reservation = new ArrayList<>();
    private String passenger;
    private Flight flight;

    /**
     * Creates a reservation
     * @param p passenger
     * @param f flight
     */
    public Reservation(String p, Flight f)
    {
        passenger = p;
        flight = f;
        reservation.add(this);
        //reservations.put(p, f);
        Itinerary.addFlight(f, p);
    }

    /**
     * Gets the reservation
     * @param p passenger
     * @param origin the starting destination
     * @param destination the ending destination
     * @return the reservation
     */
    static Reservation getReservation(String p, Airport origin, Airport destination)
    {
        for(Reservation r: reservation)
        {
            if(r.passenger.equals(p)&&r.flight.getOriginAirport().equals(origin)&&(r.flight.getDestinationAirport().equals(destination)))
                return r;
        }
        return null;
    }

    /**
     * Makes a specific reservation
     * @param p the passenger
     * @param origin the origin airport
     * @param destination the destination airport
     */
    public static String makeReservation(String p, Airport origin, Airport destination)
    {
        ArrayList<Flight> flight= Itinerary.getFlights(origin, destination, 2);
        for(Flight f: flight)
        {
            //System.out.println(f);
            new Reservation(p, f);
            if(f.getDestinationAirport().equals(destination))
                return "successful";
        }
        return "failure";
    }

    public static String makeReservation(int iD, String passenger)
    {
        ArrayList<Flight> f = Itinerary.getItinerary(iD);
        if(f == null)
            return "failure";
        for(Flight flight: f)
        {
            new Reservation(passenger, flight);
        }
        return "successful";
    }

    /**
     * deletes a specific reservation
     * @param p the passenger
     * @param origin the origin airport
     * @param destination the destination airport
     */
    static void deleteReservation(String p, Airport origin, Airport destination)
    {
        for(Reservation r: reservation)
        {
            if(r.passenger.equals(p)&&r.flight.getOriginAirport().equals(origin)&&r.flight.getDestinationAirport().equals(destination))
            {
                reservation.remove(r);
                Itinerary.removeFlight(p, origin, destination);
            }
        }
    }

    @Override
    public String toString()
    {
        return passenger+" has a reservation for "+flight;
    }

    public static void main(String[] args) throws IOException, ParseException {
        new Airport();
        new Flight();
        Airport o = Airport.getAirport("IAD");
        Airport d = Airport.getAirport("LAX");
        //System.out.println(o);
        Itinerary.getFlights(o, d, 2);
        Reservation.makeReservation(0,"Bob");
        System.out.println(Itinerary.getItinerary(0));
        System.out.println(Itinerary.getPrice("Bob"));
    }
}
