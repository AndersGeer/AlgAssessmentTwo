package controllers;

import models.Movie;
import models.User;
import utils.JSONSerializer;
import utils.Serializer;

import static models.Fixtures.movies;
import static models.Fixtures.users;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;


public class PersistenceTest 
{
	RecommenderAPI recommender;

	void populate (RecommenderAPI recommender)
	{
		for (User user : users)
		{
			recommender.addUser(user);
		}	
		for (Movie movie : movies)
		{
			recommender.addMovie(movie);
		}

	}
	private void deleteFile(String fileName)
	{
		File datastore = new File ("testdatastore.xml");
		if (datastore.exists())
		{
			datastore.delete();
		}
	}

	@Test
	public void testJSONSerializer() throws Exception
	{ 
		String datastoreFile = "testdatastore.json";
		deleteFile (datastoreFile);

		Serializer serializer = (Serializer) new JSONSerializer(new File (datastoreFile));

		recommender = new RecommenderAPI(serializer); 
		populate(recommender);
		recommender.store();

		RecommenderAPI recommender2 =  new RecommenderAPI(serializer);
		recommender2.load();

		
		
		assertEquals (recommender.getUsers().size(), recommender2.getUsers().size());
		for (User user : recommender.getUsers())
		{
			assertTrue (recommender2.getUsers().contains(user));
		}
		//deleteFile ("testdatastore.json");
	}
}
