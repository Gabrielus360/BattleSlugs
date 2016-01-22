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
	private UIProperties prop = new UIProperties();
	private Square[][] board;
	private boolean showAllSquares;
	

	/*
	 * To be used when drawing main board
	 */
	public DrawGrid(Square[][] board, boolean showAllSquares)
	{
		this.board = board;
		this.showAllSquares = showAllSquares;
		addMouseListener(this);
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
		return prop.getSquareLength();
	}

	private void setSquareLength(int squareLength) {
		System.out.println(squareLength);
		prop.setSquareLength(squareLength);
		
	}

}