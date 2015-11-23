package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class RatingTest 
{
	Movie twentyTwelve = new Movie("2012",2011,"www.2012.org");
	User homer = new User("Homer","Simpson",43,"M","Sofa guy");
	Rating rating1 = new Rating(homer, twentyTwelve, 5);

	
	@Test
	public void testCreate()
	{
		assertEquals (homer,             	rating1.user);
		assertEquals (twentyTwelve,         rating1.movie);
		assertEquals (5,					rating1.getRating());
	}
	
	@Test
	public void testIds()
	{
		Set<Integer> ids = new HashSet<>();
		for (Rating rating : models.Fixtures.ratings)
		{
			ids.add(rating.ratingId);
		}
		assertEquals (models.Fixtures.ratings.length, ids.size());
	}
	
	@Test
	public void testToString()
	{
		assertEquals ("\n\nUserId: " +rating1.getUserId() + "\nMovieId: " + rating1.getMovieId() + "\nRating: " + rating1.rating, rating1.toString());
	}
	
	@Test
	public void testEquals()
	  {
		
		Rating rating2 = new Rating(homer, twentyTwelve, 5);
		Rating ratingNew = new Rating(models.Fixtures.users[1], models.Fixtures.movies[3], 1);

	    assertEquals(rating1, rating1);
	    System.out.println(rating1);
	    System.out.println(rating2);
	    assertEquals(rating1, rating2);
	    assertNotEquals(rating1, ratingNew);
	  } 
}