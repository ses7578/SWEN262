import java.io.IOException;

/**
 * The main server for the class
 * @author: Shannon
 */
public class AFRS
{

    /**
     * gets the itinerary that a specific passenger has
     * @param p passenger name
     * @param o origin airport
     * @param d destination airport
     * @return the Itinerary
     */
    public static String getItinerary(String p, Airport o, Airport d)
    {
        return Itinerary.getItinerary(p);
    }

    /**
     * gets the reservation for a specific passenger on a specific flight
     * @param p passenger
     * @param o origin airport
     * @param d destination airport
     * @return the reservation for that specific person has
     */
    public static Reservation getReservation(String p, Airport o, Airport d)
    {
        return Reservation.getReservation(p, o, d);
    }


    /**
     *
     * @param i itinerary
     * @param p passenger
     */
    public void makeReservation(Itinerary i, String p)
    {
    }

    /**
     * deletes a specific reservation for a passenger
     * @param p passenger
     * @param o origin airport
     * @param d destination airport
     */
    public void deleteReservation(String p, Airport o, Airport d)
    {

    }

    public static void main(String[] args) throws IOException
    {
        new Airport();
        new Flight();
        Reservation r = AFRS.getReservation("Sally", Airport.getAirport("SEA"), Airport.getAirport("BOS"));
        if(r == null)
            System.out.println("There are no reservations");
        else
            System.out.println(r);
    }

}
