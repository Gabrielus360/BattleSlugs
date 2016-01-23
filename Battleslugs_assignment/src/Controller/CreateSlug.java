package Controller;

import Model.*;

public class CreateSlug 
{

	Square[][] board;
	BoardCheck checkLocation = new BoardCheck();

	public CreateSlug(Square[][] board)
	{
		this.board = board;
		int[][] result = new int[5][5];
		
		
		generateSlug(4, 4, result, 0);

		for (int i = 0; i < result.length; i++) 
		{
			System.out.println("X: " + result[i][0]+" Y: "+result[i][1]);
		}
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
		boolean repeatedLocation = false;
		boolean recurseAgain = freeIndex<result.length;
		System.out.println(recurseAgain);
		int[] temp = generateNewLocation(startingX, startingY);
		int tempX = temp[0];
		int tempY = temp[1];
		
		System.out.println("tempx: "+ tempX +" tempY "+tempY);
		
		valid = checkLocation.isValid(tempX, tempY, board[0].length-1, board[1].length-1);
		
		System.out.println(valid);
		
		if(valid && recurseAgain)
		{
			//need to check if there is a slug AFTER the check to see if location exists
			empty = checkLocation.isEmpty(board[tempX][tempY]);
			
			for (int i = 0; i < result[0].length; i++) 
			{
				if(result[i][0] == tempX)
				{
					if(result[i][1] == tempY)
					{
						repeatedLocation = true;
					}
				}
			}

			if(valid && empty && !repeatedLocation)
			{
				//System.out.println(freeIndex);
				result[freeIndex][0] = tempX;
				result[freeIndex][1] = tempY;
				freeIndex++;
				generateSlug(tempX, tempY, result, freeIndex);
			}
			else if(repeatedLocation)
			{
				generateSlug(startingX, startingY, result, freeIndex);
			}
		}
		//might be freeIndex<(result.length -1)
		else if(recurseAgain)
		{
			generateSlug(startingX, startingY, result, freeIndex);
		}

		return result;
	}
}