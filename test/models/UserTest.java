package models;

import static models.Fixtures.users;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class UserTest 
{
	User homer = new User("Homer","Simpson",43,"M","Sofa guy");

	@Test
	public void testCreate()
	{
		assertEquals ("Homer",              homer.firstName);
		assertEquals ("Simpson",            homer.lastName);
		assertEquals (43,   				homer.age);   
		assertEquals ("M",             		homer.gender);
		assertEquals ("Sofa guy",           homer.occupation);
	}

	@Test
	public void testIds()
	{
		Set<Integer> ids = new HashSet<>();
		for (User user : users)
		{
			ids.add(user.userId);
		}
		assertEquals (users.length, ids.size());
	}
	
	@Test
	  public void testToString()
	  {
	    assertEquals ("\n\nUserId: " +homer.userId + "\nName: " + homer.firstName + " " + homer.lastName, homer.toString());
	  }
	
	@Test
	  public void testEquals()
	  {
	    User homer2 = new User ("Homer", "Simpson", 43,"M","Sofa guy"); 
	    User bart   = new User ("bart", "simpson",13,"M","Student"); 

	    assertEquals(homer, homer);
	    assertEquals(homer, homer2);
	    assertNotEquals(homer, bart);
	  } 

}
