package tetrisGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import tetrisGame.Table.Box;
import javax.swing.*;

public class MyPanel extends JPanel implements KeyListener {
	public static final int UNIT=30;
	public static final int HEIGHT=UNIT*(20+2); //the +2 is a frame
	public static final int WIDTH=UNIT*(10+2);
	public static final int O=0;
	public static final int I=1;
	public static final int S=2;
	public static final int Z=3;
	public static final int L=4;
	public static final int J=5;
	public static final int T=6;
	public static final int DEFAULT_FALLING_TIMER_SPEED=300;
	public static final int SPEEDEDUP_FALLING_TIMER_SPEED=40;
	public int FALLING_TIMER_SPEED=DEFAULT_FALLING_TIMER_SPEED;
	public int PLAYER_TIMER_SPEED=40;
	public boolean gameOver=false;
	
	public char direction='N'; //can be L=left,R=right,N=none;
	public boolean speedUp=false;
	
	TetrisPiece piece;
	Table table=new Table(UNIT,WIDTH,HEIGHT);
	Timer fallingTimer;
	Timer playerTimer;
	Random random=new Random();
	
	
	MyPanel(){
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(this);
		generatePiece();
		fallingTimer=new Timer(FALLING_TIMER_SPEED,e->{
			if(piece!=null) {
				piece.fall();
				if(table.checkCollision(piece)) {
					table.addPiece(piece);
					generatePiece();
					if(table.checkGameOver(piece)) {
						gameOver();
					}
				}
			}
			repaint();
		});
		fallingTimer.start();
		
		playerTimer=new Timer(PLAYER_TIMER_SPEED,e->{
			movePlayer();
			repaint();
		});
		playerTimer.start();
	}

	
	private void movePlayer() {
		Position[] pos;
		Box b;
		switch(direction){
			case 'R':
				pos=piece.getPiecePositions();
				for(int i=0;i<4;i++){
					if(pos[i].getX()==WIDTH-2*UNIT) {
						return; //cant move if at the right there is no space left
					}
					if(pos[i].getY()/UNIT -1>0) {//only if the piece has already entered the matrix of the table (no new pieces just spawned or i would get an index out of bound exception)
						b=table.getBox(pos[i].getY()/UNIT -1 , pos[i].getX()/UNIT -1+1);	
						if(b.isOccupied()) {
							return; //cant move if at the right of one piece is occupied 
						}
					}
				}
				//if arrived here, at the right is clear
				piece.goRight();
			break;
			case 'L':
				pos=piece.getPiecePositions();
				for(int i=0;i<4;i++){
					if( pos[i].getX()==UNIT) {
						return; //cant move if at the left there is no space left
					}
					if(pos[i].getY()/UNIT -1>0) { //only if the piece has already entered the matrix of the table (no new pieces just spawned or i would get an index out of bound exception)
						b=table.getBox(pos[i].getY()/UNIT -1 , pos[i].getX()/UNIT -1-1);
						if(b.isOccupied()) {
							return; //cant move if at the left of one piece is occupied
						}
					}
				}
				//if arrived here, at the left is clear
				piece.goLeft();
			break;
		}
		
	}


	private void gameOver() {
		fallingTimer.stop();
		playerTimer.stop();
		gameOver=true;
		repaint();
		
	}
	

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d=(Graphics2D)g;
		drawFrame(g2d);
		drawGrid(g2d);
		drawTable(g2d);
		if(piece!=null) {
			drawPiece(g2d);
		}
		
		if(gameOver) {
			g2d.setColor(Color.green);
			g2d.fillRect(WIDTH/2-100, HEIGHT/2-50, 200, 100);
			g2d.setColor(Color.red);
			g2d.setFont(new Font("serif", Font.ITALIC, 30));
			g2d.drawString("Game over!", WIDTH/2-50, HEIGHT/2);
		}
	}
	
	private void drawTable(Graphics2D g2d) {
		int i,j;
		
		for(i=0;i<20;i++) {
			for(j=0;j<10;j++) {
				Box box=table.getBox(i, j);
				if(box.isOccupied()) {
					//System.out.println("entrato");
					g2d.setColor(getColorByName(box.getColor()));
					//System.out.println(box.getColor());
					g2d.fillRect((j+1)*UNIT, (i+1)*UNIT, UNIT, UNIT);
				}
			}
		}
		
	}


	private void drawPiece(Graphics2D g2d) {
		g2d.setColor(getColorByName(piece.getColor()));
		Position[] pos=piece.getPiecePositions();
		for(int i=0;i<4;i++){
			g2d.fillRect(pos[i].x, pos[i].y, UNIT, UNIT);
			g2d.drawRect(pos[i].x, pos[i].y, UNIT, UNIT);
		}
	}



	private void drawFrame(Graphics2D g2d) {
		g2d.setColor(Color.DARK_GRAY);
		//cornici orizzontali
		for(int i=0;i<WIDTH;i+=UNIT) {
			g2d.fillRect(i, 0, UNIT,UNIT);
			g2d.fillRect(i, HEIGHT-UNIT, UNIT, UNIT);
		}
		
		//cornici verticali
		for(int i=0;i<HEIGHT;i+=UNIT) {
			g2d.fillRect(0, i, UNIT,UNIT);
			g2d.fillRect(WIDTH-UNIT, i, UNIT, UNIT);
		}
		
	}


	private void drawGrid(Graphics2D g2d) {
		g2d.setColor(Color.gray);
		//linee verticali
		for(int i=0;i<=WIDTH;i+=UNIT) {
			g2d.drawLine(i, 0, i, HEIGHT);
		}
		
		//linee orizzontali
		for(int i=0;i<=HEIGHT;i+=UNIT) {
			g2d.drawLine(0,i,WIDTH,i);
		}
		
	}
	
	public void generatePiece(){
		int i=random.nextInt(J+1 - O) + O;
		switch(i) {
		case O:
			piece=new O(UNIT,WIDTH,HEIGHT);
			break;
		case I:
			piece=new I(UNIT,WIDTH,HEIGHT);
			break;
		case S:
			piece=new S(UNIT,WIDTH,HEIGHT);
			break;
		case Z:
			piece=new Z(UNIT,WIDTH,HEIGHT);
			break;
		case L:
			piece=new L(UNIT,WIDTH,HEIGHT);
			break;
		case J:
			piece=new J(UNIT,WIDTH,HEIGHT);
			break;
		case T:
			break;
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_RIGHT: //RIGHT
			direction='R';
			break;
			
		case KeyEvent.VK_LEFT: //LEFT
			direction='L';
			break;
			
		case KeyEvent.VK_DOWN: //DOWN
			FALLING_TIMER_SPEED=SPEEDEDUP_FALLING_TIMER_SPEED;
			fallingTimer.setDelay(FALLING_TIMER_SPEED);
			break;
		case KeyEvent.VK_UP: //UP
			//before rotating, i need to check if by rotating i get collisions i dont want!
			
			char type=piece.getType();
			TetrisPiece anotherPiece;
			switch(type) {
				case 'O':
					 anotherPiece=new O(piece);
					break;
				case 'I':
					 anotherPiece=new I(piece);
					break;
				case 'S':
					 anotherPiece=new S(piece);
					break;
				case 'Z':
					 anotherPiece=new O(piece); //TO CHANGE IN Z etc!!!
					break;
				case 'L':
					 anotherPiece=new O(piece);
					break;
				case 'J':
					 anotherPiece=new O(piece);
					break;
				case 'T':
					 anotherPiece=new O(piece);
					break;
				default:  //cant enter here
					anotherPiece=null;
					break;
			}
			anotherPiece.rotate();
			if(!table.checkCollision(anotherPiece)){//if rotating the copy of the current piece i get no collision, i can actually rotate the true piece
				piece.rotate();
				
			}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_RIGHT: //RIGHT
			direction='N';
			break;
		case KeyEvent.VK_LEFT: //LEFT
			direction='N';
			break;
		case KeyEvent.VK_DOWN: //DOWN
			FALLING_TIMER_SPEED=DEFAULT_FALLING_TIMER_SPEED;
			fallingTimer.setDelay(FALLING_TIMER_SPEED);
			break;
		case KeyEvent.VK_UP: //UP
			break;
			
		}
	}
	
	public static Color getColorByName(String name) { //conver color to names
	    try {
	        return (Color)Color.class.getField(name.toUpperCase()).get(null);
	    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
