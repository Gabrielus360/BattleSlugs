package Model;

/*
 * Used as intermediary between classes to be able to pass data from child to parent
 */

public interface GridListener 
{
	void clicked(Square s);
	void clicked(int xSquare,int ySquare);
}
