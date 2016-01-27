package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import Controller.GridController;
import Controller.SlugController;
import Model.Player;
import Model.Square;

public class ShowUI extends JFrame
{

	Player p1;
	Player p2;
	boolean showP1 = true;
	SlugController slugControl = new SlugController();

	/*
	 * Shows game UI
	 */
	public ShowUI(Player p1, Player p2) 
	{
		this.p1 = p1;
		this.p2 = p2;
		setTitle("Battleslugs");
		setSize(440,675);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		userInterface();
	}

	public void userInterface()
	{
		//make sure correct values are stored in player, not the temp player!
		//DrawGrid p_Board;

		//Extra attention here due to listener and visible all is true
		GridController grid = new GridController(getCurrentPlayer().getBoard(), true);
		JPanel centerGridPanel = grid.getGrid();
		//add(new StatsPanel(temp), "East");

		setLayout(new BorderLayout());
		add(centerGridPanel, "Center");
		
		
		
		int[][] slugCoordinates = new int[5][2];
		slugControl.generateSlug(6, 6, slugCoordinates, 0);
		JPanel eastPane = new JPanel();
		eastPane.setLayout(new GridLayout(2,1));

		eastPane.add(new StatsPanel(p1,p2,showP1));
		eastPane.setVisible(true);
		
		

		add(SwitchPlayerPanel(), "South");
		add(eastPane, "East");
		setVisible(true);
	}

	/*
	public addSlugsToBoard()
	{

	}
	 */


	/*
	 * Needs 2d array with x and y coordinates of slugs
	 * Format of array:
	 * Format of result:
	 * result[index][0] = XLOCATION
	 * result[index][1] = YLOCATION
	 */
	
	/*
	 * Calculates which turn it is and returns the player directly
	 */
	public Player getCurrentPlayer()
	{
		if(showP1)
		{
			return p1;
		}
		else if(!showP1)
		{
			return p2;
		}
		else
		{
			System.out.println("ERROR: Player unidentified!");
			System.out.println("Setting it anyways to player 2 due to forced compiler syntax");
			return p2;
		}
	}
	
	
	/*
	 * Has panel with a switch player button
	 */
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

	/*
	 * Switches player back and forth when clicked
	 */
	private void switchPlayer()
	{
		showP1 = !showP1;
		getContentPane().removeAll();
		userInterface();
	}

	/*
	 * Adds element given to a center of a JPanel so as to not take all the space (makes it neater)
	 */
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
