package filtration;

import flights.Flight;

import java.util.List;

/**
 * Класс, а точнее его экземпляр, представляет собой фильтр который принимает в качетсве параметра
 * список перелетов и делает его фильтрацию по введенным правилам с помощью метода applyFilters(),
 * который принимает объект класса реализующего интерфейс FiltrationRule
 */
public class FlightFilter {
    private List<Flight> flights;

    public FlightFilter(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> applyFilters(FiltrationRule firstRule) {
        return firstRule.filter(flights);
    }
}
