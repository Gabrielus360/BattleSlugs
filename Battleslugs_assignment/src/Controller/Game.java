package Controller;

import javax.swing.JFrame;

import Model.Player;
import View.DrawGrid;
import View.GamesSetup;
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

		GamesSetup setup = new GamesSetup(new Player[]{p1,p2});
		//ShowUI UI = new ShowUI(p1,p2); 
	}
}
