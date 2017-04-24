package uk.co.sky.parentcontrol;

/**
 * Created by dinesh on 24/04/2017.
 */
public class ParentalControlException extends RuntimeException {
    public ParentalControlException(String message) {
        super(message);
    }

    public ParentalControlException(String message, Throwable e) {
        super(message, e);
    }
}
