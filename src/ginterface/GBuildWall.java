package ginterface;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import code.Board;
import code.GameSettings;
import code.IceWall;
import code.Jewel;
import code.Player;
import code.StoneWall;
import code.TurtleTile;

public class GBuildWall {

	public HashMap<Integer, JButton> buttons;
	public static boolean done;
	public static boolean ticked;
	public int choice;

	public GBuildWall(Player p) {
		done = false;
		choice = 0;
		buttons = new HashMap<Integer, JButton>();
		JFrame f = new JFrame("Build a wall");
		Object[][] board = Board.board;
		int[] giPosistions = GBoard.giPosistions;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].getClass() == TurtleTile.class) {
					String turtle = ((TurtleTile) board[i][j]).getType();
					char orientation = GameSettings.turtlesOrientations.get(turtle);

					JLabel im = new JLabel(new ImageIcon("src/images/" + turtle + "/" + orientation + ".png"));
					im.setBounds(giPosistions[j], giPosistions[i], 98, 98);
					f.add(im);
				}

				else if (board[i][j].getClass() == Jewel.class) {

					String jewel = ((Jewel) board[i][j]).getType();

					JLabel im = new JLabel(new ImageIcon("src/images/" + jewel + ".png"));
					im.setBounds(giPosistions[j], giPosistions[i], 98, 98);
					f.add(im);

				}

				else if (board[i][j].getClass() == StoneWall.class) {

					JLabel im = new JLabel(new ImageIcon("src/images/StoneWall.png"));
					im.setBounds(giPosistions[j], giPosistions[i], 98, 98);
					f.add(im);

				}

				else if (board[i][j].getClass() == IceWall.class) {

					JLabel im = new JLabel(new ImageIcon("src/images/IceWall.png"));
					im.setBounds(giPosistions[j], giPosistions[i], 98, 98);
					f.add(im);

				}

				else {

					JButton b = new JButton(new ImageIcon("src/images/Transparent.png"));
					b.setBounds(giPosistions[j], giPosistions[i], 98, 98);
					b.setContentAreaFilled(false);
					buttons.put(i, b);
					f.add(b);

					// x and y are use to set the position of the wall you build after you press the
					// button, they have to be final for it to work

					final int x = i;
					final int y = j;

					b.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							if (choice != 0) {

								switch (choice) {
								case 1:
									board[x][y] = p.stoneWalls.get(0);
									p.stoneWalls.remove(0);
									break;
								case 2:
									board[x][y] = p.iceWalls.get(0);
									p.iceWalls.remove(0);
									break;
								}
								done = true;
								f.dispose();
							}

						}
					});

				}

			}

		}

		JLabel bg = new JLabel(new ImageIcon("src/images/Background.png"));
		bg.setBounds(0, 0, 900, 900);
		f.add(bg);

		// Because it is impossible to put an image inside a JCheckBox we create empty
		// JCheckboxes and we put the images next to it

		Font myFont2 = new Font("LessLarge", Font.BOLD, 18);
		
		CheckboxGroup cbg = new CheckboxGroup();
		
		if (!p.stoneWalls.isEmpty()) {
			
			Label stoneNumber = new Label("x" + p.stoneWalls.size());
			stoneNumber.setBounds(1130, 220, 40, 15);
			stoneNumber.setFont(myFont2);
			f.add(stoneNumber);
			
			
			JLabel StoneWall = new JLabel(new ImageIcon("src/images/StoneWall.png"));
			StoneWall.setBounds(1020, 150, 98, 98);
			f.add(StoneWall);
			
			Checkbox checkBoxStone = new Checkbox("", cbg, false);
			checkBoxStone.setBounds(1000, 100, 200, 200);
			f.add(checkBoxStone);
			
			
			
			// choice = 0 : nothing chosen
			checkBoxStone.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					choice = 1; // stone wall chosen
					ticked = true;
				}
			});
			
		}
		
		
		
		if (!p.iceWalls.isEmpty()) {
			
			Label stoneNumber = new Label("x" + p.iceWalls.size());
			stoneNumber.setBounds(1130,420, 40, 15);
			stoneNumber.setFont(myFont2);
			f.add(stoneNumber);
			
			JLabel IceWall = new JLabel(new ImageIcon("src/images/IceWall.png"));
			IceWall.setBounds(1020, 350, 98, 98);
			f.add(IceWall);
			
			Checkbox checkBoxIce = new Checkbox("", cbg, false);
			checkBoxIce.setBounds(1000, 300, 200, 200);
			f.add(checkBoxIce);
			
			
			
			// choice = 0 : nothing chosen
			checkBoxIce.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					choice = 2; // ice wall chosen
					ticked = true;
				}
			});

		}

		
		
		
		
		

		
		
		f.setSize(1400, 950);

		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}