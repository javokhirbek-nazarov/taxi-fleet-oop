package taxi.fleet.oop.booking;

public class BookingStatistics {

    private long newCount;

    private long takenCount;

    private long completeCount;

    private long totalCount;

    private long availableTaxiCount;

    public long getNewCount() {
        return newCount;
    }

    void setNewCount(long newCount) {
        this.newCount = newCount;
    }

    public long getTakenCount() {
        return takenCount;
    }

    void setTakenCount(long takenCount) {
        this.takenCount = takenCount;
    }

    public long getTotalCount() {
        return totalCount;
    }

    void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getCompleteCount() {
        return completeCount;
    }

    void setCompleteCount(long completeCount) {
        this.completeCount = completeCount;
    }

    public long getAvailableTaxiCount() {
        return availableTaxiCount;
    }

    public void setAvailableTaxiCount(long availableTaxiCount) {
        this.availableTaxiCount = availableTaxiCount;
    }

    @Override
    public String toString() {
        return "Booking Statistics {" +
            "newCount=" + newCount +
            ", takenCount=" + takenCount +
            ", completeCount=" + completeCount +
            ", totalCount=" + totalCount +
            ", availableTaxiCount=" + availableTaxiCount +
            '}';
    }
}
