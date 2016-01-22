package View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Player;

public class StatsPanel extends JPanel
{
	public StatsPanel(Player p) 
	{
		JLabel hits = new JLabel("Total attempted hits: " + p.getAttempts() );
		JLabel lives = new JLabel("Lives left: " + p.getLivesLeft());
		JLabel enemyHits = new JLabel("Successful enemy hits: " + p.getSuccessfulHits());
		JLabel enemyHitsLeft = new JLabel("Enemy hits left: " + (p.getTotalSlugs() - p.getSuccessfulHits()));
		setPreferredSize(new Dimension(200,300));
		//stats.setBackground(Color.black);
		
		//hits.setFont(new Font("Serif", Font.PLAIN, 14));
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(hits);
		add(lives);
	}
	
	/*
	public void refreshStats()
	{
		
	}
	*/
}
