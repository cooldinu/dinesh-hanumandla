package uk.co.sky.movie;

import uk.co.sky.exception.TechnicalFailureException;
import uk.co.sky.exception.TitleNotFoundException;

/**
 * Created by dinesh on 24/04/2017.
 */
public interface MovieService {
    String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException;
}
