package filtration;

import flights.Flight;
import flights.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс предназначен для фильтрации списка перелетов и используется для
 * исключения из списка перелетов с некорректными данными, такими как:
 * дата прилета у сегмента стоит раньше даты вылета
 */
public class ArrivalAfterDepartureFilter implements FiltrationRule {

    private FiltrationRule nextRule;

    public void setNextRule(FiltrationRule nextRule) {
        this.nextRule = nextRule;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {

        List<Flight> filteredFlights = new ArrayList<>();

        for (Flight flight : flights) {
            boolean isAppropriate = true;

            for (Segment seg : flight.getSegments()) {
                LocalDateTime departure = seg.getDepartureDateTime();
                LocalDateTime arrival = seg.getArrivalDateTime();
                if (departure.isAfter(arrival)) {
                    isAppropriate = false;
                    break;
                }
            }

            if (isAppropriate) filteredFlights.add(flight);
        }

        if (this.nextRule != null) {
            return nextRule.filter(filteredFlights);
        } else {
            return filteredFlights;
        }
    }
}
