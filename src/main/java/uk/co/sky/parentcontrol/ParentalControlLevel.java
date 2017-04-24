package uk.co.sky.parentcontrol;

/**
 * Created by dinesh on 24/04/2017.
 */
public enum ParentalControlLevel {
    U("U"),
    PG("PG"),
    UNDER_12("12"),
    UNDER_15("15"),
    UNDER_18("18");

    private String level;

    private ParentalControlLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public static ParentalControlLevel getLevel(String level){
        for (ParentalControlLevel day : ParentalControlLevel.values()) {
            if (day.getLevel().equals(level)) {
                return day;
            }
        }
        return null;
    }
}
