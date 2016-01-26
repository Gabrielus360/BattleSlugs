package Controller;

import java.util.Random;

import Model.*;

public class SlugController 
{
	BoardCheck checkLocation = new BoardCheck();
	Square[][] board=new Square[16][16];
	static Random r = new Random();
	int rand = 0;


	public SlugController()
	{
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = new Square();
			}
		}
	}

	/*
	 * 6		3		0
	 * 5	[original]	2
	 * 4		7		1
	 */
	public int[] generateNewLocation(int x, int y)
	{
		int newx = x;
		int newy = y;
		
		
		
		//Min + (int)(Math.random() * ((Max - Min) + 1))
		rand = (int)(r.nextInt(8));

		if (rand < 3) //0,1,2
		{
		    newx++;
		}
		else if (rand < 6) //3,4,5
		{
		    newx--;
		}

		//change y according to number
		if(rand % 3 == 0) //0,3,6
		{
		    newy++;
		}
		else if(rand % 3 == 1) //1,4,7
		{
		    newy--;
		}
		
		/*
		switch (rand){
    	case 1:         newy++; break;
    	case 2: newx++; newy++; break;
    	case 3: newx++;         break;
    	case 4: newx++; newy--; break;
    	case 5:         newy--; break;
    	case 6: newx--; newy--; break;
    	case 7: newx--;         break;
    	case 8: newx--; newy++; break;
		}

		
		*/

		/*
		//Shorter, not recommended by teacher
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

		 */

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
}