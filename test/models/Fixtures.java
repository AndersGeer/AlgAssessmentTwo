package models;

public class Fixtures
{
	public static boolean[] bools = 
		{
				true,
				false,
				false,
				true,
				false,
				false,
				true,
				false,
				false,
				true,
				false,
				false,
				true,
				false,
				false,
				true,
				false,
				false
		};
	public static User[] users =
		{
				new User (1,"marge", "simpson", 41,"F", "Cook", 12345),
				new User (2,"lisa",  "simpson", 12,"F","Student",12453),
				new User (3,"bart",  "simpson", 16, "M", "Bully", 54321),
				new User (4,"maggie","simpson", 82,"F","Retired",15243)
		};

	public static Movie[] movies =
		{
			new Movie (1,"Toy Story","ToystoryLINK IMDB",bools),
			new Movie (2,"Lassie","LassieLINK IMDB",bools),
			new Movie (3,"Smokey and the bandit","SmokeyAndTheBanditLINK IMDB",bools),
			new Movie (4,"Shawshank redemption","Shawshank redemptionLINK IMDB",bools),
			new Movie (5,"The hobbit","The hobbitLINK IMDB",bools),
			new Movie (6,"Star wars","StarwarsLINK IMDB",bools)
		};

	public static Rating[]ratings =
		{
				new Rating(users[0],movies[0],2),
				new Rating(users[0],movies[1],4),
				new Rating(users[0],movies[3],5),
				new Rating(users[0],movies[4],1),
				new Rating(users[0],movies[5],0),
				
				new Rating(users[1],movies[0],5),
				new Rating(users[1],movies[1],5),
				new Rating(users[1],movies[3],2),
				new Rating(users[1],movies[4],3),
				
				new Rating(users[2],movies[0],0),
				new Rating(users[2],movies[2],5),
				new Rating(users[2],movies[3],4),
				new Rating(users[2],movies[4],4),
				new Rating(users[2],movies[5],5),
				
				new Rating(users[3],movies[0],2),
				new Rating(users[3],movies[1],5),
				new Rating(users[3],movies[3],1),
				new Rating(users[3],movies[4],0),
				new Rating(users[3],movies[5],0),
				
		};
	
	public static Rating[] ratingsByUserThree=
		{
				ratings[9],
				ratings[10],
				ratings[11],
				ratings[12],
				ratings[13]
		};

	
}
