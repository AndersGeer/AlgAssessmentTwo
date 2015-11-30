package models;

import java.util.Calendar;
import java.util.Date;

import utils.ToJsonString;

public class Movie 
{
	public static int movieNumber = 1;
	int movieId;
	public String name;
	private Date releaseDate;
	public String imdbLink;
	
	
	
	
	public Movie(int movieId, String name,int year, String imdbLink) {
		this.movieId = movieId;
		this.name = name+" ("+ year+")";
		this.imdbLink = imdbLink;
		movieNumber++;
	}

	
	public Movie(String name, int year, String imdbLink) throws Exception 
	{
		if (name.isEmpty()) 
		{
			throw new Exception("Movie Title cannot be empty");
		}

		if (year < 1880 || year > Calendar.getInstance().get(Calendar.YEAR)) 
		{
			throw new Exception("Year cannot be before 1880 or after this year");
		}
		if (imdbLink.isEmpty()) 
		{
			throw new Exception("Link cannot be empty");
		}
		this.movieId = ++movieNumber;
		this.name = name+" ("+ year+")";
		this.imdbLink = imdbLink;
	}
	public Movie(int movieId, String title, String url) {
		this.movieId = movieId;
		this.name = title;
		this.imdbLink = url;
		movieNumber++;
	}


	public int getMovieId() 
	{		
		return movieId;
	}
	
	@Override
	public String toString()
	{
		return ("\n\nMovieId: " +movieId + "\nTitle: " + name);
		//return new ToJsonString(getClass(), this).toString();
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (imdbLink == null) {
			if (other.imdbLink != null)
				return false;
		} else if (!imdbLink.equals(other.imdbLink))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		return true;
	}


}
