package Model;

public class Player 
{
	private int attempts = 0;
	private int successfulHits = 0;
	private int livesLeft = 0;
	private int slugsLeftToCreate = 5;
	int totalSlugs = slugsLeftToCreate * 5;
	Square[][] board = new Square[12][12]; 
	
	/*
	 * Initializing board
	 */
	public Player() 
	{
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[1].length; j++) 
			{
				board[i][j] = new Square();
			}
		}
	}
	
	public Square[][] getBoard() {

		return board;
	}

	public void setBoard(Square[][] board) {
		this.board = board;
	}

	public int getAttempts() 
	{
		return attempts;
	}

	public void setAttempts(int attempts) 
	{
		this.attempts = attempts;
	}
	
	public void incrementAttempts() 
	{
		this.attempts++;
	}

	public int getSuccessfulHits() 
	{
		return successfulHits;
	}

	public void setSuccessfulHits(int successfulHits) 
	{
		this.successfulHits = successfulHits;
	}
	
	public void incrementSuccessfulHits() 
	{
		this.successfulHits++;
	}

	public int getSlugsLeftToCreate() 
	{
		return slugsLeftToCreate;
	}

	public void setSlugsLeftToCreate(int slugsLeftToCreate) 
	{
		this.slugsLeftToCreate = slugsLeftToCreate;
	}
	
	public void decrementSlugsLeftToCreate() 
	{
		this.slugsLeftToCreate--;
	}
	
	public int getLivesLeft() {
		return livesLeft;
	}

	public void setLivesLeft(int livesLeft) {
		this.livesLeft = livesLeft;
	}
	
	public void incrementLivesLeft() {
		this.livesLeft++;
	}
	
	public int getTotalSlugs() {
		return totalSlugs;
	}

	public void setTotalSlugs(int totalSlugs) {
		this.totalSlugs = totalSlugs;
	}
}
