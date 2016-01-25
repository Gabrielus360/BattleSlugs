package Controller;

import Model.*;

public class SlugController 
{
	BoardCheck checkLocation = new BoardCheck();
	int[][] slugLocations = new int[5][5];
	Square[][] board=new Square[16][16];


	public SlugController()
	{
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = new Square();
			}
		}
	}

	/*
	 * 8		1		2
	 * 7	[original]	3
	 * 6		5		4
	 */

	public int[] generateNewLocation(int x, int y)
	{
		int newx = x;
		int newy = y;
		int rand = 0;

		//Min + (int)(Math.random() * ((Max - Min) + 1))
		rand = 1 + (int)(Math.random() * ((8 - 1) + 1));
		
		//change x according to number
		if(rand >= 2 && rand<= 4)
		{
			newx++;
		}
		else if(rand >=6 && rand<=8)
		{
			newx--;
		}

		//change y according to number
		if(rand == 8 || rand == 1 || rand==2)
		{
			newy++;
		}
		else if(rand >= 4 && rand<= 6 )
		{
			newy--;
		}

		//Gives back new (suggested and unverified location)
		return new int[] {newx,newy};
	}

	/**
	 * Uses recursion to create a valid slug. It makes sure the slug does 
	 * not go off the grid but then some other code needs to handle changing 
	 * color of the first square to red and verifying no other squares are on
	 * the same location
	 * Format of result:
	 * result[index][0] = XLOCATION
	 * result[index][1] = YLOCATION
	 */
	public int[][] generateSlug(int startingX, int startingY, int[][] result, int freeIndex)
	{
		boolean recurseAgain = freeIndex<result.length;
		if(recurseAgain)
		{
			boolean empty = false;
			boolean valid = false;
			boolean repeatedLocation = false;

			//System.out.println("FreeIndex: " + freeIndex +" result length "+ result.length);

			int[] temp = generateNewLocation(startingX, startingY);
			int tempX = temp[0];
			int tempY = temp[1];
			//System.out.println("tempX" + tempX + " tempY " + tempY);

			valid = checkLocation.isValid(tempX, tempY, board[0].length-1, board[1].length-1);


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
		}

		return result;
	}

	public int[][] getSlugLocations() {
		return slugLocations;
	}

	public void setSlugLocations(int[][] slugLocations) {
		this.slugLocations = slugLocations;
	}
}