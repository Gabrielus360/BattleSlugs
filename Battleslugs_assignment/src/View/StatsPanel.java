package View;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Player;
import Model.Square;

public class StatsPanel extends JPanel
{
	Player playerToDisplay;
	Player enemyPlayer;
	boolean showP1Stats;

	StatsPanel(Player player, Player player2,boolean showP1Stats)
	{
		this.playerToDisplay  = player;
		this.enemyPlayer = player2;
		this.showP1Stats = showP1Stats;
		useCorrectPlayer();
		calculateLabelContents();
		this.setLayout(new GridLayout(7,1));
		addLabels();
		//this.setText(createLabelText());
	}

	public void useCorrectPlayer()
	{
		if(!showP1Stats)
		{
			Player temp = playerToDisplay;
			playerToDisplay = enemyPlayer;
			enemyPlayer = playerToDisplay;
		}
	}

	public void calculateLabelContents()
	{
		playerToDisplay.setAttempts(0);
		playerToDisplay.setSuccessfulHits(0);
		playerToDisplay.setLivesLeft(0);

		Square[][] temp = enemyPlayer.getBoard();
		Square[][] temp2 = playerToDisplay.getBoard();


		for (int i = 0; i < enemyPlayer.getBoard().length; i++) 
		{
			for (int j = 0; j < enemyPlayer.getBoard()[0].length; j++) 
			{
				if(temp[i][j].hasSlug() && temp[i][j].isHit())
				{
					playerToDisplay.incrementSuccessfulHits();
				}
				else if(temp[i][j].isHit())
				{
					playerToDisplay.incrementAttempts();
				}

				//Using enemy length for length of player to display due to
				//efficiency - will not work if they have different board sizes
				if(temp2[i][j].hasSlug() && !temp2[i][j].isHit())
				{
					playerToDisplay.incrementLivesLeft();
				}
			}
		}
	}

	public void addLabels()
	{
		this.add(new JLabel("Total attempted hits: " + playerToDisplay.getAttempts()));
		this.add(new JLabel("Lives left: " + playerToDisplay.getLivesLeft()));
		this.add(new JLabel("Successful enemy hits: " + playerToDisplay.getSuccessfulHits()));
		
	}


}
