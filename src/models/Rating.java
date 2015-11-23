package models;

import java.util.Date;

import controllers.RecommenderAPI;
import utils.ToJsonString;

public class Rating 
{
	public static int ratingNumber = 1;
	int ratingId;
	User user;
	Movie movie;
	int rating;
	private Date timestamp;
	
	
	public Rating(User user, Movie movie, int rating, Date timestamp)
	{
		this.ratingId = ratingNumber++;
		this.user = user;
		this.movie = movie;
		this.rating = rating;
		this.timestamp = timestamp;
	}
	
	public Rating(User user, Movie movie ,int rating)
	{
		this.ratingId = ratingNumber++;
		this.user = user;
		this.movie = movie;
		this.rating = rating;
		this.timestamp = new Date();
	}
	
	


	public int getId() 
	{		
		return ratingId;
	}
	
	public int getUserId()
	{
		return user.getUserId();
	}
	
	public int getRating()
	{
		return rating;
	}
	public int getMovieId()
	{
		return movie.getMovieId();
	}
	
	
	@Override
	public String toString()
	{
		return ("\n\nUserId: " +user.getUserId() + "\nMovieId: " + movie.getMovieId() + "\nRating: " + rating);
		
	}

	//Auto generated
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rating other = (Rating) obj;
		if (movie == null) {
			if (other.movie != null)
				return false;
		} else if (!movie.equals(other.movie))
			return false;
		if (rating != other.rating)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
	
	
	
	
}
