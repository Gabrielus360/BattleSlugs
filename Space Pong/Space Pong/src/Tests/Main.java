package Tests;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Main 
{
	public static void main(String[] args) 
	{
		Window w = new Window(1600,900);
		JFrame GameWindow = w.getWindow();
		
		JButton j = new JButton();
		j.setVisible(true);
		
		GameWindow.add(j);
		GameWindow.pack();
	}
}
