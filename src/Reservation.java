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
    public Itinerary itinerary;

    /**
     * Creates a reservation
     * @param p passenger
     */
    public Reservation(String p, Itinerary i)
    {
        passenger = p;
        itinerary = i;
        reservation.add(this);
    }

    /**
     * Gets the reservation
     * @param p passenger
     * @return the reservation
     */
    static String getReservation(String p)
    {
        ArrayList<Reservation> passengerReservations = new ArrayList<>();
        for(Reservation r: reservation)
        {
            if(r.passenger.equals(p))
                passengerReservations.add(r);
        }
        StringBuilder s = new StringBuilder("retrieve," + passengerReservations.size());
        for(Reservation r: passengerReservations){
            s.append(r.toString());
        }
        return s.toString();

    }

    static String getReservation(String p, String origin)
    {
        ArrayList<Reservation> passengerReservations = new ArrayList<>();
        for(Reservation r: reservation)
        {
            if(r.passenger.equals(p)&&r.itinerary.origin.equals(origin))
                passengerReservations.add(r);
        }
        StringBuilder s = new StringBuilder("retrieve," + passengerReservations.size());

        for(Reservation r: passengerReservations){
            s.append(r.toString());
        }
        return s.toString();
    }

    static String getReservation(String p, String origin, String destination)
    {
        ArrayList<Reservation> passengerReservations = new ArrayList<>();
        for(Reservation r: reservation)
        {
            if(origin.equals("")){
                if (r.passenger.equals(p) && (r.itinerary.dest.equals(destination)))
                    passengerReservations.add(r);
            }
            else {
                if (r.passenger.equals(p) && r.itinerary.origin.equals(origin) && (r.itinerary.dest.equals(destination)))
                    passengerReservations.add(r);
            }
        }
        StringBuilder s = new StringBuilder("retrieve," + passengerReservations.size());
        for(Reservation r: passengerReservations){
            s.append(r.toString());
        }
        return s.toString();
    }

    public static String makeReservation(int iD, String passenger)
    {
        Itinerary i = Itinerary.getItinerary(iD);
        if(i == null)
            return "reserve,failure";
        new Reservation(passenger, i);
        return "reserve,successful";
    }

    /**
     * deletes a specific reservation
     * @param p the passenger
     * @param origin the origin airport
     * @param destination the destination airport
     */
    static void deleteReservation(String p, Airport origin, Airport destination)
    {
        Reservation toRemove = null;
        for(Reservation r: reservation)
        {
            if(r.passenger.equals(p)&&r.itinerary.origin.equals(origin.getAirportCode())&&r.itinerary.dest.equals(destination.getAirportCode()))
            {
                toRemove = r;
            }
        }

        if(toRemove == null){
            System.out.println("error,reservation not found");
        }
        else{
            reservation.remove(toRemove);
            System.out.println("delete,successful");
        }
    }

    @Override
    public String toString()
    {
        return itinerary.toString();
    }

    public static void main(String[] args) throws IOException, ParseException {
        new Airport();
        new Flight();
        Airport o = Airport.getAirport("IAD");
        Airport d = Airport.getAirport("LAX");
        Itinerary.getFlights(o, d, 2);
        Reservation.makeReservation(0,"Bob");
        System.out.println(Itinerary.getItinerary(0));
    }
}
