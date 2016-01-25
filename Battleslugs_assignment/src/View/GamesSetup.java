package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.BoardCheck;
import Controller.SlugController;
import Model.GridListener;
import Model.Player;
import Model.Square;

public class GamesSetup extends JFrame implements GridListener
{
	Player[] playerArr;
	int currPlayer = 0;
	SlugController slugControl = new SlugController();
	BoardCheck check = new BoardCheck();
	int[][] generatedLocation;
	DrawGrid playerBoard;


	public GamesSetup(Player[] playerArr) 
	{
		this.playerArr = playerArr;
	
		setLayout(new BorderLayout());
		setSize(800,600);
		//for (int i = 0; i < playerArr.length; i++) 
		//{
			//for (int j = 0; j < playerArr[i].getTotalSlugs(); j++) 
			//{
				setup(playerArr[0]);
				currPlayer = 0;
			//}
		//}
		
		setVisible(true);

	}

	private void setup(Player p)
	{
		generatedLocation = new int[5][2];
		slugControl.generateSlug(6, 6, generatedLocation, 0);
		JPanel slugPreviewPanel = new JPanel();
		slugPreviewPanel= previewSlug(generatedLocation);
		slugPreviewPanel.setVisible(true);

		playerBoard = new DrawGrid(p.getBoard(), true, this);
		setLayout(new GridLayout(1,2));
		add(playerBoard);
		add(slugPreviewPanel);

	}

	public JPanel previewSlug(int[][] locations)
	{
		int xlocation = 0;
		int yLocation = 0;
		Square[][] board;
		board = new Square[12][12];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[1].length; j++) 
			{
				board[i][j] = new Square();
			}
		}

		for (int i = 0; i < locations.length; i++) 
		{
			xlocation = locations[i][0];
			yLocation = locations[i][1];
			board[xlocation][yLocation].setSlug(true);
		}
		//So that original square is indicated by a red color 
		board[locations[0][0]][locations[0][1]].setHit(true);

		DrawGrid grid = new DrawGrid(board,true);
		grid.setVisible(true);
		return grid;
	}

	private boolean checkValid(int x, int y, Player p)
	{
		boolean success = true;

		int xDifference = x - generatedLocation[0][0];
		int yDifference = y - generatedLocation[0][1];

		for (int i = 0; i < generatedLocation.length; i++) 
		{
			int newX = generatedLocation[i][0] + xDifference;
			int newY = generatedLocation[i][1] + yDifference;

			success = check.isValid(newX, newY, p.getBoard().length, p.getBoard().length);
			if(success)
			{
				success = check.isEmpty(p.getBoard()[newX][newY]);
				if(!success)
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		return true;
	}

	void placeSlugs(int x, int y, Player p)
	{
		int xDifference = x - generatedLocation[0][0];
		int yDifference = y - generatedLocation[0][1];

		System.out.println("xDiff: " + xDifference);
		System.out.println("yDiff: " + yDifference);
		for (int i = 0; i < generatedLocation.length; i++) 
		{
			int newX = generatedLocation[i][0] + xDifference;
			int newY = generatedLocation[i][1] + yDifference;
			
			Square temp = p.getBoard()[newX][newY];
			temp.setSlug(true);
		}
		
		playerBoard.repaint();
	}



	@Override
	//Needed in actual game, and not here, thus
	//extra since multiple squares need to be changed and not just one
	public void clicked(Square s) {}


	@Override
	public void clicked(int xSquare, int ySquare) 
	{
	
		
		if(checkValid(xSquare, ySquare,playerArr[currPlayer]))
		{
			placeSlugs(xSquare, ySquare, playerArr[currPlayer]);
		}
	}
}