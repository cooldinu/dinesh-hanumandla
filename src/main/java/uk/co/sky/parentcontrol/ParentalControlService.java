package uk.co.sky.parentcontrol;

import uk.co.sky.exception.TechnicalFailureException;
import uk.co.sky.exception.TitleNotFoundException;
import uk.co.sky.movie.Movie;
import uk.co.sky.movie.MovieService;

/**
 * ParentalControlService class provides methods to check whether the movie classification matches parental controls.
 */
public class ParentalControlService {

    /*
     * Reference to the MovieService
     */
    private MovieService movieService;

    /**
     * Method checks whether the movie classification matches parental control classification.
     * @param level
     * @param movieId
     * @return - true if movie matches parental classification else 'false' is returned
     */
    public boolean isParentalMoviePreference(ParentalControlLevel level, String movieId) {
        try {
            Movie movieToWatch = fetchMovieDetails(movieId);
            if (level.ordinal() >= movieToWatch.getClassification().ordinal()) {
                return true;
            }
            return false;
        } catch (TitleNotFoundException e) {
            throw new ParentalControlException("The movie you requested to watch cannot be found in our database.", e);
        } catch (TechnicalFailureException e) {
            throw new ParentalControlException("You cannot watch movie due to Technical difficulties, please try again later.", e);
        }
    }

    /**
     * 
     * @param movieId
     * @return
     * @throws TitleNotFoundException
     * @throws TechnicalFailureException
     */
    private Movie fetchMovieDetails(String movieId) throws TitleNotFoundException, TechnicalFailureException {
        String movieClassification = getMovieService().getParentalControlLevel(movieId);
        if (movieClassification == null || movieClassification.trim().length() == 0) {
            throw new TechnicalFailureException();
        }
        return new Movie("", "", ParentalControlLevel.getLevel(movieClassification));
    }

    public MovieService getMovieService() {
        return movieService;
    }

    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }
}
