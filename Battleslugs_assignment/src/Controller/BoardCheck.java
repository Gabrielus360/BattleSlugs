package Controller;

import Model.Square;

public class BoardCheck 
{
	

	/*
	 * Checks if a square currently has a slug, returns true and false accordingly
	 */
	public boolean isEmpty(Square square)
	{
		boolean result = true;
		if(square.hasSlug())
		{
			result = false;
		}
		
		return result;
	}

	/*
	 * Checks if location given goes out of the board
	 * @return boolean - when true, location is inside board, when invalid returns false
	 */
	public boolean isValid(int x, int y,int xMax,int yMax)
	{
		boolean result = false;

		if(x>=0 && x<xMax && y>=0 && y<yMax)
		{
			result = true;
			//System.out.println("Entered here");
		}
		return result;
	}

}
