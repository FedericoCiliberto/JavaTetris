package tetrisGame;

import java.util.Arrays;

public class O implements TetrisPiece {
	private Position[] piecePositions=new Position[4];
	private char type='O';
	private String color="yellow";

	int UNIT;
	int WIDTH;
	int HEIGHT;
	
	public O(int UNIT,int WIDTH,int HEIGHT) {
		this.UNIT=UNIT;
		this.WIDTH=WIDTH;
		this.HEIGHT=HEIGHT;
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
	
	public O(TetrisPiece anotherPiece) {
		piecePositions[0]=new Position();
		piecePositions[1]=new Position();
		piecePositions[2]=new Position();
		piecePositions[3]=new Position();
		this.UNIT=anotherPiece.getUNIT();
		this.WIDTH=anotherPiece.getWIDTH();
		this.HEIGHT=anotherPiece.getHEIGHT();
		this.color=anotherPiece.getColor();
		for(int i=0;i<4;i++) {//manual copy
			this.piecePositions[i].x=anotherPiece.getPiecePositions()[i].getX();
			this.piecePositions[i].y=anotherPiece.getPiecePositions()[i].getY();
		}
		this.type=anotherPiece.getType();
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

	@Override
	public int getUNIT() {
		return UNIT;
	}

	@Override
	public int getWIDTH() {
		return WIDTH;
	}

	@Override
	public int getHEIGHT() {
		return HEIGHT;
	}

}
