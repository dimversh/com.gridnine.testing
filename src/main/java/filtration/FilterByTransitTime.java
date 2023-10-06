package filtration;

import flights.Flight;
import flights.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс предназначен для фильтрации списка перелетов по времени пересадки или
 * нахождения на земле. Класс имеет поле maxTransitTime, которое задается через конструктор
 * и служит критерием фльтрации, перелеты, в которых время пересадки превышает заданное число
 * будут исключены из списка
 */
public class FilterByTransitTime implements FiltrationRule{

    private int maxTransitTime;

    private FiltrationRule nextRule;

    public FilterByTransitTime(int maxTransitTime) {
        this.maxTransitTime = maxTransitTime;
    }

    public void setNextRule(FiltrationRule nextRule) {
        this.nextRule = nextRule;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {

        List<Flight> filteredFlights = new ArrayList<>();

        for (Flight flight: flights) {
            boolean isAppropriate = true;
            for (int i = 0; i < flight.getSegments().size()-1; i++) {
                //Прибытие текущего сегмента
                LocalDateTime arrivalCurrent = flight.getSegments().get(i).getArrivalDateTime();
                //Отправление следующего
                LocalDateTime departureNext = flight.getSegments().get(i+1).getDepartureDateTime();
                //Разница во времени
                int duration = (int) Duration.between(arrivalCurrent, departureNext).toHours();

                if (duration > this.maxTransitTime) {
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
