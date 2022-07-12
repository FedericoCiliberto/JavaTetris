package tetrisGame;

public class O implements TetrisPiece {
	private Position[] piecePositions=new Position[4];
	private char type='O';
	private String color="yellow";

	int UNIT;
	
	
	public O(int UNIT,int WIDTH,int HEIGHT) {
		this.UNIT=UNIT;
		piecePositions[0]=new Position();
		piecePositions[1]=new Position();
		piecePositions[2]=new Position();
		piecePositions[3]=new Position();
		
		piecePositions[0].x=(WIDTH/2)-2*UNIT;
		piecePositions[1].x=piecePositions[0].x+UNIT;
		piecePositions[2].x=piecePositions[0].x;
		piecePositions[3].x=piecePositions[1].x;
		
		piecePositions[0].y=0;
		piecePositions[1].y=0;
		piecePositions[2].y=UNIT;
		piecePositions[3].y=UNIT;
	}
	
	@Override
	public Position[] getPiecePositions() {
		return piecePositions;
	}
	@Override
	public char getType() {
		return type;
	}
	
	@Override
	public void rotate() {
		//O doesn't rotate
	}
	
	@Override
	public void fall() {
		for(int i=0;i<4;i++) {
			piecePositions[i].y+=UNIT;
		}
	}
	public String getColor() {
		return color;
	}

	@Override
	public void goLeft() {
		for(int i=0;i<4;i++) {
			piecePositions[i].x-=UNIT;
		}
		
	}

	@Override
	public void goRight() {
		for(int i=0;i<4;i++) {
			piecePositions[i].x+=UNIT;
		}
		
	}

}
