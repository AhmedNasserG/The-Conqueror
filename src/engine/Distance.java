package engine;

public class Distance {

    private String from, to;
    private int distance;

    public Distance(String from, String to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    // Getters
    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getDistance() {
        return distance;
    }
}
