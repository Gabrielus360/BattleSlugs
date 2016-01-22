import Model.Player;
import View.ShowUI;

public class Main 
{
	public static void main(String[] args) 
	{

		Player p1 = new Player();
		Player p2 = new Player();

		ShowUI UI = new ShowUI(p1,p2); 
	}

}