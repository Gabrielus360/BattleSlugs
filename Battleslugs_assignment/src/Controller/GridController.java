package Controller;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import Model.GridListener;
import Model.Square;
import View.DrawGrid;
import View.UIProperties;

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

}
