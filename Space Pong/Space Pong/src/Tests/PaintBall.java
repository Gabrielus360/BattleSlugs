package Tests;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PaintBall extends JPanel
{
	public PaintBall() 
	{
		JButton j = new JButton();
		j.setVisible(true);
		this.setSize(300,300);
		this.add(j);
		this.setVisible(true);
		System.out.println(this.getSize());
	}
}
