package tetrisGame;

public class Table {
	public class Box{
		boolean occupied=false;
		String color;
		
		public Box() {
			occupied=false;
			
		}
		public boolean isOccupied() {
			return occupied;
		}
		public void setOccupied(boolean occupied) {
			this.occupied = occupied;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
	}
	
	Box[][] matrix=new Box[20][10];
	int UNIT;
	int HEIGHT;
	int WIDTH;
	
	public Table(int UNIT,int WIDTH,int HEIGHT){
		int i,j;
		for(i=0;i<20;i++) {
			for(j=0;j<10;j++) {
				matrix[i][j]=new Box();
			}
		}
		this.UNIT=UNIT;
		this.WIDTH=WIDTH;
		this.HEIGHT=HEIGHT;
	}
	
	public Box getBox(int i, int j) {
		return matrix[i][j];
	}

	public void addPiece(TetrisPiece piece) {
		Position[] pos=piece.getPiecePositions();
		for(int i=0;i<4;i++){
			matrix[pos[i].getY()/UNIT-1][pos[i].getX()/UNIT-1].setOccupied(true); //must subtract 2 from the positions because in the matrix we have no frames but in the game picture we have
			matrix[pos[i].getY()/UNIT-1][pos[i].getX()/UNIT-1].setColor(piece.getColor());
		}
		
	}
	
	public boolean checkCollision(TetrisPiece piece){
		Position[] pos=piece.getPiecePositions();
		for(int i=0;i<4;i++){
			if(pos[i].getY()/UNIT>=HEIGHT/UNIT - 2) { //the piece has reached the bottom. Collided.
				return true;
			}
			try {
				if(matrix[pos[i].getY()/UNIT-1 + 1][pos[i].getX()/UNIT-1].isOccupied()) {
					return true;
				}
			}catch(IndexOutOfBoundsException e) {
				//ignore!
				//only case is generated is when i rotate a piece before he totally entered the table
				//It gives no problem to ignore it.
			}
		}
		return false;
	}

	public boolean checkGameOver(TetrisPiece piece) {
		Position[] pos=piece.getPiecePositions();
		for(int i=0;i<4;i++) {
			if(pos[i].getY()/UNIT-1 + 1 == 0 && matrix[pos[i].getY()/UNIT-1 + 1][pos[i].getX()/UNIT-1].isOccupied()) { //if there is a block of the currente piece which is at the top and has under itself a filled box, game over.
				return true;
			}
		}
		return false;
	}


}














