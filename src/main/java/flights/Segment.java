package flights;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Segment {
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;


    public Segment(LocalDateTime dep, LocalDateTime arrival) {
        this.departureDateTime = dep;
        this.arrivalDateTime = arrival;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

//    public int getHours() {
//        return arrivalDateTime.minusHours(departureDateTime.getHour()).getHour();
//    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return "[departure: " + departureDateTime.format(fmt) +
                '|' +
                "arrival: "+ arrivalDateTime.format(fmt) + ']';
    }
}
