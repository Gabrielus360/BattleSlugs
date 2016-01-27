package Model;

public class Square
{
	/*
	 * This allows me to easily add features to a particular square,
	 * without impacting already existing methods. 
	 * As an example, one might decide to add mines to the game
	 * which need to be placed in a square like slug. 
	 * This would be easy to implement here
	 */
	boolean slug = false;
	boolean hit = false;

	public boolean hasSlug() {
		return slug;
	}
	public void setSlug(boolean slug) {
		this.slug = slug;
	}
	public boolean isHit() {
		return hit;
	}
	public void setHit(boolean hit) {
		this.hit = hit;
	}
}
