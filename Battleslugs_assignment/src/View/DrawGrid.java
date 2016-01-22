package View;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import Model.*;

public class DrawGrid extends JPanel implements MouseListener
{
	private UIProperties prop;
	private Square[][] board;
	private int[][] hits;
	private boolean showAllSquares;

	int squareLength = 0;

	/*
	 * To be used when drawing main board
	 */
	public DrawGrid(Square[][] board, int[][] hits, boolean showAllSquares)
	{
		this.board = board;
		this.hits = hits;
		this.showAllSquares = showAllSquares;
		addMouseListener(this);
	}
	
	/*
	 * Draws slug preview
	 * Here there are no hits and the player needs to be shown all squares
	 * To utalise the same code, I needed to create an empty hits[][] variable
	 */
	public DrawGrid(Square[][] board)
	{
		this.board = board;
		this.hits = new int[board.length][board[1].length];
		this.showAllSquares = true;
	}



	public void paint(Graphics g)
	{
		super.paint(g);

		//System.out.println((int)this.getSize().getWidth());
		//System.out.println((int)this.getSize().getHeight());

		calculateSquareLength();

		int length = getSquareLength();

		for (int i = board.length-1; i >= 0; i--) 
		{
			for (int j = 0; j < board[1].length; j++) 
			{
				//create large black square every time
				g.setColor(Color.black);

				g.fillRect((i*length), (j*length), squareLength, squareLength);

				g.setColor(CalculateSquareColor(board[i][j],i,j));

				// Create smaller square of a different color inside the black square
				g.fillRect(1 + (i*length), 1 + (j*length), squareLength-2, squareLength-2);

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
		Slug temp = square.getSlug();

		if(showAllSquares)
		{
			//if slug but no hit
			if(hits[x][y] == 0 && temp != null)
			{
				return Color.black;
			}
			//If no slug
			else if(temp == null)
			{
				return Color.white;
			}
			
		}

		/*
		System.out.println(showAllSquares);
		System.out.println(hits[x][y]);
		System.out.println(temp);
		 */

		//No slug and hit
		if(hits[x][y]== 1 && temp == null)
		{
			return Color.gray;
		}

		//slug and hit (hit = 2 is a confirmed hit)
		if(hits[x][y]== 2) //|| (hits[x][y] == 1 && temp != null))
		{
			//Not sure if second part of IF is required - if error when removed,
			//hits is storing as 1 when there is a slug instead of 0
			return Color.red;
		}
		//nothing done yet is white
		else if(hits[x][y] == 0)
		{
			return Color.white;
		}
		//Any other solutions are errors ex: having hits 1 and having a slug there
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
		//IMPORTANT: starts from 0 like arrays
		System.out.println(e.getX());;
		System.out.println(e.getY());;
		int x = 0;
		int y = 0;
		

		x = e.getX()/getSquareLength();
		y = e.getY()/getSquareLength();

		System.out.println(x);
		System.out.println(y);
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	private int getSquareLength() {
		return squareLength;
	}

	private void setSquareLength(int squareLength) {
		this.squareLength = squareLength;
	}

}