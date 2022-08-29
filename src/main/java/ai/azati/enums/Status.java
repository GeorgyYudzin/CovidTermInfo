package ai.azati.enums;

/**
 * This enum represents different statuses used in /history endpoint
 */
public enum Status {
    CONFIRMED,
    DEATHS,
    RECOVERED;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
