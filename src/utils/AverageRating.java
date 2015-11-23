package utils;

public class AverageRating implements Comparable<AverageRating>
{
	private int movieId;
	private float avgRating;
	private int numberOfRatings;
	private int totalRating;
	
	public AverageRating(int movieId) 
	{
		this.movieId = movieId;
		avgRating = 0;
		numberOfRatings = 0;
		totalRating = 0;
	}
	
	public void addRating(int specificRating)
	{
		totalRating += specificRating;
		numberOfRatings++;
		if (numberOfRatings >= 2) 
		{
			avgRating = CalcAvg();
		}
	}

	private float CalcAvg() 
	{
		return totalRating/numberOfRatings;
	}
	
	public float getAvg()
	{
		return avgRating;
	}
	
	public int getMovieId()
	{
		return movieId;
	}

	@Override
	public int compareTo(AverageRating other) 
	{
		if (getAvg() > other.getAvg()) 
		{
			return 1;
		}
		else if (getAvg() < other.getAvg()) 
		{
			return -1;
		}
		return 0;
	}

	@Override
	public String toString()
	{
		return new ToJsonString(getClass(), this).toString();
	}
	
	

	
	
}
