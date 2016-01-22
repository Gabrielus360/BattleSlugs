package Controller;

import Model.Square;

public class BoardControl 
{

	/*
	 * Used locations
	 */

	public boolean isEmpty(int x, int y,Square[][] board)
	{
		if(board[x][y].hasSlug())
		{
			return false;
		}
		return true;
	}

	public boolean isValid(int x, int y,int xMax,int yMax)
	{
		boolean result = true;

		if(x<xMax && x>yMax)
		{
			result = false;
		}
		else if(y<0 && y>11)
		{
			result = false;
		}
		return result;
	}

}
