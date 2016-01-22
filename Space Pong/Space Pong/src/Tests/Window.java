package Tests;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Window extends JFrame
{
	public Window(int xSize, int ySize)
	{
		this.setSize(xSize,ySize);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setTitle("GAME");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public JFrame getWindow()
	{
		return this;
	}
}
