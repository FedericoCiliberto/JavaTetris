package tetrisGame;

public class I implements TetrisPiece {
	private Position[] piecePositions=new Position[4];
	private char type='I';
	private int orientation=1; //2 orientations for I-block. 1=horizontal, 2= vertical.
	private String color="red";
	private int UNIT;
	
	
	public I(int UNIT,int WIDTH,int HEIGHT) {
		this.UNIT=UNIT;
		piecePositions[0]=new Position();
		piecePositions[1]=new Position();
		piecePositions[2]=new Position();
		piecePositions[3]=new Position();
		
		piecePositions[0].x=(WIDTH/2)-2*UNIT;
		piecePositions[1].x=piecePositions[0].x+UNIT;
		piecePositions[2].x=piecePositions[1].x+UNIT;
		piecePositions[3].x=piecePositions[2].x+UNIT;
		
		piecePositions[0].y=0;
		piecePositions[1].y=0;
		piecePositions[2].y=0;
		piecePositions[3].y=0;
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
		switch(orientation) {
		case 1: //vado in orientazione 2, verticale
			orientation=2;
			
			piecePositions[0].x+=2*UNIT;
			piecePositions[0].y-=2*UNIT;
			
			piecePositions[1].x+=UNIT;
			piecePositions[1].y-=UNIT;
			
			piecePositions[3].x-=UNIT;
			piecePositions[3].y+=UNIT;
			
			break;
			
		case 2: //vado in orientazione 1, orizzontale
			orientation=1;
			
			piecePositions[0].x-=2*UNIT;
			piecePositions[0].y+=2*UNIT;
			
			piecePositions[1].x-=UNIT;
			piecePositions[1].y+=UNIT;
			
			piecePositions[3].x+=UNIT;
			piecePositions[3].y-=UNIT;
			break;
		}
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