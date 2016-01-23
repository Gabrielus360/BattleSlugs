package View;

import java.awt.Dimension;
import java.awt.Toolkit;

public class UIProperties 
{
	
	Dimension screenSize;
	int width;
	int height;
	int SquareLength;
	

	public UIProperties() 
	{
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int)screenSize.getWidth();
		height = (int)screenSize.getHeight();
	}	
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getSquareLength() {
		return SquareLength;
	}
	public void setSquareLength(int squareLength) {
		SquareLength = squareLength;
	}
}
