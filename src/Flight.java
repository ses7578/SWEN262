import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @author: Shannon
 */
public class Flight
{
    private int flightID;
    private Airport originAirport;
    private Airport destinationAirport;
    private String departureTime;
    private String arrivalTime;
    private Date dTime;
    private Date aTime;
    private int airfare;
    private static ArrayList<Flight> flights = new ArrayList<>();

    /**
     * Creates the list of flights.txt
     * @throws IOException if the URL is not found
     */
    Flight() throws IOException {
        createFlight();
    }

    /**
     * Creates an individual flight
     * @param o origin airport
     * @param d destination airport
     * @param dT departure time
     * @param aT arrival time
     * @param flightID the flight ID
     * @param airfare the cost of the flight
     */
    private Flight(Airport o, Airport d, String dT, String aT, int flightID, int airfare) throws ParseException {
        originAirport = o;
        destinationAirport = d;
        departureTime = dT;
        arrivalTime = aT;
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        dTime = dateFormat.parse(dT.substring(0, dT.length()-2));
        aTime = dateFormat.parse(aT.substring(0, aT.length()-2));
        if(departureTime.charAt(departureTime.length()-1)=='p')
            dTime.setTime(dTime.getTime()+(12*60*60*1000));
        if(arrivalTime.charAt(arrivalTime.length()-1)=='p')
            aTime.setTime(aTime.getTime()+(12*60*60*1000));
        this.flightID = flightID;
        this.airfare = airfare;
    }

    /**
     * Helper to take the file and create a list of flights.txt
     * @throws IOException if the URL is not accessible
     */
    private void createFlight() throws IOException
    {

        URL url = new URL("http://www.se.rit.edu/~swen-262/projects/design_project/ProjectDescription/flights.txt");

        Scanner scan = null;

        // Tries to open URL
        try {
            scan = new Scanner(url.openStream());
        }
        catch (FileNotFoundException e){

            // Prints error if the file can not be opened
            System.err.println("Unable to open flights file");
            System.exit(1);
        }

        while(scan.hasNextLine())
        {
            String s = scan.nextLine();
            String[] split = s.split(",");
            Airport o = Airport.getAirport(split[0]);
            Airport d = Airport.getAirport(split[1]);
            try {
                flights.add(new Flight(o, d, split[2], split[3],Integer.valueOf(split[4]),
                        Integer.valueOf(split[5])));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets flights from a specific airport
     * @param origin the origin airport
     * @return all flights from that airprot
     */
    static ArrayList<Flight> getFlight(Airport origin)
    {
        ArrayList<Flight> flight = new ArrayList<>();
        for(Flight f: flights)
        {
            if(f.getOriginAirport().equals(origin))
            {
                flight.add(f);
            }
        }
        return flight;
    }

    /**
     * @return the origin airport
     */
    Airport getOriginAirport()
    {
        return originAirport;
    }

    /**
     * @return the destination airport
     */
    Airport getDestinationAirport()
    {
        return destinationAirport;
    }

    /**
     * @return the airfare
     */
    int getAirfare(){
        return airfare;
    }

    /**
     * @return the departure time
     */
    long getdTime()
    {
        return dTime.getTime();
    }

    /**
     * @return the arrival time
     */
    long getaTime()
    {
        return aTime.getTime();
    }

    /**
     * @return if flight arrives in a/p
     */
    char getFlightArrivalHour()
    {
        return arrivalTime.charAt(arrivalTime.length()-1);
    }

    /**
     * @return if flight departs in a/p
     */
    char getFlightDepartureHour()
    {
        return departureTime.charAt(departureTime.length()-1);
    }

    /**
     * @param f the other flight
     * @return if the flight is allowed based on the arrivals, departures, and minimum connections
     */
    boolean checkIfValid(Flight f)
    {
        boolean valid = false;
        Airport a = f.getDestinationAirport();
        if(getdTime() >= f.getaTime() + a.getConnectionsMin())
            valid = true;
        if(getFlightDepartureHour() == 'a' && f.getFlightArrivalHour() == 'p')
            valid = true;
        return valid;
    }

    @Override
    public String toString()
    {
        return flightID + "," + originAirport.getAirportCode() + "," + departureTime
                + ","+ destinationAirport.getAirportCode() + "," + arrivalTime;
    }

}
