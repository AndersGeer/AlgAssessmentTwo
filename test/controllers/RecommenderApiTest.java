package controllers;

import static models.Fixtures.*;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.Movie;
import models.Rating;
import models.User;
import utils.JSONSerializer;

public class RecommenderApiTest 
{

	private RecommenderAPI recc;

	@Before
	public void setup() throws Exception
	{
		
		recc = new RecommenderAPI(new JSONSerializer(new File ("testdatastore.xml")));
		for (User user : users)
		{
			recc.addUser(user);
		}
		for (Movie movie : movies)
		{
			recc.addMovie(movie);
		}
		for (Rating rating : ratings)
		{		
			recc.addRating(rating);
		}
	}
	
	@Test
	public void testAddUser() throws Exception
	{
		
		User u = new User("Anders", "Jakobsen", 23, "M", "Student");
		recc.addUser(u);
		
		assertTrue(recc.getUsers().contains(u));	
	}
	
	@Test
	public void testAddUser2() throws Exception
	{
		int users = recc.getUsers().size();
		
		recc.addUser("Anders", "Jakobsen",23,"M","Student");
		
		assertEquals(users+1,recc.getUsers().size());
	}
	
	@Test
	public void testGetUser()
	{
		User us = new User("Anders", "Jakobsen", 23, "M", "Student", 12345);
		recc.addUser(us);
		
		User user2 = recc.getUser(us.getUserId());
		
		assertEquals(us,user2);		
	}
	
	
	@Test
	public void testRemoveUser()
	{
		User us = new User(18, "Anders", "Jakobsen", 23, "M", "Student", 12345);
		recc.addUser(us);
		
		Collection<User> users = recc.getUsers();
		assertTrue(users.contains(us));
		
		recc.removeUser(18);
		
		assertFalse(users.contains(us));
	}
	
	@Test
	public void testAddRating() throws Exception
	{
		int begginingCount = recc.getRatings().size();
		User user = recc.getUser(1);
		int userRatings = user.getRatings().size();
		recc.addRating(1,3,5);
		
		assertEquals(begginingCount+1,recc.getRatings().size());
		assertEquals(userRatings+1,user.getRatings().size());
	}
	
	@Test
	public void testAddRating2() throws Exception
	{
		int begginingCount = recc.getRatings().size();
		User user = recc.getUser(1);
		int userRatings = user.getRatings().size();
		Movie movie = recc.getMovie(3);
		Rating rating = new Rating(user,movie,5);
		
		recc.addRating(rating);
		
		assertEquals(begginingCount+1,recc.getRatings().size());
		assertEquals(userRatings+1,user.getRatings().size());
	}
	
	@Test
	public void testAddMovie()
	{
		//Problem with test
		int movies = recc.getMovies().size();
		Movie mov = new Movie("2012",2011,"www.2012.org");
		recc.addMovie(mov);
		assertEquals(movies+1, recc.getMovies().size());
		assertTrue(recc.getMovies().contains(mov));
	}
	
	@Test
	public void testAddMovie2()
	{
		int movies = recc.getMovies().size();
		recc.addMovie("2015", 2011, "www.2012.org");
		assertEquals(movies+1, recc.getMovies().size());
	}
	
	@Test
	public void testGetMovie()
	{
		Movie mov = new Movie("2012",2011,"www.2012.org");
		recc.addMovie(mov);
		
		assertTrue(recc.getMovies().contains(mov));
		
		Movie mov2 = recc.getMovie(mov.getMovieId());
		
		assertEquals(mov,mov2);
	}
	
	@Test
	public void testRemoveMovie()
	{
		Movie mov = recc.getMovie(1);
		Movie mov2 = recc.removeMovie(1);
		
		assertEquals(mov,mov2);
		
		assertFalse(recc.getMovies().contains(mov));
		
	}
	@Test
	public void testGetUserRatings()
	{
		HashSet<Rating> userList = (HashSet<Rating>) recc.getUserRatings(3);
		for (Rating rating : ratingsByUserThree) 
		{
			assertTrue(userList.contains(rating));
		}
		assertEquals(ratingsByUserThree.length,userList.size());
	}
	@After
	public void teardown()
	{
		recc = null;
/*		User.userNumber = 1;
		Movie.movieNumber = 1;
		Rating.ratingNumber = 1;*/
	}
	
	


}
