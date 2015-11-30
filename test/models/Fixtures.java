package models;

public class Fixtures
{

	public static User[] users =
		{
				new User (1,"marge", "simpson", 41,"F", "Cook", 12345),
				new User (2,"lisa",  "simpson", 12,"F","Student",12453),
				new User (3,"bart",  "simpson", 16, "M", "Bully", 54321),
				new User (4,"maggie","simpson", 82,"F","Retired",15243)
		};

	public static Movie[] movies =
		{
			new Movie (1,"Toy Story",1994,"ToystoryLINK IMDB"),
			new Movie (2,"Lassie",1984,"LassieLINK IMDB"),
			new Movie (3,"Smokey and the bandit",1989,"SmokeyAndTheBanditLINK IMDB"),
			new Movie (4,"Shawshank redemption",2001,"Shawshank redemptionLINK IMDB"),
			new Movie (5,"The hobbit",2006,"The hobbitLINK IMDB"),
			new Movie (6,"Star wars",1979,"StarwarsLINK IMDB")
		};

	public static Rating[]ratings =
		{
				new Rating(1,users[0],movies[0],2),
				new Rating(2,users[0],movies[1],4),
				new Rating(3,users[0],movies[3],5),
				new Rating(4,users[0],movies[4],1),
				new Rating(5,users[0],movies[5],0),
				
				new Rating(6,users[1],movies[0],5),
				new Rating(7,users[1],movies[1],5),
				new Rating(8,users[1],movies[3],2),
				new Rating(9,users[1],movies[4],3),
				
				new Rating(10,users[2],movies[0],0),
				new Rating(11,users[2],movies[2],5),
				new Rating(12,users[2],movies[3],4),
				new Rating(13,users[2],movies[4],4),
				new Rating(14,users[2],movies[5],5),
				
				new Rating(15,users[3],movies[0],2),
				new Rating(16,users[3],movies[1],5),
				new Rating(17,users[3],movies[3],1),
				new Rating(18,users[3],movies[4],0),
				new Rating(19,users[3],movies[5],5)
				
				
				//Movie 1, 4 votes, Total =  9 - avg = 2.25
				//Movie 2, 3 votes, Total = 14 - avg = 4.6
				//Movie 3, 1 votes, Total =  5 - avg = 5
				//Movie 4, 4 votes, Total = 12 - avg = 3
				//Movie 5, 4 votes, Total =  8 - avg = 2
				//Movie 6, 3 votes, Total =  10 - avg = .33
				
				//User 2 User 1 similarity =  8 + 16 +  8 + 12 	= 44
				//User 2 User 3 similarity =  0 + 12 + 16 		= 28
				//User 2 User 4 similarity =  8 + 20 + 16 + 12 	= 46
		};
	
	public static Rating[] ratingsByUserThree=
		{
				ratings[9],
				ratings[10],
				ratings[11],
				ratings[12],
				ratings[13]
		};
	
	public static Movie[] reccomendationForUserTwo = 
		{
			movies[5]
		};

	
}
