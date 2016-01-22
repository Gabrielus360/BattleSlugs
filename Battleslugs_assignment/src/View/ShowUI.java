package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import Model.Player;

public class ShowUI extends JFrame
{

	Player p1;
	Player p2;
	boolean showP1 = true;
	

	public ShowUI(Player p1, Player p2) 
	{
		setTitle("Battleslugs");
		setSize(440,675);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.p1 = p1;
		this.p2 = p2;
		userInterface();
	}
	
	public void userInterface()
	{
		//make sure correct values are stored in player, not the temp player!
		DrawGrid p_Board;
		Player temp;
		
		if(showP1)
		{
			//Used for debugging. should not be true in final
			temp = p1;
		}
		else if(!showP1)
		{
			temp = p2;
		}
		else
		{
			System.out.println("Error in player identification");
			System.out.println("Setting it anyways to player 2 due to forced compiler syntax");
			temp = p2;
		}
		
		//Extra attention here
		p_Board = new DrawGrid(temp.getBoard(),false);
		add(new StatsPanel(temp), "East");
		
		setLayout(new BorderLayout());
		add(p_Board, "Center");
		

		add(SwitchPlayerPanel(), "South");
		setVisible(true);
		
		
	}
	
	public JPanel SwitchPlayerPanel() 
	{
		JToggleButton btn = new JToggleButton("Switch player");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchPlayer();
			}});
			
		return addAtCenter(btn);
	}
	
	private void switchPlayer()
	{
		showP1 = !showP1;
		getContentPane().removeAll();
		userInterface();
	}

	private JPanel addAtCenter(Component c)
	{

		JPanel result = new JPanel();
		result.setLayout(new GridLayout(3,3));

		for (int i = 0; i < 4; i++) 
		{
			result.add(new JPanel());
		}

		result.add(c);	

		for (int i = 0; i < 4; i++) 
		{
			result.add(new JPanel());
		}
		return result;
	}
}
