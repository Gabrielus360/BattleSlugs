package Controller;

import Model.*;

public class CreateSlug 
{
	public CreateSlug(Square[][] board)
	{
		
	}
	
	public int[] generateNewLocation(int x, int y)
	{
		//Min + (int)(Math.random() * ((Max - Min) + 1))

		int rand = 1 + (int)(Math.random() * ((8 - 1) + 1));

		/*
		 * 8		1		2
		 * 7	[original]	3
		 * 6		5		4
		 */

		int newx = x;
		int newy = y;
		//change x
		if(rand<= 4 && rand >= 2)
		{
			newx++;
		}
		else if(rand<=8 && rand >=6)
		{
			newx--;
		}

		//change y
		if(rand == 8 || (1<rand && rand >2))
		{
			newy--;
		}
		else if(rand >= 4 && rand<= 6 )
		{
			newy++;
		}
		
		//Gives back new (suggested and unverified location)
		return new int[] {newx,newy};
	}
	
	
}