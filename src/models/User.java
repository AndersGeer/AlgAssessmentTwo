package models;

import java.util.Collection;
import java.util.HashSet;

import utils.ToJsonString;

public class User 
{
	private Collection<Rating> ratings;
	
	public static int userNumber = 1;
	int userId;
	public String firstName;
	public String lastName;
	public int age;
	public String gender;
	public String occupation;
	public int zipCode;

	public User(int userId, String firstName, String lastName, int age, String gender, String occupation, int zipCode) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
		this.zipCode = zipCode;
		userNumber++;
		ratings = new HashSet<Rating>();
	}

	public User(String firstName, String lastName, int age, String gender, String occupation, int zipCode) 
	{
		this.userId = userNumber++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
		this.zipCode = zipCode;
		ratings = new HashSet<Rating>();
	}

	public User(String firstName, String lastName, int age, String gender, String occupation) {
		this.userId = userNumber++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
		ratings = new HashSet<Rating>();
	}

	public int getUserId()
	{
		return userId;
	}
	
	public void addRating(Rating r)
	{
		ratings.add(r);
	}
	
	public boolean hasUserSeenMovie(int movieId)
	{
		for (Rating rating : ratings) {
			if (rating.getMovieId() == movieId) 
			{
				return true;
			}
		}
		return false;
	}
	public Collection<Rating> getRatings() 
	{
		return ratings;
	}
	
	@Override
	public String toString()
	{
		return ("\n\nUserId: " +userId + "\nName: " + firstName + " " + lastName);
	}


	//Automatic implementation
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (age != other.age)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (occupation == null) {
			if (other.occupation != null)
				return false;
		} else if (!occupation.equals(other.occupation))
			return false;
		if (zipCode != other.zipCode)
			return false;
		return true;
	}



	

}





