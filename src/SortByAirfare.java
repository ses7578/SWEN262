import java.util.Comparator;
import java.util.List;

public class SortByAirfare implements SortAlgo{
    @Override
    public void sortOrder(List<Flight> flights){
        flights.sort(Comparator.comparing(Flight::getAirfare));
    }
}
