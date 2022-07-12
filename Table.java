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
	
	public Table(int UNIT){
		int i,j;
		for(i=0;i<20;i++) {
			for(j=0;j<10;j++) {
				matrix[i][j]=new Box();
			}
		}
		this.UNIT=UNIT;
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
}














