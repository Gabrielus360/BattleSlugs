import Model.Player;
import Model.Slug;
import Model.Square;
import View.ShowUI;

public class Main 
{
	public static void main(String[] args) 
	{

		Player p1 = new Player();
		Player p2 = new Player();

		int hits[][] = new int[12][12];
		Square[][] temp = new Square[12][12];
		
		for (int i = 0; i < hits.length; i++) 
		{
			for (int j = 0; j < hits[1].length; j++) 
			{
				//hits[i][j] = 2;
				temp[i][j] = new Square();
				temp[i][j].setSlug(new Slug());
			}	
		}
		
		temp[1][2] = new Square();
		hits[1][2] = 1;
		hits[3][5] = 0;
		
		
		
		p1.setHits(hits);
		p1.setBoard(temp);

		ShowUI UI = new ShowUI(p1,p2); 
	}

}