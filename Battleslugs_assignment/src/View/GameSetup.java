package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.BoardCheck;
import Controller.SlugController;
import Model.GridListener;
import Model.Player;
import Model.Square;

public class GameSetup extends JFrame implements GridListener
{
	
	SlugController slugControl = new SlugController();
	BoardCheck check = new BoardCheck();
	Player[] playerArr;
	int currPlayer = 0;
	int[][] generatedLocation;
	DrawGrid playerBoard;
	

	/*
	 * Shows first UI to add slugs to JPanel
	 */
	public GameSetup(Player[] playerArr) 
	{
		this.playerArr = playerArr;

		setLayout(new BorderLayout());
		setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setup(playerArr[currPlayer]);
		setVisible(true);

	}

	/*
	 * Draws random generation to board
	 */
	private void setup(Player p)
	{
		generatedLocation = new int[5][2];
		playerBoard = new DrawGrid(p.getBoard(), true, this);
		setLayout(new GridLayout(1,2));
		
		add(playerBoard);
		add(createSlugPreviewPanel());
		setVisible(true);
	}
	
	/*
	 * Adds slug preview and regenerate to a single panel
	 */
	
	public JPanel createSlugPreviewPanel()
	{
		slugControl.generateSlug(6, 6, generatedLocation, 0);
		JPanel slugPreviewPanel = new JPanel();
		slugPreviewPanel.setLayout(new BorderLayout());
		slugPreviewPanel.add(previewSlug(generatedLocation), "Center");
		slugPreviewPanel.add(createRegenerateBtn(slugPreviewPanel), "South");
		
		return slugPreviewPanel;
	}
	
	/*
	 * Check which player needs to place slugs and if both are ready, starts the game
	 */
	public void calculateNextDraw()
	{
		System.out.println("CurrPlayer: " + currPlayer);
		if(playerArr[currPlayer].getSlugsLeftToCreate() != 1)
		{
			playerArr[currPlayer].decrementSlugsLeftToCreate();
		}
		else if(currPlayer < playerArr.length-1)
		{
			System.out.println("Switch player contition satisfied");
			System.out.println("Currplayer:" + currPlayer);
			currPlayer++;
			getContentPane().removeAll();
			setup(playerArr[currPlayer]);
			setVisible(true);

		}
		else
		{
			System.out.println("Switch to game");
			this.setVisible(false);
			ShowUI UI = new ShowUI(playerArr[0],playerArr[1]);
		}
	}

	/*
	 * Creates regenerate button which allows the user to regerate a slug
	 */
	public JButton createRegenerateBtn(JPanel slugPanel)
	{
		JButton btn = new JButton("Regenerate Slug");
		btn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) 
			{ 
				slugControl.generateSlug(6, 6, generatedLocation, 0);
				JPanel slug = previewSlug(generatedLocation);
				
				getContentPane().removeAll();
				setup(playerArr[currPlayer]);
				
				slugPanel.add(slug,"Center");
			} 
		} );

		return btn;
	}

	/*
	 * Gives back a JPanel with slugs at the given location
	 */
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

	/*
	 * Checks location exists and is not already taken up by another slug
	 */
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

	/*
	 * Places slug in player's square[][] board
	 */
	void placeSlugs(int x, int y, Player p)
	{
		int xDifference = x - generatedLocation[0][0];
		int yDifference = y - generatedLocation[0][1];

		//System.out.println("xDiff: " + xDifference);
		//System.out.println("yDiff: " + yDifference);
		for (int i = 0; i < generatedLocation.length; i++) 
		{
			int newX = generatedLocation[i][0] + xDifference;
			int newY = generatedLocation[i][1] + yDifference;

			Square temp = p.getBoard()[newX][newY];
			temp.setSlug(true);
		}

		//After each SUCCESSFUL click, determine if the next user needs to click now
		calculateNextDraw();
		playerBoard.repaint();
	}



	@Override
	//Needed in actual game, and not here, thus
	//extra since multiple squares need to be changed and not just one
	public void clicked(Square s) {}

	/*
	 * Called when user clicks a field
	 */
	@Override
	public void clicked(int xSquare, int ySquare) 
	{
		if(checkValid(xSquare, ySquare,playerArr[currPlayer]))
		{
			placeSlugs(xSquare, ySquare, playerArr[currPlayer]);
		}
	}
}