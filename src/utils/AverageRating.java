package utils;

public class AverageRating implements Comparable<AverageRating>
{
	private int movieId;
	private double avgRating;
	private double numberOfRatings;
	private double totalRating;
	
	public AverageRating(int movieId) 
	{
		this.movieId = movieId;
		avgRating = 0.0;
		numberOfRatings = 0;
		totalRating = 0;
	}
	
	public void addRating(int specificRating)
	{
		totalRating += specificRating;
		numberOfRatings++;
	}

	private double CalcAvg() 
	{
		return totalRating/numberOfRatings;
	}
	
	public double getAvg()
	{
		avgRating = CalcAvg();
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
		if (getAvg() < other.getAvg()) 
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
