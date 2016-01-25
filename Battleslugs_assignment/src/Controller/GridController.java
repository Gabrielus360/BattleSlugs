package Controller;

import javax.swing.JPanel;

import Model.GridListener;
import Model.Square;
import View.DrawGrid;

public class GridController implements GridListener
{
	int xClicked = 0;
	int yClicked = 0;
	Square[][] board;
	JPanel grid;
	int xSquareLocation;
	int ySquareLocation;
	BoardCheck check =new BoardCheck();
	
	public GridController(Square[][] board, boolean showAll) 
	{
		this.board = board;
		grid = new DrawGrid(board, showAll, this);
	}

	@Override
	public void clicked(Square s) 
	{
		System.out.println("Button CLICKED");
		s.setHit(true);
		grid.repaint();
	}
	
	public int getxClicked() {
		return xClicked;
	}

	public void setxClicked(int xClicked) {
		this.xClicked = xClicked;
	}

	public int getyClicked() {
		return yClicked;
	}

	public void setyClicked(int yClicked) {
		this.yClicked = yClicked;
	}

	public JPanel getGrid() {
		return grid;
	}

	public void setGrid(JPanel grid) {
		this.grid = grid;
	}

	@Override
	public void clicked(int xSquare, int ySquare) {
		//forced to implement due to lack of delegates in java.
		//Only needed in the beginning of game when slugs need to be placed		
	}

}
