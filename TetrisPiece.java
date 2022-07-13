package tetrisGame;

public interface TetrisPiece {
	
	public void rotate();
	public Position[] getPiecePositions();
	public void fall();
	public char getType();
	public String getColor();
	public void goLeft();
	public void goRight();
	public int getUNIT();
	public int getWIDTH();
	public int getHEIGHT();
}
