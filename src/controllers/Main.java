package controllers;

import java.io.File;
import java.util.Collection;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import edu.princeton.cs.introcs.In;
import models.Movie;
import models.Rating;
import models.User;
import utils.JSONSerializer;
import utils.Serializer;

public class Main 
{
	private RecommenderAPI reccApi;
	
	public Main() throws Exception
	{
		File datastore = new File("datastore.json");
		Serializer serializer = new JSONSerializer(datastore);

		reccApi = new RecommenderAPI(serializer);
		if (datastore.isFile())
		{
			reccApi.load();
		}
		else 
		{
			//Primary loads
			usersRead();
			moviesRead();
			ratingsRead();
			reccApi.store();
		}
	}


	public static void main(String[] args) throws Exception 
	{
		Main main = new Main();

		Shell shell = ShellFactory.createConsoleShell("reccApi", "Welcome to recommender-console - ?help for instructions", main);
		shell.commandLoop();

		main.reccApi.store();
	}

	
	//Commands
	@Command(description="Get all users details")
	public void getUsers ()
	{
		Collection<User> users = reccApi.getUsers();
		System.out.println(users);
	}

	@Command(description="Create a new User")
	public void createUser (@Param(name="first name") String firstName, 	@Param(name="last name") String lastName, 
			@Param(name="age")      	int age,     		@Param(name="gender")  String gender,
			@Param(name="Occupation") String occupation) throws Exception
	{
		reccApi.addUser(firstName, lastName, age, gender,occupation);
	}

	@Command(description="Get a user")
	public void getUser(@Param(name="User Id") int id) 
	{
		User user = reccApi.getUser(id);
		System.out.println(user);
	}
	
	@Command(description="Delete a user")
	public void DeleteUser(@Param(name="User Id") int id) 
	{
		reccApi.removeUser(id);
	}

	@Command(description="Get all movie details")
	public void getMovies ()
	{
		Collection<Movie> movies = reccApi.getMovies();
		System.out.println(movies);
	}
	
	@Command(description="Create a new Movie")
	public void createMovie (@Param(name="Title") String title, 	@Param(name="Year") int year, 
			@Param(name="Imdb Link")      	String imdbLink) throws Exception
	{
		reccApi.addMovie(title, year, imdbLink);
	}

	@Command(description="Get a Movie")
	public void getMovie(@Param(name="Movie Id") int id) 
	{
		Movie movie = reccApi.getMovie(id);
		System.out.println(movie);
	}
	
	@Command(description="Delete a Movie")
	public void DeleteMovie(@Param(name="User Id") int id) 
	{
		reccApi.removeMovie(id);
	}
	
	@Command(description="Get all rating details")
	public void getRatings ()
	{
		Collection<Rating> ratings = reccApi.getRatings();
		System.out.println(ratings);
	}
	
	@Command(description="Create a new Rating")
	public void createRating (@Param(name="User Id") int userId, 	@Param(name="Movie Id") int movieId, 
			@Param(name="Rating")      	int rating) throws Exception
	{
		reccApi.addRating(userId, movieId, rating);
	}

	@Command(description="Get top ten movies")
	public void getTopTen ()
	{
		Collection<Movie> topTen = reccApi.getTopTen(10);
		System.out.println(topTen);
	}
	@Command(description="Get recommendations for user")
	public void getRecommendationsForUser(@Param(name="User Id") int userId)
	{
		Collection<Movie> recommendedMovies = reccApi.getRecommendations(userId);
		System.out.println(recommendedMovies);
	}
	
	@Command(description="Get A users Ratings")
	public void getUserRatings(@Param(name="User Id") int userId)
	{
		Collection<Rating> userRatings = reccApi.getUserRatings(userId);
		System.out.println(userRatings);
	}
	

	public void usersRead() throws Exception
	{
		File usersFile = new File("DataBig/users.dat");
		In inUsers = new In(usersFile);
		//each field is separated(delimited) by a '|'
		String delims = "[|]";
		while (!inUsers.isEmpty()) {
			// get user and rating from data source
			String userDetails = inUsers.readLine();

			// parse user details string
			String[] userTokens = userDetails.split(delims);

			// output user data to console.
			if (userTokens.length == 7) 
			{
				String firstName = userTokens[1];
				String lastName = userTokens[2];
				int age = Integer.parseInt(userTokens[3]);
				String gender = userTokens[4];
				String occupation = userTokens[5];
				//int zipCode = Integer.parseInt(userTokens[6]);
				//				if (gender != "M" || gender != "F") 
				//				{
				//					throw new Exception("Invalid member gender: "+gender);
				


				User user = new User(firstName, lastName, age, gender, occupation);
				reccApi.addUser(user);


			}else
			{
				throw new Exception("Invalid member length: "+userTokens.length);
			}
		}
	}

	public void ratingsRead() throws Exception
	{
		File ratingFile = new File("DataBig/ratings.dat");
		In inRatings = new In(ratingFile);
		//each field is separated(delimited) by a '|'
		String delims = "[|]";
		while (!inRatings.isEmpty()) {
			// get user and rating from data source
			String ratingDetails = inRatings.readLine();

			// parse user details string
			String[] ratingTokens = ratingDetails.split(delims);

			// output user data to console.
			if (ratingTokens.length == 4) 
			{

				int userId = Integer.parseInt(ratingTokens[0]);
				int movieId = Integer.parseInt(ratingTokens[1]);
				int rating = Integer.parseInt(ratingTokens[2]);
				//Date timestamp = Date(ratingTokens[3]);
				if (rating < -5 || rating > 5) 
				{
					throw new Exception("Invalid rating: " + rating);
				}
				User user = reccApi.getUser(userId);
				if (user == null) 
				{
					throw new Exception("User does not exist");
				}
				Movie movie = reccApi.getMovie(movieId);
				if (movie == null)
				{
					throw new Exception("Movie does not exist");	
				}

				reccApi.addRating(userId,movieId,rating);


			}else
			{
				throw new Exception("Invalid rating length: "+ratingTokens.length);
			}
		}
	}

	public void moviesRead() throws Exception
	{
		File moviesFile = new File("DataBig/items.dat");
		In inMovies = new In(moviesFile);
		//each field is separated(delimited) by a '|'
		String delims = "[|]";
		while (!inMovies.isEmpty()) {
			// get user and rating from data source
			String movieDetails = inMovies.readLine();

			// parse user details string
			String[] movieTokens = movieDetails.split(delims);

			// output user data to console.
			if (movieTokens.length == 23) 
			{
				int movieId = Integer.parseInt(movieTokens[0]);
				String title = movieTokens[1];
				//Date releaseDate = movieTokens[2];
				String url = movieTokens[3];
				
				if (title.isEmpty() ) 
				{
					throw new Exception("Invalid title: " + title);
				}


				Movie movie = new Movie(movieId,title,url);
				reccApi.addMovie(movie);


			}else
			{
				throw new Exception("Invalid member length: "+movieTokens.length);
			}
		}
	}


}
