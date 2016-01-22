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
	 *hits store the hits the other player/s has made on this player
	 *First two variables are coordinates
	 *Result in those coordinates represent the following:
	 * 0 = no hit = white
	 * 1 = hit , no slug = grey
	 * 2 = hit and slug  = red
	 */
	int[][] hits = new int[12][12];



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

	public int getSuccessfulHits() 
	{
		return successfulHits;
	}

	public void setSuccessfulHits(int successfulHits) 
	{
		this.successfulHits = successfulHits;
	}

	public int getSlugsLeftToCreate() 
	{
		return slugsLeftToCreate;
	}

	public void setSlugsLeftToCreate(int slugsLeftToCreate) 
	{
		this.slugsLeftToCreate = slugsLeftToCreate;
	}
	public int getLivesLeft() {
		return livesLeft;
	}

	public void setLivesLeft(int livesLeft) {
		this.livesLeft = livesLeft;
	}
	public int getTotalSlugs() {
		return totalSlugs;
	}

	public void setTotalSlugs(int totalSlugs) {
		this.totalSlugs = totalSlugs;
	}
	public int[][] getHits() {
		return hits;
	}

	public void setHits(int[][] hits) {
		this.hits = hits;
	}

}
