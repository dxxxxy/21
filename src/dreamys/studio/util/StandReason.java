package dreamys.studio.util;

public enum StandReason {
    STAND("Standing :|"),
    BLACKJACK("Blackjack! :)"),
    BUST("Bust! :(");

    private String reason;

    StandReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
