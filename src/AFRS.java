import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

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
     * @param o the origin airport
     * @param d the destination airport
     * @return a flight connecting the two airports.txt
     */
    private static Flight getFlight(Airport o, Airport d)
    {
        return Flight.getFlight(o, d);
    }

    /**
     *
     * @param o the starting airport
     * @param d the ending airport
     * @return the flights.txt that go between those two
     */
    public static ArrayList<Flight> getAllFlights(Airport o, Airport d)
    {
        return Flight.getAllFlights(o, d);
    }

    /**
     *
     * @param aCode the airport code
     * @return the airport with that code
     */
    private static Airport getAirport(String aCode)
    {
        return Airport.getAirport(aCode);
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
    private static void deleteReservation(String p, Airport o, Airport d)
    {
        Reservation.deleteReservation(p, o, d);
    }

    public static void main(String[] args) throws IOException, ParseException {
        new Airport();
        new Flight();
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a command");
        String input;
        while(!(input = scan.nextLine()).equals("quit"))
        {
            input = input.replace(" ", "");
            String[] key = input.split(",");
            switch(key[0])
            {
                case "airport":
                    if(key.length!=2)
                        System.out.println("There was an error reading your command");
                    else
                        System.out.println(AFRS.getAirport(key[1]));
                    break;
                case "info":
                    Airport o = AFRS.getAirport(key[1]);
                    Airport d = AFRS.getAirport(key[2]);
                    System.out.println(AFRS.getFlight(o,d));
                    break;
                case "reserve":
                    System.out.println("This is where reserved would be");
                case "help":
                    System.out.println("These are the commands you can run: ");
                    System.out.println("airport,airportCode");
                    System.out.println("info,airportCode1,airportCode2");
                    break;
                case "delete":
                    String p = key[1];
                    Airport oA = AFRS.getAirport(key[2]);
                    Airport dA = AFRS.getAirport(key[3]);
                    AFRS.deleteReservation(p, oA, dA);
                default:
                    System.out.println("Unrecognized command");
            }

        }
    }

}
