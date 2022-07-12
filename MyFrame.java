package tetrisGame;

import java.awt.Color;

import javax.swing.JFrame;

public class MyFrame extends JFrame{
	MyPanel gamePanel= new MyPanel();
	MyFrame(){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Tetris");
		this.getContentPane().setBackground(Color.black);
		
		this.add(gamePanel);
		this.pack();
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
