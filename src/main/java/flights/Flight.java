package flights;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Flight {

    private List<Segment> segments;

    public Flight(List<Segment> segs) {
        this.segments = segs;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    @Override
    public String toString() {
        return "Flight consists " + segments.size() + " segments : " + segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}
