import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Shannon Sloan
 */
public class Weather {
    String airportCode;
    private HashMap<String, Integer> airportWeather = new HashMap<>();
    static ArrayList<Weather> weather = new ArrayList<>();

    /**
     * takes the weather and puts it in a list for all of the airports
     * @throws IOException if the URL is not found
     */
    public Weather() throws IOException
    {
        createWeather();
    }

    /**
     * creates weather for a specific airport
     * @param aCode airport code
     * @param w the list of weathers
     */
    public Weather(String aCode, HashMap<String, Integer> w)
    {
        airportCode = aCode;
        airportWeather = w;
    }

    /**
     * Creates the weather
     * @throws IOException if the URL is not found
     */
    public void createWeather() throws IOException {
        URL url = new URL("http://www.se.rit.edu/~swen-262/projects/design_project/ProjectDescription/weather.txt");
        Scanner scanner = new Scanner(url.openStream());
        while(scanner.hasNextLine())
        {
            String s = scanner.nextLine();
            String[] split = s.split(",");
            HashMap<String, Integer> w = new HashMap<>();
            for(int i = 1; i<split.length; i+=2)
            {
                w.put(split[i], Integer.valueOf(split[i+1]));
            }
            weather.add(new Weather(split[0],w));
        }
    }

    /**
     * gets the weather for a specific airport
     * @param a the airport
     * @return the weather
     */
    public static HashMap<String, Integer> getWeather(Airport a)
    {
        for(Weather w : weather)
        {
            if(w.airportCode.equals(a.airportCode))
                return w.airportWeather;
        }
        return null;
    }

    @Override
    public String toString(){
        String s = "The weather for "+airportCode+" is ";
        for(Map.Entry mapElement : airportWeather.entrySet())
        {
            s = s+mapElement.getKey()+" "+mapElement.getValue();
        }
        return s;
    }

    public static void main(String[] args)
    {
        try {
            new Weather();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Airport a = Airport.getAirport("ATL");
        System.out.println(Weather.getWeather(a));
    }
}
