import filtration.ArrivalAfterDepartureFilter;
import filtration.FilterByDepartureTime;
import filtration.FilterByTransitTime;
import filtration.FlightFilter;
import flights.Flight;
import flights.FlightBuilder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        //Создание списка перелетов
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("Старый список перелетов: " + flights);
        System.out.println("Количество: " + flights.size());

        //Создание экземпляра фильтра и помещение в его конструктор списка
        FlightFilter filter = new FlightFilter(flights);

        //Создание правил фильтрации и цепочки из правил которые позволят добавлять правила динамически
        //Используется шаблон Chain of Responsibility
        FilterByTransitTime filterByTransitTime = new FilterByTransitTime(2);
        FilterByDepartureTime filterByDepartureTime = new FilterByDepartureTime(LocalDateTime.now());
        ArrivalAfterDepartureFilter arrivalAfterDepartureFilter = new ArrivalAfterDepartureFilter();

        //        Установка правил фильтрации в цепочку для демонстрации
//        filterByTransitTime.setNextRule(filterByDepartureTime);
//        filterByDepartureTime.setNextRule(arrivalAfterDepartureFilter);

        //Вызывается метод apllyFilter() в который помещается первый фильтр в цепочке
        List<Flight> filteredList = filter.applyFilters(filterByTransitTime);
        System.out.println("Новый список перелетов: " + filteredList);
        System.out.println("Количество " + filteredList.size());

        List<Flight> filteredList2 = filter.applyFilters(filterByDepartureTime);
        System.out.println("Новый список перелетов: " + filteredList2);
        System.out.println("Количество " + filteredList2.size());

        List<Flight> filteredList3 = filter.applyFilters(arrivalAfterDepartureFilter);
        System.out.println("Новый список перелетов: " + filteredList3);
        System.out.println("Количество " + filteredList3.size());


    }
}
