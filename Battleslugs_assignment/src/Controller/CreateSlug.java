package Controller;

import Model.*;

public class CreateSlug 
{

	Square[][] board;
	BoardCheck checkLocation = new BoardCheck();

	public CreateSlug(Square[][] board)
	{
		this.board = board;
	}

	/*
	 * 8		1		2
	 * 7	[original]	3
	 * 6		5		4
	 */

	public int[] generateNewLocation(int x, int y)
	{
		//Min + (int)(Math.random() * ((Max - Min) + 1))

		int newx = x;
		int newy = y;
		int rand = 0;

		rand = 1 + (int)(Math.random() * ((8 - 1) + 1));
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

	public int[][] generateSlug(int startingX, int startingY, int[][] result, int freeIndex)
	{
		boolean empty = false;
		boolean valid = false;
		int[] temp = generateNewLocation(startingX, startingY);
		int tempX = temp[0];
		int tempY = temp[1];

		empty = checkLocation.isEmpty(board[tempX][tempY]);
		valid = checkLocation.isValid(tempX, tempY, board[0].length, board[1].length);

		for (int i = 0; i < result[0].length; i++) 
		{
			if(result[i][0] == tempX)
			{
				if(result[i][1] == tempY)
				{
					valid = false;
				}
			}
		}
		
		if(valid && empty)
		{
			result[freeIndex][0] = tempX;
			result[freeIndex][1] = tempY;
			freeIndex++;
		}
		//might be freeIndex<result.length -1
		if(freeIndex<result.length)
		{
		generateSlug(tempX, tempY, result, freeIndex);
		}
		
		return result;
	}
}