package Controller;

import Model.Player;
import View.ShowUI;

public class Game 
{
	/*
	 * Initializes two players and hands them down to ShowUI to display the game to the user
	 */
	public Game()
	{
		Player p1 = new Player();
		Player p2 = new Player();

		ShowUI UI = new ShowUI(p1,p2); 

		//Created here for testing purposes
		
		//CreateSlug generateSlug = new CreateSlug();
		//int[][] result = generateSlug.getSlugLocations();
	}
}
