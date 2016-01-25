package Controller;

import javax.swing.JFrame;

import Model.Player;
import View.DrawGrid;
import View.GameSetup;
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

		GameSetup setup = new GameSetup(new Player[]{p1,p2});
		//ShowUI UI = new ShowUI(p1,p2); 
	}
}
