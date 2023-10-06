package filtration;

import flights.Flight;

import java.util.List;

/**
 * Интерфейс предназначен для создания различных типов правил фильтрации и
 * содержит только один метод filter(), который принимает список перелетов
 * и возвращает отфильтрованный список
 */
public interface FiltrationRule {
    List<Flight> filter(List<Flight> flights);
}
