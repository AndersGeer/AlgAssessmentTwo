package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

	int formerMaxSimilarity = Integer.MAX_VALUE;

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
	public void addUser(User u)
	{
		userIndex.put(u.getUserId(), u);
	}

	public void addUser(String firstName,String lastName, int age,String gender,String occupation) throws Exception
	{
		User user = new User(firstName,lastName,age,gender,occupation);
		addUser(user);
	}
	public User getUser(int id)
	{
		return userIndex.get(id);
	}
	public void removeUser(int id)
	{
		userIndex.remove(id);
	}

	public void addRating(Rating rating) throws Exception
	{
		//Get user and movie to check if they actually exist before adding the rating
		int userId = rating.getUserId();
		User user = userIndex.get(userId);
		Movie movie = movieIndex.get(rating.getMovieId());
		//Checking for null as the get methods return null if it does not exist
		if (user == null || movie == null) 
		{
			throw new Exception("Something is wrong! Check User and Movie id's");
		}
		user.addRating(rating);
		ratingIndex.put(rating.getId(), rating);	
	}
	public void addRating(int userId, int movieId, int rating) throws Exception
	{
		//Get user and movie to check if they actually exist before adding the rating
		User user = userIndex.get(userId);
		Movie movie = movieIndex.get(movieId);
		//Checking for null as the get methods return null if it does not exist
		if (user == null || movie == null) 
		{
			throw new Exception("Something is wrong! Check User and Movie id's");
		}
		if (rating > 5 || rating < -5) 
		{
			throw new Exception("Rating must be between 0 and 5");
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

	public void addMovie(String title, int year, String url) throws Exception 
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
		//Returns movie removed - or null if no movie with that id - used for testing purposes
		return movieIndex.remove(id);
	}

	public Collection<Movie> getRecommendations(int userId)
	{
		return recommendations(userId);
	}

	public Collection<Movie> getTopTen(int i)
	{
		return topTen(i);
	}



	private Collection<Movie> topTen(int i) 
	{
		//Returns the i highest average rated movies
		Map<Integer,AverageRating> ratings = new HashMap<>();
		for (Rating rating : ratingIndex.values()) 
		{
			//Adds each movie to a collection as they are encountered, and then adds the rating to each of them.
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

		for (int j = listOfRatings.size()-i; j < listOfRatings.size(); j++) 
		{
			topTenList.add(movieIndex.get(listOfRatings.get(j).getMovieId()));
		}
		return topTenList;
	}

	private Collection<Movie> recommendations(int userId) 
	{
		//Returns a list of movies recommended for a user. - If user has not rated anything, the ten most popular movies are recommended
		if (userIndex.get(userId).getRatings().size() == 0) 
		{
			return topTen(10);
		}
		
		User[] users = new User[userIndex.values().size()];
		
		//A list of similarity for ALL the users
		int[] similarityIndex = new int[userIndex.values().size()];
		ArrayList<User> userList = new ArrayList<>(userIndex.values());
		userList.toArray(users);
		for (int i = 0; i < users.length; i++) 
		{
			if (users[i].getUserId() != userId) 
			{
				similarityIndex[i] = compareRatings(userId,users[i].getUserId());
			}


		}
		Collection<Movie> reccomendationsForUser = new ArrayList<>();


		//Get the movies recommended for the highest similarity user and adds all the movies to the list.
		Collection<Movie> listOfOneUser = getSimilarMovies(userId, users, similarityIndex);
		reccomendationsForUser.addAll(listOfOneUser);

		int loops = 0;
		//Adds more movies if there are few movies - loop not infinite, in case of a very unlucky rating.
		while (reccomendationsForUser.size() < 5 && loops < 50) 
		{
			//Gets list from user a little less similar compared to last user compared to.
			listOfOneUser = getSimilarMovies(userId, users, similarityIndex);
			for (Movie movie : listOfOneUser) 
			{
				if (!reccomendationsForUser.contains(movie)) 
				{
					reccomendationsForUser.add(movie);
				}
			}
			loops++;
		}

		return reccomendationsForUser;
	}

	private Collection<Movie> getSimilarMovies(int userId, User[] users, int[] similarityIndex) 
	{
		//Gets movies from similar users ratings.
		int highestSimilarity = 0;
		int highestSimilarityIndex = -1;

		for (int i = 0; i < similarityIndex.length; i++) 
		{
			if (similarityIndex[i] > highestSimilarity && similarityIndex[i] < formerMaxSimilarity) 
			{
				highestSimilarity = similarityIndex[i];
				highestSimilarityIndex = i;
			}
		}
		
		Collection<Rating> similarUserRatings = users[highestSimilarityIndex].getRatings();

		Collection<Movie> reccomendationsForUser = new ArrayList<>();
		User user = userIndex.get(userId);
		for (Rating rating : similarUserRatings) 
		{
			if (!user.hasUserSeenMovie(rating.getMovieId()) && rating.getRating() >= 4) 
			{
				reccomendationsForUser.add(movieIndex.get(rating.getMovieId()));
			}
		}
		formerMaxSimilarity = highestSimilarity;
		return reccomendationsForUser;
	}

	private int compareRatings(int userId, int userId2) 
	{
		//Compares ratings to create a similarity index.
		int similarity = 0;

		ArrayList<Rating> userRatings = new ArrayList<Rating>(getUserRatings(userId));
		ArrayList<Rating> compareUserRatings = new ArrayList<Rating> (getUserRatings(userId2));

		Collections.sort(userRatings);
		Collections.sort(compareUserRatings);

		Rating[] compareRatingsArray = new Rating[compareUserRatings.size()];
		compareUserRatings.toArray(compareRatingsArray);
		for (Rating rating : userRatings) 
		{

			int movieId = rating.getMovieId();

			for (int i = 0; i < compareRatingsArray.length; i++) 
			{
				if (compareRatingsArray[i].getMovieId() == movieId) 
				{	
					int similarRating = Math.abs(compareRatingsArray[i].getRating()-rating.getRating());
					switch (similarRating) 
					{
					case 0:
						similarity += 20;
						break;
					case 1:
						similarity += 16;
						break;
					case 2:
						similarity += 12;
						break;
					case 3:
						similarity += 8;
						break;
					case 4:
						similarity += 4;

					default:
						break;


					}
				}
			}
		}
		return similarity;
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


