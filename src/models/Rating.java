package models;

import java.util.Date;

import controllers.RecommenderAPI;
import utils.ToJsonString;

public class Rating implements Comparable<Rating>
{
	public static int ratingNumber = 1;
	int ratingId;
	User user;
	Movie movie;
	int rating;
	
	
	
	public Rating(int id, User user, Movie movie ,int rating) 
	{
		this.ratingId = id;
		this.user = user;
		this.movie = movie;
		this.rating = rating;
		ratingNumber++;
	}
	
	public Rating(User user, Movie movie ,int rating) throws Exception
	{
		if (rating < -5 || rating > 5) 
		{
			throw new Exception("Rating has to be between -5 and 5");
		}
		this.ratingId = ratingNumber++;
		this.user = user;
		this.movie = movie;
		this.rating = rating;
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




	@Override
	public int compareTo(Rating that) {
		
		return that.getMovieId() - this.getMovieId();
	}
	
	
	
	
	
	
}
