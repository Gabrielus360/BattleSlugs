package Controller;

import Model.Square;

public class BoardCheck 
{
	

	/*
	public boolean isEmpty(int x, int y,Square[][] board)
	{
		if(board[x][y].hasSlug())
		{
			return false;
		}
		return true;
	}
	*/
	
	/*
	 * Passing square is more efficient on both sides, 
	 * kept code just in case
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

	public boolean isValid(int x, int y,int xMax,int yMax)
	{
		boolean result = false;

		if(x>0 && x<xMax && y>0 && y<yMax)
		{
			result = true;
			//System.out.println("Entered here");
		}
		return result;
	}

}
