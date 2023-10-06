package filtration;

import flights.Flight;
import flights.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Фильтр предназначен для фильтрациии перелетов по времени отправления
 */
public class FilterByDepartureTime implements FiltrationRule {
    private LocalDateTime from;
    private LocalDateTime to;

    private FiltrationRule nextRule;

    public void setNextRule(FiltrationRule nextRule) {
        this.nextRule = nextRule;
    }

    /**
     * Конструктор для указания диапазона
     *
     * @param from - нижняя граница
     * @param to   - верхняя граница
     */
    public FilterByDepartureTime(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    /*
     * Конструктор, который принимает только нижнюю границу значений
     */
    public FilterByDepartureTime(LocalDateTime from) {
        this.from = from;
    }


    @Override
    public List<Flight> filter(List<Flight> flights) {

        List<Flight> filteredList = new ArrayList<>();

        if (this.to == null) {
            for (Flight flight : flights) {
                boolean isAppropriate = true;
                LocalDateTime departureTime = flight.getSegments().get(0).getDepartureDateTime();
                if (departureTime.isBefore(this.from)) {
                    isAppropriate = false;
                }

                if (isAppropriate) filteredList.add(flight);


            }
        } else {
            for (Flight flight : flights) {
                boolean isAppropriate = true;
                LocalDateTime departureTime = flight.getSegments().get(0).getDepartureDateTime();
                if (departureTime.isBefore(this.from) || departureTime.isAfter(this.to)) {
                    isAppropriate = false;
                }

                if (isAppropriate) filteredList.add(flight);
            }
        }
        if (this.nextRule != null) {
            return nextRule.filter(filteredList);
        } else {
            return filteredList;
        }

    }
}
