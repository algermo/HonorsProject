/*****************************************************************
GUI for the MathGame

@author Molly Alger
@author Jennifer Moon
@version 1
*****************************************************************/

package MathGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

public class GamePanel extends JPanel {

	private JButton[][] board;
	private int size = 4;
	
	public GamePanel() {
		
		// create panels for the GUI
		JPanel j = new JPanel();
		j.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		JPanel center = new JPanel();
		JPanel bottom = new JPanel();
		
		ButtonListener listen = new ButtonListener();
		
		// create a grid layout for the board
		center.setLayout(new GridLayout(size, size));
		
		// fill the board with JLabels
		board = new JButton[size][size];
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				board[row][col] = new JButton("X");
				board[row][col].setPreferredSize(new Dimension (75, 50));
				board[row][col].setEnabled(true);
				center.add(board[row][col]);
				board[row][col].addActionListener(listen);
			}
		}
		
		// manually placing fires
		board[0][1].setEnabled(false);
		board[0][1].setBackground(Color.red);
		board[2][1].setEnabled(false);
		board[2][1].setBackground(Color.red);
		board[3][1].setEnabled(false);
		board[3][1].setBackground(Color.red);
		board[1][2].setEnabled(false);
		board[1][2].setBackground(Color.red);
		board[2][2].setEnabled(false);
		board[2][2].setBackground(Color.red);
		board[2][3].setEnabled(false);
		board[2][3].setBackground(Color.red);
		
		// put panels in correct places
		add(j);
		j.add(top, BorderLayout.NORTH);
		j.add(center, BorderLayout.CENTER);
		j.add(bottom, BorderLayout.SOUTH);
		
	}
	
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			for (int row = 0; row < size; row++){
				for (int col = 0; col < size; col++) {
					if(board[row][col] == e.getSource()) {
						board[row][col].setBackground(Color.blue);
						for(int i = 0; i < size; i++) {
							board[row][i].setEnabled(false);
							board[i][col].setEnabled(false);
						}
					}
				}
			}
		}
	}
}
