package Model;

import javax.swing.JComponent;

public class Square extends JComponent
{
	/*
	 * This allows me to easily add features to a particular square,
	 * without impacting already existing methods. 
	 * As an example, one might decide to add mines to the game
	 * which need to be placed in a square like slug. 
	 * This would be easy to implement here
	 */
	Slug slug;

	
	public Slug getSlug() {
		return slug;
	}

	public void setSlug(Slug slug) {
		this.slug = slug;
	}

}
