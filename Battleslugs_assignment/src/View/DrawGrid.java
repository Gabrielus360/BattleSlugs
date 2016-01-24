package View;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import Controller.BoardCheck;
import Model.*;

public class DrawGrid extends JPanel implements MouseListener, GridListener
{
	private UIProperties prop = new UIProperties();
	private Square[][] board;
	private boolean showAllSquares;
	private GridListener gListener;

	/*
	 * Draws the main board with a mouseListener which calls parent GridListener
	 */
	public DrawGrid(Square[][] board, boolean showAllSquares, GridListener gListener)
	{
		this.board = board;
		this.showAllSquares = showAllSquares;
		this.gListener = gListener;
		addMouseListener(this);
	}
	
	/*
	 *  Draws the main board without a mouseListener, thus it is only displayed
	 */
	public DrawGrid(Square[][] board, boolean showAllSquares)
	{
		this.board = board;
		this.showAllSquares = showAllSquares;
	}


	public void paint(Graphics g)
	{
		super.paint(g);
		int length = 0;

		calculateSquareLength();
		length = prop.getSquareLength();

		for (int i = board.length-1; i >= 0; i--) 
		{
			for (int j = 0; j < board[1].length; j++) 
			{
				//create large black square every time
				g.setColor(Color.black);

				g.fillRect((i*length), (j*length), length, length);

				g.setColor(CalculateSquareColor(board[i][j],i,j));

				// Create smaller square of a different color inside the black square
				g.fillRect(1 + (i*length), 1 + (j*length), length-2, length-2);

			}	
		}
	}

	private void calculateSquareLength()
	{
		int temp = 0;

		//Square length has to be dynamic so as to always choose smallest side
		if(this.getSize().getHeight() > this.getSize().getWidth())
		{
			temp = (int)this.getSize().getWidth();
		}
		else
		{
			temp = (int)this.getSize().getHeight();	
		}

		// divided by number of squares
		setSquareLength(temp/board.length);
	}

	/*
	 * Can be used for any square that needs coloring
	 * Color representation:
	 * Grey = hit but empty
	 * Black = has a slug but has not been hit
	 * Red = slug and hit
	 * White = unhit square
	 * Green = major error
	 */

	private Color CalculateSquareColor(Square square, int x, int y) 
	{
		if(showAllSquares)
		{
			//if slug but no hit
			if(!square.isHit() && square.hasSlug())
			{
				return Color.black;
			}
			//If no slug
			else if(!square.isHit() && !square.hasSlug())
			{
				return Color.white;
			}

		}


		//No slug and hit
		if(board[x][y].isHit() && !board[x][y].hasSlug())
		{
			return Color.gray;
		}

		//slug and hit
		if(board[x][y].isHit() && board[x][y].hasSlug())
		{
			return Color.red;
		}
		//nothing done yet is white
		else if(!board[x][y].isHit())
		{
			return Color.white;
		}
		//Any other solutions are errors
		else
		{
			System.out.println("Error in square color processing(1)");
			System.out.println("DREW GREEN AS ERROR");
			return Color.GREEN;
		}

	}


	@Override
	public void mouseClicked(MouseEvent e) 
	{
		BoardCheck check = new BoardCheck();

		int x = e.getX();
		int y = e.getY();

		int xSquareLocation = e.getX()/getSquareLength();
		int ySquareLocation = e.getY()/getSquareLength();

		//System.out.println("X: " + xSquareLocation + " Y: " + ySquareLocation);

		boolean valid = check.isValid(xSquareLocation, ySquareLocation, board[0].length, board[1].length);

		if (valid) 
		{
			System.out.println("Valid!");
			clicked(board[xSquareLocation][ySquareLocation]);
		}
		else
		{
			System.out.println("Ignore!");
		}
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	private int getSquareLength() {
		return prop.getSquareLength();
	}

	private void setSquareLength(int squareLength) {
		System.out.println(squareLength);
		prop.setSquareLength(squareLength);

	}

	@Override
	public void clicked(Square s) 
	{
		gListener.clicked(s);
	}



}