package models;

import static models.Fixtures.users;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MovieTest 
{
	Movie twentyTwelve;

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	@Before
	public void beforeTest() throws Exception
	{
		twentyTwelve = new Movie("2012",2011,"www.2012.org");
	}
	@Test
	public void testCreate()
	{
		assertEquals("2012 (2011)",twentyTwelve.name);
		assertEquals("www.2012.org",twentyTwelve.imdbLink);
	}
	@Test
	public void movieEmptyTitle() throws Exception
	{
		exception.expect(Exception.class);
		new Movie("",2011,"www.2012.org");
	}
	@Test
	public void movieNullTitle() throws Exception
	{
		exception.expect(Exception.class);
		new Movie(null,2011,"www.2012.org");
	}
	@Test
	public void movieYearPreMovies() throws Exception
	{
		exception.expect(Exception.class);
		new Movie("2015",1810,"www.2012.org");
	}
	@Test
	public void movieYear1880() throws Exception
	{
		new Movie("2015",1880,"www.2012.org");
	}
	@Test
	public void movieYearThisYear() throws Exception
	{
		new Movie("2015",Calendar.getInstance().get(Calendar.YEAR),"www.2012.org");
	}
	@Test
	public void movieYearNextYear() throws Exception
	{
		exception.expect(Exception.class);
		new Movie("2015",Calendar.getInstance().get(Calendar.YEAR)+1,"www.2012.org");
	}
	@Test
	public void movieEmptyLink() throws Exception
	{
		exception.expect(Exception.class);
		new Movie("2015",2011,"");
	}
	@Test
	public void movieNullLink() throws Exception
	{
		exception.expect(Exception.class);
		new Movie("2015",2011,null);
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
	public void testEquals() throws Exception
	  {
	    Movie twentyTwelve2 = new Movie("2012",2011,"www.2012.org");
	    Movie bart   = new Movie("Sommeren '92",2015,"www.imdb.com/sommeren92");

	    assertEquals(twentyTwelve, twentyTwelve);
	    assertEquals(twentyTwelve, twentyTwelve2);
	    assertNotEquals(twentyTwelve, bart);
	  } 
}
