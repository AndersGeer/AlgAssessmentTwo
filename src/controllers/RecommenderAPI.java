package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import models.Movie;
import models.Rating;
import models.User;
import utils.Serializer;
import utils.AverageRating;

public class RecommenderAPI 
{
	private Serializer serializer;

	private Map<Integer,User> 	userIndex = 	new HashMap<>();
	private Map<Integer,Movie> 	movieIndex = 	new HashMap<>();
	private Map<Integer,Rating> ratingIndex = 	new HashMap<>();

	public RecommenderAPI(Serializer serializer) 
	{
		this.serializer = serializer;
	}

	@SuppressWarnings("unchecked")
	public void load() throws Exception
	{
		serializer.read();
		Rating.ratingNumber = 	(int) 					serializer.pop();
		Movie.movieNumber 	= 	(int) 					serializer.pop();
		User.userNumber 	= 	(int) 					serializer.pop();
		ratingIndex 		= 	(Map<Integer, Rating>) 	serializer.pop();
		movieIndex 			= 	(Map<Integer, Movie>) 	serializer.pop();
		userIndex			=	(Map<Integer, User>) 	serializer.pop();

	}

	public void store() throws Exception
	{
		serializer.push(userIndex);
		serializer.push(movieIndex);
		serializer.push(ratingIndex);
		serializer.push(User.userNumber);
		serializer.push(Movie.movieNumber);
		serializer.push(Rating.ratingNumber);
		serializer.write(); 
	}

	//User methods
	public User addUser(User u)
	{
		return userIndex.put(u.getUserId(), u);
	}
	
	public User addUser(String firstName,String lastName, int age,String gender,String occupation)
	{
		User user = new User(firstName,lastName,age,gender,occupation);
		return addUser(user);
	}
	public User getUser(int id)
	{
		return userIndex.get(id);
	}
	public User removeUser(int id)
	{
		return userIndex.remove(id);
	}

	public void addRating(Rating rating) throws Exception
	{
		int userId = rating.getUserId();
		User user = userIndex.get(userId);
		Movie movie = movieIndex.get(rating.getMovieId());
		if (user == null || movie == null) 
		{
			throw new Exception("Something is wrong! Check User and Movie id's");
		}
		user.addRating(rating);
		ratingIndex.put(rating.getId(), rating);	
	}
	public void addRating(int userId, int movieId, int rating) throws Exception
	{
		User user = userIndex.get(userId);
		Movie movie = movieIndex.get(movieId);		
		if (user == null || movie == null) 
		{
			throw new Exception("Something is wrong! Check User and Movie id's");
		}
		Rating thisRating = new Rating(user,movie,rating);
		user.addRating(thisRating);
		ratingIndex.put(thisRating.getId(), thisRating);
	}
	
	
	public Collection<Rating> getUserRatings(int userId)
	{
		return userIndex.get(userId).getRatings();	
	}

	
	public void addMovie(Movie movie) 
	{
		movieIndex.put(movie.getMovieId(), movie);		
	}
	
	public void addMovie(String title, int year, String url) 
	{
		Movie movie = new Movie(title,year,url);
		movieIndex.put(movie.getMovieId(), movie);
	}

	public Movie getMovie(int id)
	{
		return movieIndex.get(id);
	}
	
	public Movie removeMovie(int id)
	{ 
		//Returns movie removed - or null if no movie with that id
		return movieIndex.remove(id);
	}
	
	public Collection<Movie> getRecommendations(int userId)
	{
		return recommendations();
	}
	
	public Collection<Movie> getTopTen()
	{
		return topTen();
	}
	  
	
	
	private Collection<Movie> topTen() 
	{
		Map<Integer,AverageRating> ratings = new HashMap<>();
		for (Rating rating : ratingIndex.values()) 
		{
			int movieId = rating.getMovieId();
			AverageRating avgRating = ratings.get(movieId);
			if (avgRating == null) 
			{
				avgRating = new AverageRating(movieId);
				ratings.put(rating.getMovieId(), avgRating);
			}
			avgRating.addRating(rating.getRating());
		}
		ArrayList<AverageRating> listOfRatings = new ArrayList<>(ratings.values());
		Collections.sort(listOfRatings);
		Collection<Movie> topTenList = new ArrayList<>();
		
		for (int i = listOfRatings.size()-10; i < listOfRatings.size(); i++) 
		{
			topTenList.add(movieIndex.get(listOfRatings.get(i).getMovieId()));
		}
		return topTenList;
	}

	private Collection<Movie> recommendations() {
		// TODO Auto-generated method stub
		// returns the top ten movies for the specific user
		return null;
	}

	public Collection<User> getUsers() 
	{		
		return userIndex.values();
	}

	public Collection<Movie> getMovies() 
	{		
		return movieIndex.values();
	}

	public Collection<Rating> getRatings() 
	{		
		return ratingIndex.values();
	}
}	


