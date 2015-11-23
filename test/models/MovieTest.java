package models;

import static models.Fixtures.users;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class MovieTest 
{
	Movie twentyTwelve = new Movie("2012",2011,"www.2012.org");


	@Test
	public void testCreate()
	{
		assertEquals("2012",twentyTwelve.name);
		assertEquals("www.2012.org",twentyTwelve.imdbLink);
	}

	@Test
	public void testIds()
	{
		Set<Integer> ids = new HashSet<>();
		for (Movie movie : models.Fixtures.movies)
		{
			ids.add(movie.movieId);
		}
		assertEquals (models.Fixtures.movies.length, ids.size());
	}

	@Test
	public void testToString()
	{
		assertEquals ("\n\nMovieId: " +twentyTwelve.movieId + "\nTitle: " + twentyTwelve.name, twentyTwelve.toString());
	}

	@Test
	public void testEquals()
	  {
	    Movie twentyTwelve2 = new Movie("2012",2011,"www.2012.org");
	    Movie bart   = new Movie("Sommeren '92",2015,"www.imdb.com/sommeren92");

	    assertEquals(twentyTwelve, twentyTwelve);
	    assertEquals(twentyTwelve, twentyTwelve2);
	    assertNotEquals(twentyTwelve, bart);
	  } 
}
