package uk.co.sky.parentcontrol;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.co.sky.exception.TechnicalFailureException;
import uk.co.sky.exception.TitleNotFoundException;
import uk.co.sky.movie.MovieService;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * ParentalControlService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 24, 2017</pre>
 */
public class ParentalControlServiceTest {

    @InjectMocks
    private ParentalControlService parentalControlService = new ParentalControlService();

    @Mock
    private MovieService movieService;
    
    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
        
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: isParentalMoviePreference(ParentalControlLevel level, String movieId)
     */
    @Test
    public void testIsParentalMoviePreferenceValid() throws Exception {
        when(movieService.getParentalControlLevel("1")).thenReturn("U");

        boolean canWatchMovie = parentalControlService.isParentalMoviePreference(ParentalControlLevel.PG, "1");

        assertEquals(true, canWatchMovie);
    }

    @Test
    public void testIsParentalMoviePreferenceInValidData() throws Exception {
        when(movieService.getParentalControlLevel(any(String.class))).thenThrow(new TechnicalFailureException());

        try {
            boolean canWatchMovie = parentalControlService.isParentalMoviePreference(ParentalControlLevel.PG, null);
            fail("Test case failed");
        } catch (ParentalControlException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testIsParentalMoviePreferenceMovieNotSuitable() throws Exception {
        when(movieService.getParentalControlLevel("1")).thenReturn("PG");

        boolean canWatchMovie = parentalControlService.isParentalMoviePreference(ParentalControlLevel.U, "1");

        assertEquals(false, canWatchMovie);
    }

    @Test
    public void testIsParentalMoviePreferenceMovieNotSuitable_1() throws Exception {
        when(movieService.getParentalControlLevel("1")).thenReturn("15");

        boolean canWatchMovie = parentalControlService.isParentalMoviePreference(ParentalControlLevel.U, "1");

        assertEquals(false, canWatchMovie);
    }

    @Test
    public void testIsParentalMoviePreferenceInValidMovieId() throws Exception {
        when(movieService.getParentalControlLevel("1000")).thenThrow(new TitleNotFoundException());

        try {
            boolean canWatchMovie = parentalControlService.isParentalMoviePreference(ParentalControlLevel.PG, "1000");
            fail("Test case failed");
        } catch (ParentalControlException e) {
            assertEquals("The movie you requested to watch cannot be found in our database.", e.getMessage());
            assertTrue(true);
        }
    }

    @Test
    public void testIsParentalMoviePreferenceTechnicalFailure() throws Exception {
        when(movieService.getParentalControlLevel("100")).thenThrow(new TechnicalFailureException());

        try {
            boolean canWatchMovie = parentalControlService.isParentalMoviePreference(ParentalControlLevel.PG, "1");
            fail("Test case failed");
        } catch (ParentalControlException e) {
            assertEquals("You cannot watch movie due to Technical difficulties, please try again later.", e.getMessage());
            assertTrue(true);
        }
    }
}
