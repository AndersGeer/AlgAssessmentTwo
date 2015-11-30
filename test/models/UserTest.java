package models;

import static models.Fixtures.users;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserTest 
{
	User homer;

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	@Before
	public void beforeTest() throws Exception
	{
		homer = new User("Homer","Simpson",43,"M","Sofa guy");
	}
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
	public void UserNegativeAge() throws Exception
	{
		exception.expect(Exception.class);
		User u = new User("Anders", "Jakobsen", -1, "M", "Student");
	}
	@Test
	public void UserAgeZero() throws Exception
	{
		User u = new User("Anders", "Jakobsen", 0, "M", "Student");
	}
	@Test
	public void UserAge150() throws Exception
	{
		User u = new User("Anders", "Jakobsen", 150, "M", "Student");
	}
	@Test
	public void UserAge151() throws Exception
	{
		exception.expect(Exception.class);
		User u = new User("Anders", "Jakobsen", 151, "M", "Student");
	}
	
	@Test
	public void userGenderNull() throws Exception
	{
		exception.expect(NullPointerException.class);
		User u = new User("Anders", "Jakobsen", 23, null, "Student");
	}
	@Test
	public void userGenderEmpty() throws Exception
	{
		exception.expect(Exception.class);
		User u = new User("Anders", "Jakobsen", 23, "", "Student");
	}
	@Test
	public void UserGender_m() throws Exception
	{
		
		User u = new User("Anders", "Jakobsen", 23, "m", "Student");
	}
	@Test
	public void UserGender_f() throws Exception
	{
		
		User u = new User("Anders", "Jakobsen", 23, "f", "Student");
	}
	@Test
	public void UserGender_F() throws Exception
	{
		
		User u = new User("Anders", "Jakobsen", 23, "F", "Student");
	}
	@Test
	public void UserGender_A() throws Exception
	{
		exception.expect(Exception.class);
		User u = new User("Anders", "Jakobsen", 23, "A", "Student");
	}
	@Test
	public void UserFirstNameEmpty() throws Exception
	{
		exception.expect(Exception.class);
		User u = new User("", "Jakobsen", 23, "A", "Student");
	}
	@Test
	public void UserLastNameEmpty() throws Exception
	{
		exception.expect(Exception.class);
		User u = new User("Anders", "", 23, "A", "Student");
	}
	@Test
	public void UserFirstNameNull() throws Exception
	{
		exception.expect(Exception.class);
		User u = new User(null, "Jakobsen", 23, "A", "Student");
	}
	@Test
	public void UserLastNameNull() throws Exception
	{
		exception.expect(Exception.class);
		User u = new User("Anders", null, 23, "A", "Student");
	}
	@Test
	public void UserOccEmpty() throws Exception
	{
		exception.expect(Exception.class);
		User u = new User("Anders", "Jakobsen", 23, "A", "");
	}
	@Test
	public void UserOccNull() throws Exception
	{
		exception.expect(Exception.class);
		User u = new User("Anders", null, 23, "A", null);
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
	  public void testEquals() throws Exception
	  {
	    User homer2 = new User ("Homer", "Simpson", 43,"M","Sofa guy"); 
	    User bart   = new User ("bart", "simpson",13,"M","Student"); 

	    assertEquals(homer, homer);
	    assertEquals(homer, homer2);
	    assertNotEquals(homer, bart);
	  } 

}
