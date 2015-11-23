package models;

import java.util.Date;

import utils.ToJsonString;

public class Movie 
{
	public static int movieNumber = 1;
	int movieId;
	public String name;
	private Date releaseDate;
	public String imdbLink;
	
	private boolean unknownGenre;
	private boolean action;
	private boolean adventure;
	private boolean animation;
	private boolean children;
	private boolean comedy;
	private boolean crime;
	private boolean documentary;
	private boolean drama;
	private boolean fantasy;
	private boolean filmNoir;
	private boolean horror;
	private boolean musical;
	private boolean mystery;
	private boolean romance;
	private boolean sciFi;
	private boolean thriller;
	private boolean western;
	
	
	
	
	public Movie(int movieId, String name, String imdbLink, boolean[] genres) {
		this.movieId = movieId;
		this.name = name;
		//this.releaseDate = releaseDate;
		this.imdbLink = imdbLink;
		this.unknownGenre = genres[0];
		this.action = 		genres[1];
		this.adventure = 	genres[2];
		this.animation = 	genres[3];
		this.children = 	genres[4];
		this.comedy = 		genres[5];
		this.crime = 		genres[6];
		this.documentary = 	genres[7];
		this.drama = 		genres[8];
		this.fantasy = 		genres[9];
		this.filmNoir = 	genres[10];
		this.horror = 		genres[11];
		this.musical = 		genres[12];
		this.mystery = 		genres[13];
		this.romance = 		genres[14];
		this.sciFi = 		genres[15];
		this.thriller = 	genres[16];
		this.western = 		genres[17];
		movieNumber++;
	}

	
	public Movie(String name, int year, String imdbLink) {
		this.movieId = ++movieNumber;
		this.name = name;
		this.imdbLink = imdbLink;
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
		if (action != other.action)
			return false;
		if (adventure != other.adventure)
			return false;
		if (animation != other.animation)
			return false;
		if (children != other.children)
			return false;
		if (comedy != other.comedy)
			return false;
		if (crime != other.crime)
			return false;
		if (documentary != other.documentary)
			return false;
		if (drama != other.drama)
			return false;
		if (fantasy != other.fantasy)
			return false;
		if (filmNoir != other.filmNoir)
			return false;
		if (horror != other.horror)
			return false;
		if (imdbLink == null) {
			if (other.imdbLink != null)
				return false;
		} else if (!imdbLink.equals(other.imdbLink))
			return false;
		if (musical != other.musical)
			return false;
		if (mystery != other.mystery)
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
		if (romance != other.romance)
			return false;
		if (sciFi != other.sciFi)
			return false;
		if (thriller != other.thriller)
			return false;
		if (unknownGenre != other.unknownGenre)
			return false;
		if (western != other.western)
			return false;
		return true;
	}	

	

}
